package com.example.fooji.controller;

import com.example.fooji.entity.EntWord;
import com.example.fooji.service.ServWord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ConWord {
    private final ServWord service;

    public ConWord(ServWord service) {
        this.service = service;
    }

    @GetMapping
    public EntWord getRandomWord() {
        return service.getRandomWord();
    }
}




public class UserController {





}
