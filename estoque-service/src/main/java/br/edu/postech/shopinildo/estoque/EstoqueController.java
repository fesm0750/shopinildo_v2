package br.edu.postech.shopinildo.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;


    @GetMapping
    public List<Estoque> getAll(Pageable pageable) {
        return estoqueService.findAll(pageable);
    }

    @GetMapping("/{itemId}")
    public Estoque findByItemId(@PathVariable String itemId) {
        return estoqueService.findByItemId(itemId);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Void> update(@PathVariable String itemId, @RequestBody @Valid EstoqueDTO request) {
        estoqueService.save(itemId, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{itemId}/increase")
    public ResponseEntity<Void> increase(@PathVariable String itemId, @RequestBody @Valid EstoqueDTO dto) {
        estoqueService.increase(itemId, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{itemId}/decrease")
    public ResponseEntity<Void> decrease(@PathVariable String itemId, @RequestBody @Valid EstoqueDTO dto) {
        estoqueService.decrease(itemId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable String itemId) {
        estoqueService.delete(itemId);
        return ResponseEntity.noContent().build();
    }
}


