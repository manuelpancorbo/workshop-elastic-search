package org.odindelrio.workshop.elasticsearch.domain;

import rx.Observable;

public interface AdvertReader {

    Observable<Advert> findAll();

    Observable<Advert> findById(String id);
}
