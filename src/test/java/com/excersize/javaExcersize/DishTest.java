package com.excersize.javaExcersize;

import com.excersize.javaExcersize.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
            .collect(Collectors.toList());
    
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
            .collect(Collectors.toList());
    
        assertEquals("[h, e, l, o, w, r, d]", uniqueCharacters.toString());
    }
}
