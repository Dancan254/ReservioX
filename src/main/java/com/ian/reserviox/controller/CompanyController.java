package com.ian.reserviox.controller;

import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.response.ApiResponse;
import com.ian.reserviox.service.auth.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/ad/{companyId}")
    public ResponseEntity<?> postAd(@PathVariable Long companyId, @ModelAttribute AdDto adDto) throws IOException {
        boolean success = companyService.postAd(companyId, adDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/ads/{companyId}")
    public ResponseEntity<?> getAllAds(@PathVariable Long companyId){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllAds(companyId));
    }

    @GetMapping("/ad/{companyId}")
    public ResponseEntity<?> getAdById(@PathVariable Long companyId){
        if (companyService.getAdById(companyId) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAdById(companyId));
    }

    @PutMapping("/ad/{companyId}")
    public ResponseEntity<?> updateAd(@PathVariable Long companyId, @Validated @ModelAttribute AdDto adDto) throws IOException {
        try {
            String result = companyService.updateAd(companyId, adDto);
            if ("Ad updated successfully".equals(result)) {
                return ResponseEntity.ok().body(new ApiResponse(true, "Ad updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Ad not found"));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error updating ad"));
        }
    }

    @DeleteMapping("/ad/{companyId}")
    public ResponseEntity<String> deleteAd(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.deleteAd(companyId));
    }
}
