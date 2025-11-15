package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.HostelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostelService {

    private final HostelRepository hostelRepository;

    // Get all hostels as DTO
    public List<HostelDTO> getAllHostels() {
        return hostelRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Save a new hostel
    public HostelDTO saveHostel(Hostel hostel) {
        Hostel saved = hostelRepository.save(hostel);
        return convertToDTO(saved);
    }

    // Get hostel by id
    public HostelDTO getHostelById(Long id) {
        return hostelRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Hostel not found"));
    }

    // Convert Hostel entity to DTO
    private HostelDTO convertToDTO(Hostel hostel) {
        return HostelDTO.builder()
                .id(hostel.getId())
                .name(hostel.getName())
                .address(hostel.getAddress())
                .buildings(
                        hostel.getBuildings() != null ?
                        hostel.getBuildings().stream()
                                .map(this::convertBuildingToDTO)
                                .collect(Collectors.toList())
                        : null
                )
                .build();
    }

    private BuildingDTO convertBuildingToDTO(Building building) {
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
                .vacantBeds(room.getVacantBeds()) // calculated
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
