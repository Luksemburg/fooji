package com.example.fooji.repository;

import com.example.fooji.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    //Word findById(long id);
    // Get N random words using native SQL
    @Query(value = "SELECT * FROM public.jlpt_n5_vocab ORDER BY random() LIMIT :limit", nativeQuery = true)
    List<Word> findRandomWords(@Param("limit") int limit);

    @Query(value = "SELECT * FROM public.jlpt_n5_vocab v WHERE v.kanji <> v.hiragana ORDER BY random() LIMIT :limit",
            nativeQuery = true)
    List<Word> findRandomWordsKanjiOnly(@Param("limit") int limit);


    /*@Query(value = "SELECT * FROM :query", nativeQuery = true)
    List<Word> findRandomWordsByVocabulary(String query);

    @Query(value = "SELECT * FROM :query", nativeQuery = true)
    List<Word> findRandomWordsKanjiOnlyByVocabulary(String query);*/
}
