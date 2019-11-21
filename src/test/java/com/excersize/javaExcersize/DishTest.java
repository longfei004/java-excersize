package com.excersize.javaExcersize;

import com.excersize.javaExcersize.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DishTest {
    
    private List<Dish> dishes;
    
    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
    
        dishes = mapper.readValue(FileUtils.loadTestSuiteResource("create_dish_list.json"),
            new TypeReference<List<Dish>>() {
            });
    }
    
    @Test
    void should_get_all_meat() {
        List<Dish> meatDish = dishes.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).collect(Collectors.toList());
    
        for (Dish dish:meatDish) {
            assertSame(dish.getType(), Dish.Type.MEAT);
        }
    }
}
