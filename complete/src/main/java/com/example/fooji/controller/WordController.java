package com.example.fooji.controller;

import com.example.fooji.entity.Word;
import com.example.fooji.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/words")
public class WordController {

    private static final Logger log = LoggerFactory.getLogger(WordController.class);

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    /*@GetMapping
    public Optional<EntWord> getRandomWord() {
        return service.getRandomWord();
    }*/

    @GetMapping
    public Optional<Word> getById(@RequestParam Long id) {
        log.info("======== ConWord ========");
        /*EntWord ent = new EntWord();
        ent.setEnglish("test");
        ent.setId(666L);
        ent.setHiragana("あさ");
        ent.setKanji("朝");
        return ent;*/
        return wordService.getById(id);
    }

    @GetMapping("/random")
    public List<Word> getRandomWords(@RequestParam(defaultValue = "4") int limit) {
        return wordService.getRandomWords(limit);
    }
}
