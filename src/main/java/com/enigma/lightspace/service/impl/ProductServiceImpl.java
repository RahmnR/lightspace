package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Product;
import com.enigma.lightspace.entity.ProductPrice;
import com.enigma.lightspace.entity.Vendor;
import com.enigma.lightspace.model.request.ProductRequest;
import com.enigma.lightspace.model.response.ProductResponse;
import com.enigma.lightspace.repository.ProductRepository;
import com.enigma.lightspace.service.CategoryService;
import com.enigma.lightspace.service.ProductPriceService;
import com.enigma.lightspace.service.ProductService;
import com.enigma.lightspace.service.VendorService;
import com.enigma.lightspace.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ValidationUtil validationUtil;
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final CategoryService categoryService;
    private final VendorService vendorService;
    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductResponse create(ProductRequest request,Authentication authentication) {
        validationUtil.validate(request);
        Vendor vendor = vendorService.getByAuth(authentication);
        if (vendor.getName().isEmpty()||vendor.getName().isBlank())throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                "Profile Name is Required, Please Update your profile");

        System.out.println("Coba dini"+vendor.getName());
        Product product = Product.builder()
                .code(generateCode(request))
                .product(request.getName())
                .category(categoryService.getOrSave(request.getCategory()))
                .status(true)
                .build();
        productRepository.saveAndFlush(product);

        ProductPrice productPrice = ProductPrice.builder()
                .product(product)
                .price(request.getPrice())
                .stock(request.getStock())
                .vendor(vendor)
                .isActive(true)
                .build();
        productPriceService.save(productPrice);
        return toProductRespon(product,productPrice);
    }



    @Override
    public List<ProductResponse> createBulk(List<ProductRequest> products, Authentication authentication) {
        return products.stream().map(request -> this.create(request,authentication))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAll() {

        List<Product> collect = productRepository.findAll().stream()
                .filter(product -> product.getStatus().equals(true))
                .collect(Collectors.toList());

        return collect.stream().map(product -> {
            ProductPrice productPrice = productPriceService.productActive(product.getId(), true);
            return toProductRespon(product,productPrice);
        }).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getByCode(String code) {
        Product product = findOrThrow(code);
        ProductPrice productPrice = productPriceService.productActive(product.getId(), true);
        return toProductRespon(product,productPrice);
    }
    @Override
    public Product getProductByCode(String code) {
        return findOrThrow(code);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductResponse update(ProductRequest request) {
        Product product = findOrThrow(request.getProductCode());
        ProductPrice productPrice = productPriceService.productActive(product.getId(), true);
        product.setProduct(request.getName());
        productRepository.save(product);

        if (!productPrice.equals(request.getPrice())){

            productPrice.setIsActive(false);

            ProductPrice newPrice = ProductPrice.builder()
                    .price(request.getPrice())
                    .stock(request.getStock())
                    .vendor(productPrice.getVendor())
                    .isActive(true)
                    .build();
            productPriceService.save(newPrice);
            return toProductRespon(product,newPrice);
        }
        productPrice.setStock(request.getStock());
        return toProductRespon(product,productPrice);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteByCode(String code) {
        Product product = findOrThrow(code);
        product.setStatus(false);
        productRepository.save(product);
    }

    private Product findOrThrow(String code){
        return productRepository.findByCodeAndStatus(code, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
    }
    private String generateCode(ProductRequest request){
        String substring = request.getName().substring(0, 3).toUpperCase();
        int size = productRepository.findAll().size()+1;
        return substring+size;
    }
    private ProductResponse toProductRespon(Product product, ProductPrice productPrice){
        return ProductResponse.builder()
                .productId(product.getId())
                .productCode(product.getCode())
                .name(product.getProduct())
                .category(product.getCategory().getCategory())
                .stock(productPrice.getStock())
                .price(productPrice.getPrice())
                .vendor(productPrice.getVendor().getName())
                .build();
    }
}
