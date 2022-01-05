package ro.deloittedigital.bootcampbackend.services.interfaces;

import ro.deloittedigital.bootcampbackend.boundry.dto.ReadProductDTO;
import ro.deloittedigital.bootcampbackend.boundry.dto.WriteProductDTO;
import ro.deloittedigital.bootcampbackend.entity.model.Product;


import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<ReadProductDTO> returnAllProductsAndPagination(Optional<Integer> page, Optional<Integer> size);

    ReadProductDTO get(Long id);

    Double getProductRate(Long id);

    Product updateProduct(Long productId, WriteProductDTO productDTO);

}
