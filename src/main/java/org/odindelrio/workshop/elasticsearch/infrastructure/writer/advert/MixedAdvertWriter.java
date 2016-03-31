package org.odindelrio.workshop.elasticsearch.infrastructure.writer.advert;

import org.odindelrio.workshop.elasticsearch.domain.Advert;
import org.odindelrio.workshop.elasticsearch.domain.AdvertWriter;

import javax.inject.Inject;
import java.util.Set;

public class MixedAdvertWriter implements AdvertWriter {

    private Set<AdvertWriter> repositories;

    @Inject
    public MixedAdvertWriter(Set<AdvertWriter> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void add(Advert advert) {
        repositories.forEach(repository -> repository.add(advert));
    }
}
