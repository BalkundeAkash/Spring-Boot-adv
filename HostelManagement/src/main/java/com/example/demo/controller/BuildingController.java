package com.example.demo.controller;

import com.example.demo.dto.BuildingDTO;
import com.example.demo.entity.Building;
import com.example.demo.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping
    public List<BuildingDTO> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public BuildingDTO getBuildingById(@PathVariable Long id) {
        return buildingService.getBuildingById(id);
    }

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody Building building) {
        return buildingService.saveBuilding(building);
    }
}
