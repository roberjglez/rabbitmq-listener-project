package com.rjgonzalez.rabbitmq.listener.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rjgonzalez.rabbitmq.listener.dto.ActorRQDTO;
import com.rjgonzalez.rabbitmq.listener.entity.ActorEntity;
import com.rjgonzalez.rabbitmq.listener.repository.ActorRepository;
import com.rjgonzalez.rabbitmq.listener.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {

	ModelMapper modelMapper;
	ActorRepository actorRepository;

	@Autowired
	public ActorServiceImpl(ActorRepository actorRepository, ModelMapper modelMapper) {
		this.actorRepository = actorRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<Void> createActor(ActorRQDTO actorRQDTO) {

		ActorEntity actorRQEntity = modelMapper.map(actorRQDTO, ActorEntity.class);

		actorRepository.save(actorRQEntity);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}

}
