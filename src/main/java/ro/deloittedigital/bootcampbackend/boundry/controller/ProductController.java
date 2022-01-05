package ro.deloittedigital.bootcampbackend.boundry.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.deloittedigital.bootcampbackend.boundry.dto.ReadProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteProductDTO;
import ro.deloittedigital.bootcampbackend.entity.model.Product;
import ro.deloittedigital.bootcampbackend.entity.model.Rate;

import ro.deloittedigital.bootcampbackend.services.interfaces.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    @GetMapping("/rates/{productId}")
    public ResponseEntity<?> getProductRateById(@PathVariable Long productId) {

        Double productRate = productService.getProductRate(productId);

        return new ResponseEntity<>(productRate, HttpStatus.OK);

    }


    @GetMapping("/{productId}")
    public ResponseEntity<ReadProductDTO> getProductById(@PathVariable(name = "productId") Long id) {

        ReadProductDTO productDTO = productService.get(id);

        return ResponseEntity.ok().body(productDTO);
    }


    @GetMapping()
    public ResponseEntity<?> returnAllProducts(@RequestParam Optional<Integer> page,
                                               @RequestParam Optional<Integer> size) {

        List<ReadProductDTO> list = productService.returnAllProductsAndPagination(page, size);


        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PatchMapping("/{productId}")
    ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody WriteProductDTO productDTO) {

        Product product = productService.updateProduct(productId, productDTO);

        return ResponseEntity.ok(product);

    }

}

