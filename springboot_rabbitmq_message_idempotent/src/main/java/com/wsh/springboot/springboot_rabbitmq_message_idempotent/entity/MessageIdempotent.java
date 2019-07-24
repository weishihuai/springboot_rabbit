package com.wsh.springboot.springboot_rabbitmq_message_idempotent.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message_idempotent")
public class MessageIdempotent implements Serializable {
    @Id
    @Column(name = "message_id")
    private String messageId;
    @Column(name = "message_content")
    private String messageContent;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
