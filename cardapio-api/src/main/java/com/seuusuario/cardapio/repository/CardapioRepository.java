package com.seuusuario.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seuusuario.cardapio.model.CardapioItem;

public interface CardapioRepository extends JpaRepository<CardapioItem, Long> {
}
