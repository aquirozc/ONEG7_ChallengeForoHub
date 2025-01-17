package com.aquirozc.forohub.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aquirozc.forohub.domain.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {


}
