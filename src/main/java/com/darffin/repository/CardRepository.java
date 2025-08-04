package com.darffin.repository;
import com.darffin.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByName(String name);
}
