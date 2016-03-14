package org.odindelrio.workshop.elasticsearch.domain;

import rx.Observable;

public interface AdvertRepository {
    void add(Advert advert);

    Observable<Advert> findAll();

    Observable<Advert> findById(String id);
}
