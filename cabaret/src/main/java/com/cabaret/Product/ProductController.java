package com.cabaret.Product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    record NewUpdateRequest(
            String name,
            int amount,
            double price
    ){

    }
    @PostMapping("/")
    public  Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    @GetMapping
    public List<Product>productList(){
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable long id){
        return  productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product item not found"));
    }
    @PutMapping("{id}")
    public void updateProduct(
            @PathVariable("id") long id,
            @RequestBody ProductController.NewUpdateRequest request
    ){
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product item not found!"));

        existingProduct.setName(request.name());
        existingProduct.setAmount(request.amount());
        existingProduct.setPrice(request.price());
        productRepository.save(existingProduct);
    }
    @DeleteMapping("{id}")
    public  void  deleteProduct(
            @PathVariable("id") long id){
                productRepository.deleteById(id);
    }


}
