package ro.deloittedigital.bootcampbackend.services.implementation;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.deloittedigital.bootcampbackend.boundry.dto.ReadProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.BadRequestException;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.NotFoundException;
import ro.deloittedigital.bootcampbackend.entity.model.Product;
import ro.deloittedigital.bootcampbackend.entity.model.Rate;
import ro.deloittedigital.bootcampbackend.entity.repository.ProductRepository;
import ro.deloittedigital.bootcampbackend.services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReadProductDTO get(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NotFoundException("Product with id: {id} not found");
        }
        return modelMapper.map(product.get(), ReadProductDTO.class);
    }

    @Override
    public Double getProductRate(Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("There is no product[id=" + id + "]!");
        } else {
            double count = product.get().getRates().size();
            return count == 0 ? 0 : product.get().getRates().stream().map(Rate::getRate).reduce(0, Integer::sum) / count;
        }

    }

    @Override
    public Product updateProduct(Long productId, WriteProductDTO productDTO) {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("There is no product[id=" + productId + "]!");
        }
        Product product = optionalProduct.get();
        product.setTitle(productDTO.getTitle());

        product.setShortDescription(productDTO.getShortDescription());
        product.setLongDescription(productDTO.getLongDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setCurrency(productDTO.getCurrency());
        product.setPhotoUrl(productDTO.getPhotoUrl());
        product.setProductType(productDTO.getProductType());

        return productRepository.save(product);
    }

    @Override
    public List<ReadProductDTO> returnAllProductsAndPagination(Optional<Integer> page, Optional<Integer> size) {

        List<Product> list;
        if (page.isEmpty() && size.isEmpty()) {
            list = productRepository.findAll();
        } else if (page.isPresent()) {
            if (page.get() >= 0) {
                if (size.isPresent()) {
                    if (size.get() >= 1) {
                        list = getProductsWithPagination(page, size);
                    } else {
                        throw new BadRequestException("Size must be >=1 !");
                    }
                } else {
                    list = getProductsWithPagination(page, size);
                }
            } else {
                throw new BadRequestException("Page must be >=0 !");
            }

        } else {
            if (size.get() >= 1) {
                list = getProductsWithPagination(page, size);
            } else {
                throw new BadRequestException("Size must be >=1 !");
            }

        }
        List<ReadProductDTO> listDTO = new ArrayList<>();

        list.forEach(Product -> listDTO.add(modelMapper.map(Product, ReadProductDTO.class)));


        return listDTO;
    }


    private List<Product> getProductsWithPagination(Optional<Integer> page, Optional<Integer> size) {
        List<Product> list;
        Page<Product> pagesWithData = productRepository.findAll(PageRequest.of(
                page.orElse(0),
                size.orElse(5)));

        list = pagesWithData.getContent();

        return list;
    }


}
