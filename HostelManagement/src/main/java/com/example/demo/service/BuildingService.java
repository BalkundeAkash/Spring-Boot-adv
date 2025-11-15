package com.example.demo.service;

import com.example.demo.dto.BuildingDTO;
import com.example.demo.dto.FloorDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.dto.BedDTO;
import com.example.demo.entity.Building;
import com.example.demo.entity.Floor;
import com.example.demo.entity.Room;
import com.example.demo.entity.Bed;
import com.example.demo.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;

    // Get all buildings
    public List<BuildingDTO> getAllBuildings() {
        return buildingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get building by ID
    public BuildingDTO getBuildingById(Long id) {
        return buildingRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Building not found"));
    }

    // Save a new building
    public BuildingDTO saveBuilding(Building building) {
        Building saved = buildingRepository.save(building);
        return convertToDTO(saved);
    }

    // Convert Building entity to DTO
    private BuildingDTO convertToDTO(Building building) {
        return BuildingDTO.builder()
                .id(building.getId())
                .name(building.getName())
                .floors(
                        building.getFloors() != null ?
                                building.getFloors().stream()
                                        .map(this::convertFloorToDTO)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }

    private FloorDTO convertFloorToDTO(Floor floor) {
        return FloorDTO.builder()
                .id(floor.getId())
                .name(floor.getName())
                .rooms(
                        floor.getRooms() != null ?
                                floor.getRooms().stream()
                                        .map(this::convertRoomToDTO)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }

    private RoomDTO convertRoomToDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .totalBeds(room.getTotalBeds())
                .vacantBeds(room.getVacantBeds()) // dynamically calculated
                .beds(
                        room.getBeds() != null ?
                                room.getBeds().stream()
                                        .map(this::convertBedToDTO)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }

    private BedDTO convertBedToDTO(Bed bed) {
        return BedDTO.builder()
                .id(bed.getId())
                .bedNumber(bed.getBedNumber())
                .occupied(bed.isOccupied())
                .build();
    }
}
