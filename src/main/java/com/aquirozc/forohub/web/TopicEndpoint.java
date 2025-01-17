package com.aquirozc.forohub.web;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aquirozc.forohub.domain.dto.TopicCreationRequest;
import com.aquirozc.forohub.domain.dto.TopicGenericResponse;
import com.aquirozc.forohub.domain.model.Topic;
import com.aquirozc.forohub.domain.service.TopicService;
import com.aquirozc.forohub.infra.ReadOnlyTopicException;
import com.aquirozc.forohub.infra.TopicNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/topics")
public class TopicEndpoint {

    private TopicService service;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<TopicGenericResponse> createTopic(@RequestBody TopicCreationRequest t, Principal principal, UriComponentsBuilder URIBuilder) {
        
        TopicGenericResponse res = service.submitNewTopic(t, Long.parseLong(principal.getName()));

        URI uri = URIBuilder.path("/api/v1/topics/{id}").buildAndExpand(res.getId()).toUri();

        return ResponseEntity.created(uri).body(res);

    }

    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<TopicGenericResponse> deleteTopic(@PathVariable long id, Principal principal){
        Topic topic = service.getRepo().findById(id)
                                        .orElseThrow(TopicNotFoundException::new);

        if(topic.getUser().getId() != Long.parseLong(principal.getName())) {
            throw new ReadOnlyTopicException();
        }

        service.deleteTopic(topic);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<TopicGenericResponse>> getTopics() {
        return ResponseEntity.ok(service.getAllTopics());
    }


    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<TopicGenericResponse> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTopic(id));
    }

    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<TopicGenericResponse> updateTopic(@PathVariable long id, TopicCreationRequest req, Principal principal){
        Topic topic = service.getRepo().findById(id)
                                        .orElseThrow(TopicNotFoundException::new);

        if(topic.getUser().getId() != Long.parseLong(principal.getName())) {
            throw new ReadOnlyTopicException();
        }

        return ResponseEntity.ok().body(service.updateTopic(topic, req));
    }
    
}
