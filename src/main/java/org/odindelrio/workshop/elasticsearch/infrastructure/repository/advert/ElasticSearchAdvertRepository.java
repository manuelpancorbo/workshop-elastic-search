package org.odindelrio.workshop.elasticsearch.infrastructure.repository.advert;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.odindelrio.workshop.elasticsearch.domain.Advert;
import org.odindelrio.workshop.elasticsearch.domain.AdvertReader;
import org.odindelrio.workshop.elasticsearch.domain.AdvertWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.*;


public class ElasticSearchAdvertRepository implements AdvertReader, AdvertWriter {

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
    public Observable<Advert> findAll() {
        return null;
    }

    @Override
    public Observable<Advert> findById(String id) {
        return null;
    }
}
