package com.example.fooji.service;

import com.example.fooji.entity.Word;
import com.example.fooji.repository.WordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private static final Logger log = LoggerFactory.getLogger(WordService.class);

    private final WordRepository wordRepository;
    private final String[] levels = {"jlpt_n1_vocab", "jlpt_n2_vocab", "jlpt_n3_vocab", "jlpt_n4_vocab", "jlpt_n5_vocab"};

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Word> getRandomWordsByVocabulary(List<Boolean> vocabulary, int limit) {

        List<String> includedTables = new ArrayList<>();
        for (int i = 0; i < vocabulary.size(); i++) {
            if (vocabulary.get(i)) includedTables.add(levels[i]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("WITH vocab AS (");
        for (int i = 0; i < includedTables.size(); i++) {
            sb.append("SELECT * FROM ").append(includedTables.get(i));
            if (i < includedTables.size() - 1) {
                sb.append(" UNION ALL ");
            }

        }
        sb.append(")\n" +
                "SELECT id, hiragana, kanji, alternative,\n" +
                "       CASE\n" +
                "         WHEN rand_value < 0.5 OR alternative IS NULL THEN english\n" +
                "         ELSE alternative\n" +
                "       END AS english\n" +
                "FROM (\n" +
                "    SELECT *, RANDOM() AS rand_value\n" +
                "    FROM vocab) AS all_vocab ORDER BY random() LIMIT ").append(limit);
        log.info("----- query ----- {}", sb);

        //return wordRepository.findRandomWordsByVocabulary(sb.toString());
        return entityManager.createNativeQuery(sb.toString(), Word.class).getResultList();
    }

    public List<Word> getRandomWordsKanjiOnlyByVocabulary(List<Boolean> vocabulary, int limit) {

        List<String> includedTables = new ArrayList<>();
        for (int i = 0; i < vocabulary.size(); i++) {
            if (vocabulary.get(i)) includedTables.add(levels[i]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("WITH vocab AS (");
        for (int i = 0; i < includedTables.size(); i++) {
            sb.append("SELECT * FROM ").append(includedTables.get(i));
            if (i < includedTables.size() - 1) {
                sb.append(" UNION ALL ");
            }
            sb.append(" WHERE kanji <> hiragana ");
        }
        sb.append(")\n" +
                "SELECT id, hiragana, kanji, alternative,\n" +
                "       CASE\n" +
                "         WHEN rand_value < 0.5 OR alternative IS NULL THEN english\n" +
                "         ELSE alternative\n" +
                "       END AS english\n" +
                "FROM (\n" +
                "    SELECT *, RANDOM() AS rand_value\n" +
                "    FROM vocab) AS all_vocab ORDER BY random() LIMIT ").append(limit);
        log.info("----- query 2 ----- {}", sb);

        //return wordRepository.findRandomWordsKanjiOnlyByVocabulary(sb.toString());
        return entityManager.createNativeQuery(sb.toString(), Word.class).getResultList();
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
