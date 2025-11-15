package com.example.demo.controller;

import com.example.demo.dto.BedDTO;
import com.example.demo.entity.Bed;
import com.example.demo.service.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beds")
@RequiredArgsConstructor
public class BedController {

    private final BedService bedService;

    @GetMapping
    public List<BedDTO> getAllBeds() {
        return bedService.getAllBeds();
    }

    @GetMapping("/{id}")
    public BedDTO getBedById(@PathVariable Long id) {
        return bedService.getBedById(id);
    }

    @PostMapping
    public BedDTO createBed(@RequestBody Bed bed) {
        return bedService.saveBed(bed);
    }

    @PutMapping("/{id}/occupy")
    public BedDTO occupyBed(@PathVariable Long id) {
        return bedService.occupyBed(id);
    }

    @PutMapping("/{id}/vacate")
    public BedDTO vacateBed(@PathVariable Long id) {
        return bedService.vacateBed(id);
    }

    @GetMapping("/vacant")
    public List<BedDTO> getVacantBeds() {
        return bedService.getVacantBeds();
    }
}
