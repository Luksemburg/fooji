package com.example.fooji.controller;

import com.example.fooji.entity.EntWord;
import com.example.fooji.repo.RepWord;
import com.example.fooji.service.ServWord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/words")
public class ConWord {
    private final ServWord service;

    public ConWord(ServWord service) {
        this.service = service;
    }

    @GetMapping
    public Optional<EntWord> getRandomWord() {
        return service.getRandomWord();
    }
}
