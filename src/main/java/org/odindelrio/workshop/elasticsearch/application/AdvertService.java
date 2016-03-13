package org.odindelrio.workshop.elasticsearch.application;

import org.odindelrio.workshop.elasticsearch.domain.Advert;
import org.odindelrio.workshop.elasticsearch.domain.AdvertRepository;
import rx.Observable;

import javax.inject.Inject;

public class AdvertService {
    private AdvertRepository advertRepository;

    @Inject
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public Observable<Advert> listAdverts(){
        return this.advertRepository.findAll();
    }

    public Observable<Advert> showAdvert(String a){
        return this.advertRepository.findById(a);
    }

    public void createAdvert(Advert advert){
        advertRepository.add(advert);
    }
}
