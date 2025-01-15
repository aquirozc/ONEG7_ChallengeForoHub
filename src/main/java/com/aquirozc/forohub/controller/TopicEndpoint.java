package com.aquirozc.forohub.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aquirozc.forohub.data.Topic;
import com.aquirozc.forohub.data.User;
import com.aquirozc.forohub.repo.TopicRepository;
import com.aquirozc.forohub.repo.UserRepository;
import com.aquirozc.forohub.transitional.TopicDAO;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicEndpoint {

    private UserRepository userRepo;
    private TopicRepository topicRepo;

    public TopicEndpoint(UserRepository userRepo, TopicRepository topicRepo) {
        this.userRepo = userRepo;
        this.topicRepo = topicRepo;
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<Topic> createTopic(@RequestBody TopicDAO t, Principal principal, UriComponentsBuilder URIBuilder) {
        
        User user = userRepo.findById(Long.parseLong(principal.getName())).get();
        Topic topic = new Topic(t, user);
        topicRepo.save(topic);

        URI uri = URIBuilder.path("/api/v1/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(topic);

    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Topic>> getTopics() {
        return ResponseEntity.ok(topicRepo.findAll());
    }


    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {

        if(!topicRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(topicRepo.findById(id).get());
    }

    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody TopicDAO t, Principal principal) {

        if(!topicRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Topic topic = topicRepo.findById(id).get();

        if(topic.getUser().getId() != Long.parseLong(principal.getName())) {
            return ResponseEntity.status(403).build();
        }

        topic.setTitle(t.getTitle());
        topic.setMessage(t.getMessage());
        topicRepo.save(topic);

        return ResponseEntity.ok(topic);
    }
    
}
