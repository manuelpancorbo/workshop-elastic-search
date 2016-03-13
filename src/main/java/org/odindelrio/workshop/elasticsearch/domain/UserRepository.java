package org.odindelrio.workshop.elasticsearch.domain;

import rx.Observable;

public interface UserRepository {
    void add(User user);

    Observable<User> findAll();

    Observable<User> findById(String userId);

    Observable<User> findByName(String name);
}
