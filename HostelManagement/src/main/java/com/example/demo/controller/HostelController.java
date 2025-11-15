package com.example.demo.controller;

import com.example.demo.dto.HostelDTO;
import com.example.demo.entity.Hostel;
import com.example.demo.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostels")
@RequiredArgsConstructor
public class HostelController {

    private final HostelService hostelService;

    @GetMapping
    public List<HostelDTO> getAllHostels() {
        return hostelService.getAllHostels();
    }

    @GetMapping("/{id}")
    public HostelDTO getHostelById(@PathVariable Long id) {
        return hostelService.getHostelById(id);
    }

    @PostMapping
    public HostelDTO createHostel(@RequestBody Hostel hostel) {
        return hostelService.saveHostel(hostel);
    }
}
