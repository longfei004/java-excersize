package com.excersize.javaExcersize;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileProcessTest {
    
    @Test
    void should_read_first_line() throws IOException {
        String value = processFile();
    
        assertEquals("This is first line,", value);
    }
    
    private String processFile() throws IOException {
        File file = new ClassPathResource("test_data.txt").getFile();
        BufferedReader br = Files.newBufferedReader(file.toPath());
        return br.readLine();
    }
}
