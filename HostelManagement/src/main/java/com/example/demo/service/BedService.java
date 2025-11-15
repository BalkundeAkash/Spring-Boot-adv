package com.example.demo.service;

import com.example.demo.dto.BedDTO;
import com.example.demo.entity.Bed;
import com.example.demo.repository.BedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BedService {

    private final BedRepository bedRepository;

    public List<BedDTO> getAllBeds() {
        return bedRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BedDTO getBedById(Long id) {
        return bedRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Bed not found"));
    }

    public BedDTO saveBed(Bed bed) {
        Bed saved = bedRepository.save(bed);
        return convertToDTO(saved);
    }

    public BedDTO occupyBed(Long bedId) {
        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() -> new RuntimeException("Bed not found"));
        bed.setOccupied(true);
        Bed updated = bedRepository.save(bed);
        return convertToDTO(updated);
    }

    public BedDTO vacateBed(Long bedId) {
        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() -> new RuntimeException("Bed not found"));
        bed.setOccupied(false);
        Bed updated = bedRepository.save(bed);
        return convertToDTO(updated);
    }

    public List<BedDTO> getVacantBeds() {
        return bedRepository.findAll()
                .stream()
                .filter(bed -> !bed.isOccupied())
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BedDTO convertToDTO(Bed bed) {
        return BedDTO.builder()
                .id(bed.getId())
                .bedNumber(bed.getBedNumber())
                .occupied(bed.isOccupied())
                .build();
    }
}
