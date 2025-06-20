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
    @Query(value = "SELECT * FROM vocabulary.voc_n5 ORDER BY random() LIMIT :limit", nativeQuery = true)
    List<Word> findRandomWords(@Param("limit") int limit);
}
