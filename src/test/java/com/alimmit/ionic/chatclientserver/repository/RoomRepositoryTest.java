package com.alimmit.ionic.chatclientserver.repository;

import com.alimmit.ionic.chatclientserver.ChatClientServerApplicationTests;
import com.alimmit.ionic.chatclientserver.entity.Room;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RoomRepositoryTest extends ChatClientServerApplicationTests {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void assertCRUD() throws Exception{

        Room room = roomRepository.save(Room.create("test-room"));
        assertNotNull(room);
        assertEquals(room, roomRepository.findOne(room.getId()));

        room = roomRepository.save(room.setName("test-room-update"));
        assertEquals(room, roomRepository.findOne(room.getId()));

        roomRepository.delete(room);
        assertNull(roomRepository.findOne(room.getId()));
    }
}