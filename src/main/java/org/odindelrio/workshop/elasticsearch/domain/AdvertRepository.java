package org.odindelrio.workshop.elasticsearch.domain;

import rx.Observable;

public interface AdvertRepository {
    void add(Advert user);

    Observable<Advert> findAll();

    Observable<Advert> findById(String id);
}
