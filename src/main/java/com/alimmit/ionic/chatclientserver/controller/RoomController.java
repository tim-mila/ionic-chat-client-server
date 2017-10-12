package com.alimmit.ionic.chatclientserver.controller;

import com.alimmit.ionic.chatclientserver.entity.Room;
import com.alimmit.ionic.chatclientserver.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    private final static String PATH = Path.API_V1 + "/rooms";
    private final static String PATH_READ = RoomController.PATH + "/{id}";

    private final RoomRepository roomRepository;

    public RoomController(final RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping(RoomController.PATH)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Room create(@RequestBody final Room room) {
        return roomRepository.save(room);
    }

    @GetMapping(RoomController.PATH)
    public List<Room> all() {
        return roomRepository.findAll();
    }

    @GetMapping(RoomController.PATH_READ)
    public Room read(@PathVariable final Long id) {
        return roomRepository.findOne(id);
    }

}
