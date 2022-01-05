package ro.deloittedigital.bootcampbackend.services.implementation;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteBasketProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.ReadBasketProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteBasketDTO;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.AlreadyExistsException;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.BadRequestException;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.NotFoundException;
import ro.deloittedigital.bootcampbackend.entity.model.Basket;
import ro.deloittedigital.bootcampbackend.entity.model.BasketProduct;
import ro.deloittedigital.bootcampbackend.entity.model.Product;
import ro.deloittedigital.bootcampbackend.entity.repository.BasketProductRepository;
import ro.deloittedigital.bootcampbackend.entity.repository.BasketRepository;
import ro.deloittedigital.bootcampbackend.entity.repository.ProductRepository;
import ro.deloittedigital.bootcampbackend.services.interfaces.BasketService;

import java.math.BigDecimal;
import java.util.*;


@AllArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepository;
    private BasketProductRepository basketProductRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;



    @Override
    public Basket createBasket() {

        var sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        var optionalBasket = basketRepository.findBySessionId(sessionId);
        if (optionalBasket.isPresent()) {
            throw new AlreadyExistsException("Basket[sessionId=" + sessionId + "] already exists!");
        }
        var basketDTO = new WriteBasketDTO(sessionId);

        return basketRepository.save(modelMapper.map(basketDTO, Basket.class));

    }

    @Override
    public Set<ReadBasketProductDTO> getAllProductsFromBasket(String session) {
        Set<ReadBasketProductDTO> readListDTO = new HashSet<>();
        Set<BasketProduct> basketProducts = new HashSet<>();

        Optional<Basket> basket = basketRepository.findBySessionId(session);
        if (basket.isEmpty()){
            return readListDTO;
        }else{
            basketProducts = basket.get().getProducts();
            basketProducts.forEach(BasketProduct -> readListDTO.add(converToReadBasketProductDTO(BasketProduct)));
            return readListDTO;
        }


    }


    @Override
    public ReadBasketProductDTO updateBasket(WriteBasketProductDTO p, Long basketId, String session) {

        if (p.getProductId() == null || p.getProductId() <0 ){
            throw new BadRequestException("Pruduct ID is required and must be >=1!");
        }

        if (p.getQuantity() == null || p.getQuantity() < 0) {
            throw new BadRequestException("Quantity is required and must be >= 0 !");
        }

        Optional<Product> product = productRepository.findById(p.getProductId());
        if (product.isEmpty()) {
            throw new NotFoundException("The product with id " + p.getProductId() + " does not exist! ");
        }


        BasketProduct basketProduct = modelMapper.map(p, BasketProduct.class);
        basketProduct.setProduct(product.get());
        basketProduct.setPrice(product.get().getPrice());
        basketProduct.setTotal_price(basketProduct.getPrice().multiply(new BigDecimal(basketProduct.getQuantity())));


        Optional<Basket> basket = basketRepository.findBySessionId(session);
        Optional<BasketProduct> productExistInBasket=Optional.empty();
        Long prodIdInBasket = -1L;
        if (basket.isPresent()) {
            for (BasketProduct bp : basket.get().getProducts()) {
                if (bp.getProduct().getId().longValue() == p.getProductId().longValue()) {
                    prodIdInBasket = bp.getId();
                    if(p.getQuantity() == 0){
                        basket.get().getProducts().remove(bp);
                        basketProductRepository.deleteById(prodIdInBasket);
                        bp.setQuantity(0L);
                        bp.setTotal_price(new BigDecimal(0));
                        return converToReadBasketProductDTO(bp);
                    }
                }
            }
            if (prodIdInBasket <= 0) {

                basket.get().getProducts().add(basketProduct);
                basketRepository.save(basket.get());
                return converToReadBasketProductDTO(basketProduct);

            } else {
                productExistInBasket = basketProductRepository.findById(prodIdInBasket);
                if (productExistInBasket.isPresent()) {
                    productExistInBasket.get().setQuantity(p.getQuantity());
                    productExistInBasket.get().setTotal_price(productExistInBasket.get().getPrice().multiply(new BigDecimal(p.getQuantity())));
                    basketProductRepository.save(productExistInBasket.get());
                } else {
                    throw new NotFoundException("The product with id " + prodIdInBasket + " does not exist in database! ");
                }
            }
        }else{
            Basket basket1 = createBasket();
            basket1.getProducts().add(basketProduct);
            basketRepository.save(basket1);
            return converToReadBasketProductDTO(basketProduct);
        }

        return converToReadBasketProductDTO(productExistInBasket.get());
    }


    private ReadBasketProductDTO converToReadBasketProductDTO(BasketProduct product){
        return ReadBasketProductDTO.builder()
                .price(product.getPrice())
                .total_price(product.getTotal_price())
                .quantity(product.getQuantity())
                .title(product.getProduct().getTitle())
                .build();
    }




}
