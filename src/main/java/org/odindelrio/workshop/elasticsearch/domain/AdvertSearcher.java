package org.odindelrio.workshop.elasticsearch.domain;

import rx.Observable;

public interface AdvertSearcher {
    Observable<Advert> findAllBySearchText(String searchText);

}
