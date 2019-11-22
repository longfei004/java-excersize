package com.excersize.javaExcersize;

import com.excersize.javaExcersize.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DishTest {
    
    private List<Dish> menu;
    
    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        menu = mapper.readValue(FileUtils.loadTestSuiteResource("create_dish_list.json"),
            new TypeReference<List<Dish>>() {
            });
    }
    
    @Test
    void should_get_all_meat_dish_name() {
        List<String> meat_menu = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getType() == Dish.Type.MEAT) {
                meat_menu.add(dish.getName());
            }
        }
    
        assertEquals("[pork, beef, chicken]", meat_menu.toString());
    }
    
    @Test
    void should_get_all_meat_dish_name_by_stream() {
        List<String> meat_menu = menu.stream()
            .filter(dish -> dish.getType() == Dish.Type.MEAT)
            .map(Dish::getName)
            .collect(toList());
        
        assertEquals("[pork, beef, chicken]", meat_menu.toString());
    }
    
    @Test
    void should_get_low_caloric_dishes_with_sorted() {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
    
        lowCaloricDishes.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }
        
        assertEquals("[season fruit, prawns, rice]", lowCaloricDishesName.toString());
    }
    
    @Test
    void should_get_low_caloric_dishes_with_sorted_by_stream() {
        List<String> lowCaloricDishesName = menu.stream()
            .filter(dish -> dish.getCalories() < 400)
            .sorted(comparing(Dish::getCalories))
            .map(Dish::getName)
            .collect(toList());
    
        assertEquals("[season fruit, prawns, rice]", lowCaloricDishesName.toString());
    }
    
    // flatMap的使用
    @Test
    void should_get_unique_characters_form_word_list() {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        
        List<String> uniqueCharacters = words.stream()
            .map(word -> word.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(toList());
        
        assertEquals("[h, e, l, o, w, r, d]", uniqueCharacters.toString());
    }
    
    @Test
    void should_get_all_pairs_of_two_list() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        
        List<String> pairs = num1.stream()
            .flatMap(i -> num2.stream()
                .map(j -> new Integer[]{i, j})
            )
            .map(Arrays::toString)
            .collect(toList());
        
        assertEquals("[[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]]", pairs.toString());
    }
}
