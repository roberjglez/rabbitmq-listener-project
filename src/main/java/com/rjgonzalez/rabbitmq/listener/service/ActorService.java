package com.rjgonzalez.rabbitmq.listener.service;

import org.springframework.http.ResponseEntity;

import com.rjgonzalez.rabbitmq.listener.dto.ActorRQDTO;

public interface ActorService {

	public ResponseEntity<Void> createActor(ActorRQDTO actorRQDTO);

}
