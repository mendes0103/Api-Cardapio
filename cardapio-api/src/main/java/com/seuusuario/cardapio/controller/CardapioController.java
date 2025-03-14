package com.seuusuario.cardapio.controller;

import com.seuusuario.cardapio.model.CardapioItem;
import com.seuusuario.cardapio.repository.CardapioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    private final CardapioRepository repository;

    public CardapioController(CardapioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<CardapioItem> criarItem(@RequestBody CardapioItem item) {
        return ResponseEntity.ok(repository.save(item));
    }

    @GetMapping
    public List<CardapioItem> listarItens() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioItem> buscarItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioItem> atualizarItem(@PathVariable Long id, @RequestBody CardapioItem itemAtualizado) {
        return repository.findById(id)
                .map(item -> {
                    item.setNome(itemAtualizado.getNome());
                    item.setDescricao(itemAtualizado.getDescricao());
                    item.setPreco(itemAtualizado.getPreco());
                    return ResponseEntity.ok(repository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
