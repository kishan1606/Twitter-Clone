package com.cooksys.assessment1team3.repositories;

import com.cooksys.assessment1team3.entities.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findByLabel(String label);
}
