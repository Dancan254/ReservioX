package com.ian.reserviox.controller;

import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.dto.ReservationDTO;
import com.ian.reserviox.service.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/ads")
    public ResponseEntity<List<AdDto>> getAllAds() {
        return ResponseEntity.ok(clientService.getAllAds());
    }
    @GetMapping("/ads/{adId}")
    public ResponseEntity<AdDto> getAdById(@PathVariable Long adId) {
        Optional<AdDto> adDto = clientService.getAdById(adId);
        return adDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/ads/search/{name}")
    public ResponseEntity<?> searchAdByName(@PathVariable String name) {
        List<AdDto> ads = clientService.searchAdByName(name);
        if (ads.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No advertisements found by the name: " + name);
        } else {
            return ResponseEntity.ok(ads);
        }
    }
    @GetMapping("/bookService")
    public ResponseEntity<String> bookService(@RequestBody ReservationDTO reservationDTO) {
        boolean isBooked = clientService.bookService(reservationDTO);
        if (isBooked) {
            return ResponseEntity.ok("Service booked successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to book service. Please try again.");
        }
    }
    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId) {
        return ResponseEntity.ok(clientService.getAdDetailsByAdId(adId));
    }
}
