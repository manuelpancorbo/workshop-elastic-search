package org.odindelrio.workshop.elasticsearch.application;

import org.odindelrio.workshop.elasticsearch.domain.Advert;
import org.odindelrio.workshop.elasticsearch.domain.AdvertReader;
import org.odindelrio.workshop.elasticsearch.domain.AdvertWriter;
import rx.Observable;

import javax.inject.Inject;

public class AdvertService {

    private AdvertReader advertReader;
    private AdvertWriter advertWriter;

    @Inject
    public AdvertService(AdvertReader advertReader, AdvertWriter advertWriter) {
        this.advertReader = advertReader;
        this.advertWriter = advertWriter;
    }

    public Observable<Advert> listAdverts() {
        return this.advertReader.findAll();
    }

    public Observable<Advert> showAdvert(String a) {
        return this.advertReader.findById(a);
    }

    public void createAdvert(Advert advert) {
        advertWriter.add(advert);
    }
}
