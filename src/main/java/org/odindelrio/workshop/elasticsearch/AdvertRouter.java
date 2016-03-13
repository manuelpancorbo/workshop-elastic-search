package org.odindelrio.workshop.elasticsearch;

import org.odindelrio.workshop.elasticsearch.application.AdvertService;
import org.odindelrio.workshop.elasticsearch.domain.Advert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@RestController
public class AdvertRouter {
    private AdvertService service;
    private static final Logger logger = LoggerFactory.getLogger(AdvertRouter.class);

    @Inject
    public AdvertRouter(AdvertService service) {
        this.service = service;
    }

    @RequestMapping(path = "/adverts", method = RequestMethod.GET)
    public List<Advert> listAdverts() {
        return service.listAdverts().toList().toBlocking().first();
    }

    @RequestMapping(path = "/adverts", method = RequestMethod.POST)
    public ResponseEntity<Advert> createAdvert(@RequestBody Advert advert) {
        service.createAdvert(advert);
        return ResponseEntity
            .created(URI.create("/adverts/" + advert.getId()))
            .body(advert);
    }

    @RequestMapping(path = "/adverts/{advertId}", method = RequestMethod.GET)
    public Advert showAdvert(@PathVariable String advertId) {
        return service.showAdvert(advertId).toBlocking().first();
    }

}
