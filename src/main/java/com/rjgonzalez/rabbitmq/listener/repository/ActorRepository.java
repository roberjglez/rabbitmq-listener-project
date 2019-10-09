package com.rjgonzalez.rabbitmq.listener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rjgonzalez.rabbitmq.listener.entity.ActorEntity;

/**
 * Actor Repository
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

}
