package ro.deloittedigital.bootcampbackend.boundry.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteBasketDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteBasketProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.ReadBasketProductDTO;
import ro.deloittedigital.bootcampbackend.entity.model.Basket;
import ro.deloittedigital.bootcampbackend.services.interfaces.BasketService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/baskets")
public class BasketController {

    private BasketService basketService;

    @PostMapping()
    ResponseEntity<?> createBasket() {

        Basket basket = basketService.createBasket();

        return ResponseEntity.ok(basket);

    }

    @GetMapping()
    public ResponseEntity<?> getAllProductsFromBasket(HttpSession session){

        String sessionId = session.getId();

        Set<ReadBasketProductDTO> list = basketService.getAllProductsFromBasket(sessionId);

        return ResponseEntity.ok().body(list);
    }



    @PatchMapping("/{basketId}")
    public ResponseEntity<?> updateProductFromBasket(@Valid @RequestBody WriteBasketProductDTO product, @PathVariable Long basketId, HttpSession session){

        String sessionId = session.getId();

        ReadBasketProductDTO basketProduct = basketService.updateBasket(product, basketId, sessionId);

        return new ResponseEntity<>(basketProduct, HttpStatus.OK);

    }
}