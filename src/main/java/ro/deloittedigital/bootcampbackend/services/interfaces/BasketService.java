package ro.deloittedigital.bootcampbackend.services.interfaces;

import ro.deloittedigital.bootcampbackend.boundry.dto.WriteBasketProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.ReadBasketProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteBasketDTO;
import ro.deloittedigital.bootcampbackend.entity.model.Basket;

import java.util.Set;

public interface BasketService {

    Basket createBasket();

    Set<ReadBasketProductDTO> getAllProductsFromBasket(String session);

    ReadBasketProductDTO updateBasket(WriteBasketProductDTO product, Long basketId, String session);

}
