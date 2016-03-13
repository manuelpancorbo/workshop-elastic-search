package org.odindelrio.workshop.elasticsearch.infrastructure.repository.advert;

import org.odindelrio.workshop.elasticsearch.domain.Advert;
import org.odindelrio.workshop.elasticsearch.domain.AdvertRepository;
import org.odindelrio.workshop.elasticsearch.domain.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import rx.Observable;

import javax.inject.Inject;

public class JdbcAdvertRepository implements AdvertRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcAdvertRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Inject
    public JdbcAdvertRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Advert advert) {
        jdbcTemplate.update(
            "INSERT INTO " +
                "advert (id, title, body, latitude, longitude, zip_code) " +
                "VALUES (?, ?, ?, ?, ?, ?)",
            advert.getId(),
            advert.getTitle(),
            advert.getBody(),
            advert.getLatitude(),
            advert.getLongitude(),
            advert.getZipCode()
        );
    }

    @Override
    public Observable<Advert> findAll() {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                jdbcTemplate
                    .query(
                        "SELECT id, title, body, latitude, longitude, zip_code " +
                            "FROM advert",
                        rowMapper()
                    )
                    .forEach(advert -> subscriber.onNext(advert));
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Advert> findById(String id) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                Advert advert = jdbcTemplate
                    .queryForObject(
                        "SELECT id, title, body, latitude, longitude, zip_code " +
                            "FROM advert " +
                            "WHERE id = ?",
                        rowMapper(),
                        id
                    );
                subscriber.onNext(advert);
                subscriber.onCompleted();
            }
        });
    }

    private RowMapper<Advert> rowMapper() {

        return (rs, rowNum) -> {

            Location location = new Location(rs.getDouble("latitude"), rs.getDouble("longitude"));

            return new Advert(
                rs.getString("id"),
                rs.getString("title"),
                rs.getString("body"),
                location.getLatitude(),
                location.getLongitude(),
                rs.getString("zip_code")
            );
        };
    }
}
