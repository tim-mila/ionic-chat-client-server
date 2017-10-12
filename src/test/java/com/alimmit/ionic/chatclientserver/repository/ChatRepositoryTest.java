package com.alimmit.ionic.chatclientserver.repository;

import com.alimmit.ionic.chatclientserver.ChatClientServerApplicationTests;
import com.alimmit.ionic.chatclientserver.entity.Chat;
import com.alimmit.ionic.chatclientserver.entity.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ChatRepositoryTest extends ChatClientServerApplicationTests {

    private static final String ROOM_NAME = "chat-room";

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ChatRepository chatRepository;

    private Room room;

    @Before
    public void before() throws Exception {
        room = roomRepository.save(Room.create(ROOM_NAME));
    }

    @After
    public void after() throws Exception {
        roomRepository.delete(room);
    }

    @Test
    public void assertCRUD() throws Exception{

        Chat chat = chatRepository.save(new Chat().setRoom(room).setText("hello").setUsername("tim"));
        assertNotNull(chat);

        chat = chatRepository.save(chat.setText("hello world"));
        assertEquals("hello world", chat.getText());

        chatRepository.delete(chat);
    }

    @Test
    public void assertFindByRoom() throws Exception {

        List<Chat> chats = chatRepository.findByRoomNameOrderByAtDesc(ROOM_NAME);
        assertEquals(0, chats.size());

        Chat chat = chatRepository.save(new Chat().setRoom(room).setText("hello").setUsername("tim"));
        chats = chatRepository.findByRoomNameOrderByAtDesc(ROOM_NAME);
        assertEquals(1, chats.size());

        chatRepository.delete(chat);
    }
}