package com.alimmit.ionic.chatclientserver.repository;

import com.alimmit.ionic.chatclientserver.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByName(String name);
}
