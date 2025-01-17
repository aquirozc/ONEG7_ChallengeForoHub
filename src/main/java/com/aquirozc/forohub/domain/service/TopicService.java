package com.aquirozc.forohub.domain.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aquirozc.forohub.domain.dto.TopicCreationRequest;
import com.aquirozc.forohub.domain.dto.TopicGenericResponse;
import com.aquirozc.forohub.domain.model.Topic;
import com.aquirozc.forohub.domain.model.User;
import com.aquirozc.forohub.domain.repo.TopicRepository;
import com.aquirozc.forohub.domain.repo.UserRepository;
import com.aquirozc.forohub.infra.TopicAlreadySubmitedException;
import com.aquirozc.forohub.infra.TopicNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TopicService {

    private TopicRepository topicRepo;
    private UserRepository userRepo;

    public TopicRepository getRepo(){
        return topicRepo;
    }

    public void deleteTopic(Topic topic){
        topicRepo.delete(topic);
    }

    public List<TopicGenericResponse> getAllTopics(){
        return topicRepo.findAll().stream().map(TopicGenericResponse::new).toList();
    }

    public TopicGenericResponse getTopic(long id){
        return new TopicGenericResponse(topicRepo.findById(id).orElseThrow(TopicNotFoundException::new));
    }

    public TopicGenericResponse submitNewTopic(TopicCreationRequest req, long userID){
        User user = userRepo.findById(userID).get();
        Topic topic = createTopicFromCreationRequest(req, user);

        try {
            topicRepo.save(topic);
        } catch (Exception e) {
            throw new TopicAlreadySubmitedException();
        }

        return new TopicGenericResponse(topic);
    }

    public TopicGenericResponse updateTopic(Topic topic, TopicCreationRequest t){
        topic.setTitle(t.getTitle());
        topic.setMessage(t.getMessage());
        topicRepo.save(topic);

        return new TopicGenericResponse(topic);
    }

    private Topic createTopicFromCreationRequest(TopicCreationRequest req, User user){
        Topic topic  = new Topic();
        topic.setTitle(req.getTitle());
        topic.setMessage(req.getMessage());
        topic.setCourse(req.getCourse());
        topic.setActive(true);
        topic.setDate(new Date(System.currentTimeMillis()));
        topic.setUser(user);
        return topic;
    }
    
}
