package com.alimmit.ionic.chatclientserver.entity;

import javax.persistence.PrePersist;
import java.util.Date;

public class ChatEntityListener {

    @PrePersist
    public void prePersist(final Chat chat) {
        chat.setAt(new Date());
    }
}
