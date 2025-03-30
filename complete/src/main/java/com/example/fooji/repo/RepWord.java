package com.example.fooji.repo;

import com.example.fooji.entity.EntWord;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RepWord extends JpaRepository<EntWord, Long> {
    Optional<EntWord> getRandomWord();
}
