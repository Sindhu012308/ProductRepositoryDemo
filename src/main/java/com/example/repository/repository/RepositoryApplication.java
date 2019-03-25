package com.example.repository.repository;

import com.example.repository.repository.model.Product;
import com.example.repository.repository.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RepositoryApplication implements CommandLineRunner {

    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger LOG = LoggerFactory.getLogger(RepositoryApplication.class);

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Product product1 = new Product();
        product1.setName("Tester Product");
        product1.setDescription("This is a tester product");
        product1.setCategory("TEST");
        product1.setType("GENERAL");
        product1.setPrice(0.0);
        //save product1
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Tester Product #2");
        product2.setDescription("This is a tester product");
        product2.setCategory("TEST");
        product2.setType("CUSTOM");
        product2.setPrice(15.0);
        //save product2
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Tester Product");
        product3.setDescription("This is a tester product");
        product3.setCategory("TEST");
        product3.setType("SPECIFIC");
        product3.setPrice(238.0);
        //save product3
        productRepository.save(product3);

        Product productFromRestTemplate = restTemplate.getForObject("http://localhost:8080/api/products/"+"402881896911f5e5016911f5e97a0000",Product.class);
    LOG.info("Product received with Rest Template: "+productFromRestTemplate.toString());
       /*
        //UPDATE OPERATION

        Product productToUpdate = productRepository.findByType("SPECIFIC");
        if(productToUpdate != null){
            LOG.info("Before Update product details: "+productToUpdate.toString());
            //String id =  productToUpdate.getId();
            productToUpdate.setName("Updated Product");
            productToUpdate.setDescription("Updated Description");

            Product updated = productRepository.save(productToUpdate);
            LOG.info("Updated product details: "+updated.toString());
        }
        else {
            LOG.info("Details of Product to Update not found");
        }

        //productRepository.delete(product3);

        Product foundProduct = productRepository.findByType("GENERAL");

        if(foundProduct != null){
            LOG.info("Product count before delete operation in database: "+productRepository.count());
            productRepository.delete(foundProduct);
            LOG.info("Product is deleted");
            LOG.info("Product count after delete operation in database: "+productRepository.count());
        }
        else{
            LOG.info("Product not found!");
        }

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            LOG.info("Products found:" + product.toString());
        }

        Product resultProd= productRepository.findByType("GENERAL");
            LOG.info("GENERAL type of Product found:" + resultProd.toString());

        List<Product> resultDesc = productRepository.findByDescriptionAndCategory("This is a tester product", "TEST");
        for (Product resDesc : resultDesc) {
            LOG.info("Description and Category found:" + resDesc.toString());
        }

        List<String> names = new ArrayList<>();
        names.add("Tester Product");
        //names.add("Tester Product #2");
        List<Product> resultProduct = productRepository.findByCategoryAndNameIn("TEST", names);
        for( Product product : resultProduct){
            LOG.info("Matching Result for findByCategoryAndNameIn: " + product.toString());
        }*/
    }
}
