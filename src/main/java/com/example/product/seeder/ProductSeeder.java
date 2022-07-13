package com.example.product.seeder;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.util.NumberHelper;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

@Component
public class ProductSeeder implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;
    private static final int NUMBER_OF_PRODUCT = 100;

    public static ArrayList<Product> products;

    public void generate(){
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("black");
        colors.add("white");
        colors.add("pink");
        ArrayList<String> sizes = new ArrayList<>();
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Other");
        Random random = new Random();
        Faker faker = new Faker();
        products = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PRODUCT; i++) {
            Product obj = Product.builder()
                    .name(faker.name().name())
                    .description(faker.name().title())
                    .gender(genders.get(NumberHelper.numberRandom(0, genders.size()- 1)))
                    .color(colors.get(NumberHelper.numberRandom(0, colors.size() -1)))
                    .size(sizes.get(NumberHelper.numberRandom(0, sizes.size() -1)))
                    .price(NumberHelper.numberRandom(10000,100000))
                    .status(NumberHelper.numberRandom(0,1))
                    .createdAt(LocalDateTime.now().minusDays(NumberHelper.numberRandom(1,30)))
                    .build();
            products.add(obj);
        }
        productRepository.saveAll(products);

    }

    @Override
    public void run(String... args) throws Exception {
        generate();
    }
}

