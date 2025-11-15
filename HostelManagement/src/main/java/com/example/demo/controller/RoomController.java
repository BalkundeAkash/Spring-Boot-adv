package com.example.demo.controller;

import com.example.demo.dto.RoomDTO;
import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public RoomDTO createRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @GetMapping("/vacant")
    public List<RoomDTO> getVacantRooms() {
        return roomService.getVacantRooms();
    }
}
