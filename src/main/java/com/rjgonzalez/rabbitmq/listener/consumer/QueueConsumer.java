package com.rjgonzalez.rabbitmq.listener.consumer;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjgonzalez.rabbitmq.listener.dto.ActorRQDTO;
import com.rjgonzalez.rabbitmq.listener.service.ActorService;

@Component
public class QueueConsumer {

	ActorService actorService;
	ModelMapper modelMapper;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public QueueConsumer(ActorService actorService, ModelMapper modelMapper) {
		this.actorService = actorService;
		this.modelMapper = modelMapper;
	}

	public void receiveMessage(String message) {
		processMessage(message);
	}

	public void receiveMessage(byte[] message) {
		String strMessage = new String(message);
		processMessage(strMessage);
	}

	private void processMessage(String message) {

		ActorRQDTO actorRQDTO = new ActorRQDTO();
		try {
			actorRQDTO = new ObjectMapper().readValue(message, ActorRQDTO.class);
		} catch (JsonParseException e) {
			logger.warn("Bad JSON in message: " + message);
		} catch (JsonMappingException e) {
			logger.warn("cannot map JSON to NotificationRequest: " + message);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		actorService.createActor(actorRQDTO);
	}

}
