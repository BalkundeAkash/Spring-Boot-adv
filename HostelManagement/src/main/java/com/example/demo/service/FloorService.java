package com.example.demo.service;

import com.example.demo.dto.FloorDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.dto.BedDTO;
import com.example.demo.entity.Floor;
import com.example.demo.entity.Room;
import com.example.demo.entity.Bed;
import com.example.demo.repository.FloorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FloorService {

    private final FloorRepository floorRepository;

    // Get all floors
    public List<FloorDTO> getAllFloors() {
        return floorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get floor by ID
    public FloorDTO getFloorById(Long id) {
        return floorRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Floor not found"));
    }

    // Save a new floor
    public FloorDTO saveFloor(Floor floor) {
        Floor saved = floorRepository.save(floor);
        return convertToDTO(saved);
    }

    // Convert Floor entity to DTO
    private FloorDTO convertToDTO(Floor floor) {
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

    // Get all vacant rooms in this floor
    public List<RoomDTO> getVacantRooms(Long floorId) {
        Floor floor = floorRepository.findById(floorId)
                .orElseThrow(() -> new RuntimeException("Floor not found"));

        return floor.getRooms().stream()
                .filter(room -> room.getVacantBeds() > 0)
                .map(this::convertRoomToDTO)
                .collect(Collectors.toList());
    }
}
