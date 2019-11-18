package com.excersize.javaExcersize;

import com.excersize.javaExcersize.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppleTest {
    
    private List<Apple> inventory;
    
    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        inventory = mapper.readValue(FileUtils.loadTestSuiteResource("create_apple_inventory.json"),
            new TypeReference<List<Apple>>() {
            });
    }
    
    @Test
    void should_get_green_apples() {
        List<Apple> greenApples = filterGreenApples(inventory);
        
        assertEquals(9, greenApples.size());
    }
    
    @Test
    void should_get_heavy_apples() {
        List<Apple> heavyApples = filterHeavyApples(inventory);
    
        assertEquals(7, heavyApples.size());
    }
    
    @Test
    void should_get_green_apples_by_passing_function() {
        List<Apple> greenApples = filterApples(inventory, Apple::isGreen);
    
        assertEquals(9, greenApples.size());
    }
    
    @Test
    void should_get_heavy_apples_by_passing_function() {
        List<Apple> heavyApples = filterApples(inventory, Apple::isHeavy);
    
        assertEquals(7, heavyApples.size());
    }
    
    @Test
    void should_get_all_apples_which_color_is_green() {
        List<Apple> greenApples = inventory.stream().filter((Apple a) -> a.getColor().equals("green")).collect(toList());
        
        assertEquals(9, greenApples.size());
    }
    
    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
            inventory) {
            if (apple.getColor().equals("green")) {
                result.add(apple);
            }
        }
        return result;
    }
    
    private List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
            inventory) {
            if (apple.getWeight() > 100) {
                result.add(apple);
            }
        }
        return result;
    }
    
    private List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
            inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    
}
