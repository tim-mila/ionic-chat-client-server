package com.alimmit.ionic.chatclientserver.repository;

import com.alimmit.ionic.chatclientserver.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByRoomNameOrderByAtDesc(String names);
}
