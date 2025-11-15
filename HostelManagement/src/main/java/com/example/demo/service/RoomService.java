package com.example.demo.service;

import com.example.demo.dto.RoomDTO;
import com.example.demo.dto.BedDTO;
import com.example.demo.entity.Room;
import com.example.demo.entity.Bed;
import com.example.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    // Get all rooms
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get room by ID
    public RoomDTO getRoomById(Long id) {
        return roomRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    // Save a new room
    public RoomDTO saveRoom(Room room) {
        Room saved = roomRepository.save(room);
        return convertToDTO(saved);
    }

    // Get all vacant rooms
    public List<RoomDTO> getVacantRooms() {
        return roomRepository.findAll()
                .stream()
                .filter(room -> room.getVacantBeds() > 0)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert Room entity to DTO
    private RoomDTO convertToDTO(Room room) {
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
