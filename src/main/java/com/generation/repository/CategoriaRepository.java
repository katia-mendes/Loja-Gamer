package com.generation.Repository;
package com.generation.Controller;

import generation.model.Produto;
import generation.repository.CategoriaRepository;
import com.generation.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    public CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody @NotNull Produto produto) {
        if (produtoRepository.existsById(produto.getCategoria().getId())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoRepository.save(produto));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
    }

    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody @NotNull Produto produto) {
        if (produtoRepository.existsById(produto.getId())) {

            if (categoriaRepository.existsById(produto.getCategoria().getId())) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produto));
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @ResponseStatus (HttpStatus.NO_CONTENT)

    @DeleteMapping( "/{id} " )
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtoRepository.deleteById(id);
    }

}

