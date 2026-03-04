package com.movie.pricingservice.controller;

import com.movie.pricingservice.entity.Pricing;
import com.movie.pricingservice.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @GetMapping
    public List<Pricing> getAllPricing() {
        return pricingService.getAllPricing();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pricing> getPricingById(@PathVariable Long id) {
        return pricingService.getPricingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/movieName/{movieName}")
    public ResponseEntity<Pricing> getPricingByName(@PathVariable String movieName) {
        return pricingService.getPricingByName(movieName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cinemahall/{cinemahall}")
    public ResponseEntity<List<Pricing>> getPricingByCinemahall(@PathVariable String cinemahall) {
        return ResponseEntity.ok(pricingService.getPricingByCinemahall(cinemahall));
    }

    @GetMapping("/seatType/{seatType}")
    public ResponseEntity<List<Pricing>> getPricingBySeatType(@PathVariable String seatType) {
        return ResponseEntity.ok(pricingService.getPricingBySeatType(seatType));
    }

    @GetMapping("/dayType/{dayType}")
    public ResponseEntity<List<Pricing>> getPricingByDayType(@PathVariable String dayType) {
        return ResponseEntity.ok(pricingService.getPricingByDayType(dayType));
    }

    @PostMapping
    public Pricing createPricing(@RequestBody Pricing pricing) {
        return pricingService.createPricing(pricing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pricing> updatePricing(@PathVariable Long id, @RequestBody Pricing pricing) {
        try {
            return ResponseEntity.ok(pricingService.updatePricing(id, pricing));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePricing(@PathVariable Long id) {
        pricingService.deletePricing(id);
        return ResponseEntity.noContent().build();
    }
}
