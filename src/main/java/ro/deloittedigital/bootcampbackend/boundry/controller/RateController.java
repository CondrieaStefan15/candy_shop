package ro.deloittedigital.bootcampbackend.boundry.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.deloittedigital.bootcampbackend.boundry.dto.RateDTO;
import ro.deloittedigital.bootcampbackend.services.interfaces.RateService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/products/ratings")
public class RateController {

    private RateService rateService;

    @GetMapping
    public ResponseEntity<?> getAllRatings() {
        return new ResponseEntity<>(rateService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProductRating(@Valid @RequestBody RateDTO rateDTO) {
        return new ResponseEntity<>(rateService.addRate(rateDTO), HttpStatus.OK);
    }

    @PatchMapping("/{rateId}")
    public ResponseEntity<?> updateProductRating(@PathVariable(name = "rateId") Long rateId,
                                                 @Valid @RequestBody RateDTO rateDTO) {
        return new ResponseEntity<>(rateService.updateRate(rateId, rateDTO), HttpStatus.OK);
    }
}
