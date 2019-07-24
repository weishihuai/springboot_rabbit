package com.wsh.springboot.springboot_rabbitmq_message_idempotent.repository;

import com.wsh.springboot.springboot_rabbitmq_message_idempotent.entity.MessageIdempotent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageIdempotentRepository extends JpaRepository<MessageIdempotent, String> {


}
