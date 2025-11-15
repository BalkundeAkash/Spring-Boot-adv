package com.example.demo.controller;

import com.example.demo.dto.FloorDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.entity.Floor;
import com.example.demo.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
@RequiredArgsConstructor
public class FloorController {

    private final FloorService floorService;

    @GetMapping
    public List<FloorDTO> getAllFloors() {
        return floorService.getAllFloors();
    }

    @GetMapping("/{id}")
    public FloorDTO getFloorById(@PathVariable Long id) {
        return floorService.getFloorById(id);
    }

    @PostMapping
    public FloorDTO createFloor(@RequestBody Floor floor) {
        return floorService.saveFloor(floor);
    }

    @GetMapping("/{id}/vacant-rooms")
    public List<RoomDTO> getVacantRooms(@PathVariable Long id) {
        return floorService.getVacantRooms(id);
    }
}
