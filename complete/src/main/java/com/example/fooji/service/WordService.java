package com.example.fooji.service;

import com.example.fooji.entity.Word;
import com.example.fooji.repository.WordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private static final Logger log = LoggerFactory.getLogger(WordService.class);

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getRandomWords(int limit) {
        log.info("Fetching List by Limit: {}", limit);
        return wordRepository.findRandomWords(limit);
    }

    public Optional<Word> getById(Long id) {
        log.info("Fetching word by ID: {}", id);
        return wordRepository.findById(id);
    }

    public List<Word> getRandomWordsKanjiOnly(int limit) {
        log.info("findRandomWordsKanjiOnly by Limit: {}", limit);
        return wordRepository.findRandomWordsKanjiOnly(limit);
    }

    // For testing without DB (optional)
    /*public Optional<Word> mockWord(Long id) {
        Word ent = new Word();
        ent.setId(id);
        ent.setEnglish("test");
        ent.setHiragana("あさ");
        ent.setKanji("朝");
        return Optional.of(ent);
    }*/
}
