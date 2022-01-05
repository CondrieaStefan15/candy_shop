package ro.deloittedigital.bootcampbackend.services.implementation;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.deloittedigital.bootcampbackend.boundry.dto.RateDTO;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.NotFoundException;
import ro.deloittedigital.bootcampbackend.entity.model.Product;
import ro.deloittedigital.bootcampbackend.entity.model.Rate;
import ro.deloittedigital.bootcampbackend.entity.repository.ProductRepository;
import ro.deloittedigital.bootcampbackend.entity.repository.RateRepository;
import ro.deloittedigital.bootcampbackend.services.interfaces.RateService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RateServiceImpl implements RateService {

    private RateRepository rateRepository;
    private ProductRepository productRepository;

    @Override
    public Rate addRate(RateDTO rateDTO) {

        Optional<Product> productOptional = productRepository.findById(rateDTO.getProductId());

        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id: " + rateDTO.getProductId() + " not found in database");
        }

        Rate rate = new Rate(null, productOptional.get(), rateDTO.getRate());
        return rateRepository.save(rate);

    }

    public Rate updateRate(Long rateId, RateDTO rateDTO) {

        Optional<Product> productOptional = productRepository.findById(rateDTO.getProductId());
        Optional<Rate> rate = rateRepository.findById(rateId);

        if (rate.isEmpty()) {
            throw new NotFoundException("Rate with id: " + rateId + " not found in database");
        }
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id: " + rateDTO.getProductId() + " not found in database");
        }

        rate.get().setRate(rateDTO.getRate());
        return rateRepository.save(rate.get());

    }


    @Override
    public List<Rate> getAll() {
        return rateRepository.findAll();
    }


}
