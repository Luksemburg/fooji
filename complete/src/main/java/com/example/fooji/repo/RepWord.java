package com.example.fooji.repo;

import com.example.fooji.entity.EntWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepWord extends JpaRepository<EntWord, Long> {

    EntWord getRandomWord();
}
