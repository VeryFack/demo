package com.example.demo;

import com.example.demo.service.CandyCrushLetterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    private CandyCrushLetterService candyCrushLetterService;

    @Test
    void testRemove() {
        candyCrushLetterService.removeCharacters("aabcccbbad");
    }

    @Test
    void testReplace() {
        candyCrushLetterService.replaceByPreviousLetter("aabcccbbad");
    }
}
