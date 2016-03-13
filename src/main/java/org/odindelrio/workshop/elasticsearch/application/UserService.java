package org.odindelrio.workshop.elasticsearch.application;

import org.odindelrio.workshop.elasticsearch.domain.User;
import org.odindelrio.workshop.elasticsearch.domain.UserRepository;
import rx.Observable;

import javax.inject.Inject;

public class UserService {

    private UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<User> listUsers(String name){
        if (name != null) {
            return this.userRepository.findByName(name);
        }

        return this.userRepository.findAll();
    }

    public Observable<User> showUser(String userId){
        return this.userRepository.findById(userId);
    }

    public void createUser(User user){
        userRepository.add(user);
    }
}
