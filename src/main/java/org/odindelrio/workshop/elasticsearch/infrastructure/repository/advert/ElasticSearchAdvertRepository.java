package org.odindelrio.workshop.elasticsearch.infrastructure.repository.advert;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.odindelrio.workshop.elasticsearch.domain.Advert;
import org.odindelrio.workshop.elasticsearch.domain.AdvertSearcher;
import org.odindelrio.workshop.elasticsearch.domain.AdvertWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


public class ElasticSearchAdvertRepository implements AdvertSearcher, AdvertWriter {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchAdvertRepository.class);

    /**
     * Note that this is a hostname, is called elasticsearch due to the docker-compose configuration.
     */
    private static final String ES_HOST = "elasticsearch";

    private Client client;

    public ElasticSearchAdvertRepository() {
         client = new TransportClient()
            .addTransportAddress(new InetSocketTransportAddress(ES_HOST, 9300));
    }

    @Override
    public void add(Advert advert) {
        XContentBuilder builder;

        try {
            builder = jsonBuilder()
                .startObject()
                .field("id", advert.getId())
                .field("title", advert.getTitle())
                .field("body", advert.getBody())
                .field("zipCode", advert.getZipCode())
                    .startObject("location")
                        // lat & lon abbreviates are required by ES instead of full names.
                        .field("lat", advert.getLatitude())
                        .field("lon", advert.getLongitude())
                    .endObject()
                .endObject();

            logger.info(String.format("Inserting... %s", builder.string()));

        } catch (IOException e) {
            throw new RuntimeException("Error while indexing in Elastic Search", e);
        }

        IndexResponse response = client.prepareIndex("ads", "ad", advert.getId())
            .setSource(builder)
            .execute()
            .actionGet();

        logger.info(String.format("Advert '%s' indexed in ES, version: '%s", advert.getId(), response.getVersion()));
    }

    @Override
    public Observable<Advert> findAllBySearchText(String searchText) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {

                // Constructs a query that gives 90% of priority to the title and 10% to the body.
                QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                    .should(
                        QueryBuilders.matchQuery("title", searchText)
                            .boost(0.9f)
                            .type(MatchQueryBuilder.Type.PHRASE)
                    )
                    .should(
                        QueryBuilders.matchQuery("body", searchText).boost(0.1f)
                    );

                SearchRequestBuilder searchBuilder = client.prepareSearch("ads").setQuery(queryBuilder);

                logger.info(String.format("query to execute in ES: %s", searchBuilder.toString()));

                ListenableActionFuture<SearchResponse> searchResponseFuture = searchBuilder.execute();

                SearchResponse searchResponse;

                try {
                    searchResponse = searchResponseFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException("Error executing search in Elastic Search", e);
                }

                searchResponse
                    .getHits()
                    .forEach(hit -> subscriber.onNext(transformHitToAdvert(hit)));

                subscriber.onCompleted();
            }
        });
    }

    private Advert transformHitToAdvert(SearchHit hit) {

        Map<String, Object> source = hit.getSource();
        Map<String, Double> location = (Map<String, Double>) source.get("location");

        return new Advert(
            hit.getId(),
            (String) source.get("title"),
            (String) source.get("body"),
            location.get("lat"),
            location.get("lon"),
            (String) source.get("zipCode")
        );
    }

}
