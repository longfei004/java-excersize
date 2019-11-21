package com.excersize.javaExcersize;

import com.excersize.javaExcersize.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void should_get_all_meat_dish() {
        List<String> meat_menu = menu.stream()
            .filter(dish -> dish.getType() == Dish.Type.MEAT)
            .map(Dish::getName)
            .collect(toList());
    
        assertEquals("[pork, beef, chicken]", meat_menu.toString());
    }
    
    
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
                .map(j -> new int[]{i, j})
            )
            .map(Arrays::toString)
            .collect(toList());
    
        assertEquals("[[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]]", pairs.toString());
    }
}
