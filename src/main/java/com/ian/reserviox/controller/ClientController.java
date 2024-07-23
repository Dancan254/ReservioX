package com.ian.reserviox.controller;

import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.service.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
