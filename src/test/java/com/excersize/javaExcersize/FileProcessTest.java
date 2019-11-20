package com.excersize.javaExcersize;

import com.excersize.javaExcersize.utils.BufferedReaderProcessor;
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
    
    @Test
    void should_read_two_lines() throws IOException {
        String value = myProcessFile((BufferedReader br) -> br.readLine() + br.readLine());
        
        assertEquals("This is first line,This is second line.", value);
    }
    
    @Test
    void should_read_first_line_by_lambda() throws IOException {
        String value = myProcessFile((BufferedReader::readLine));
    
        assertEquals("This is first line,", value);
    }
    
    private String myProcessFile(BufferedReaderProcessor p) throws IOException {
        File file = new ClassPathResource("test_data.txt").getFile();
        BufferedReader br = Files.newBufferedReader(file.toPath());
        
        return p.process(br);
    }
    
    private String processFile() throws IOException {
        File file = new ClassPathResource("test_data.txt").getFile();
        BufferedReader br = Files.newBufferedReader(file.toPath());
        
        return br.readLine();
    }
}
