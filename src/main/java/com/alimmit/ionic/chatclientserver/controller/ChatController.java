package com.alimmit.ionic.chatclientserver.controller;

import com.alimmit.ionic.chatclientserver.entity.Chat;
import com.alimmit.ionic.chatclientserver.entity.Room;
import com.alimmit.ionic.chatclientserver.repository.ChatRepository;
import com.alimmit.ionic.chatclientserver.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private final static String CHATS = RoomController.ROOMS + "/{roomName}/chats";
    private final static String CHAT = RoomController.ROOMS + "/{roomName}/chat";

    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;

    public ChatController(final ChatRepository chatRepository, final RoomRepository roomRepository) {
        this.chatRepository = chatRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping(ChatController.CHATS)
    public List<Chat> list(@PathVariable final String roomName) {
        return chatRepository.findByRoomNameOrderByAtDesc(roomName);
    }

    @PostMapping(ChatController.CHAT)
    public Chat create(@PathVariable("roomName") final String roomName, @RequestBody final Chat chat) {
        final Room room = roomRepository.findByName(roomName);
        return chatRepository.save(chat.setRoom(room));
    }
}
