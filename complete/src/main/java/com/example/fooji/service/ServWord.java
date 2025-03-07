package com.example.fooji.service;

import com.example.fooji.entity.EntWord;
import com.example.fooji.repo.RepWord;
import org.springframework.stereotype.Service;

@Service
public class ServWord {
    private final RepWord repository;

    public ServWord(RepWord repository) {
        this.repository = repository;
    }

    public EntWord getRandomWord(){
        return repository.getRandomWord();
    }
}
