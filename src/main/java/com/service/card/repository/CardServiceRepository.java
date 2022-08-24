package com.service.card.repository;

import com.service.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardServiceRepository extends JpaRepository<Card, Long> {
}
