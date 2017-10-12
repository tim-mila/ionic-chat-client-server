package com.alimmit.ionic.chatclientserver.controller;

import com.alimmit.ionic.chatclientserver.entity.Room;
import com.alimmit.ionic.chatclientserver.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    public final static String ROOMS = Path.API_V1 + "/rooms";
    public final static String ROOMS_READ = RoomController.ROOMS + "/{id}";

    private final RoomRepository roomRepository;

    public RoomController(final RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping(RoomController.ROOMS)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Room create(@RequestBody final Room room) {
        return roomRepository.save(room);
    }

    @GetMapping(RoomController.ROOMS)
    public List<Room> all() {
        return roomRepository.findAll();
    }

    @GetMapping(RoomController.ROOMS_READ)
    public Room read(@PathVariable final Long id) {
        return roomRepository.findOne(id);
    }

}
