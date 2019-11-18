package com.excersize.javaExcersize;

import com.excersize.javaExcersize.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppleTest {
    
    private List<Apple> inventory;
    
    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        inventory = mapper.readValue(FileUtils.loadTestSuiteResource("create_apple_inventory.json"),
                new TypeReference<List<Apple>>(){});
    }
    
    @Test
    void should_get_all_apples_which_color_is_green() {
        List<Apple> greenApples = inventory.stream().filter((Apple a) -> a.getColor().equals("green")).collect(toList());
        
        assertEquals(9, greenApples.size());
    }
}
