package org.odindelrio.workshop.elasticsearch;

import org.odindelrio.workshop.elasticsearch.application.UserService;
import org.odindelrio.workshop.elasticsearch.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class Router {

    private UserService service;
    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    @Inject
    public Router(UserService service) {
        this.service = service;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "Welcome to your Spring Cloud project!";
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> listUsers(@RequestParam(name = "name", required = false) String name) {
        return service.listUsers(name).toList().toBlocking().first();
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        service.createUser(user);
        return ResponseEntity
            .created(URI.create("/users/" + user.getId()))
            .body(user);
    }

    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public User showUser(@PathVariable String userId) {
        return service.showUser(userId).toBlocking().first();
    }
}
