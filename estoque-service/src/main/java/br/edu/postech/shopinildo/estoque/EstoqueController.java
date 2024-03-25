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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.postech.shopinildo.estoque.request.EstoqueRequest;
import br.edu.postech.shopinildo.estoque.response.AvailabilityResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;


    @GetMapping
    public ResponseEntity<List<Estoque>> getAll(Pageable pageable) {
        return ResponseEntity.ok(estoqueService.findAll(pageable));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Estoque> findByItemId(@PathVariable String itemId) {
        return ResponseEntity.ok(estoqueService.findByItemId(itemId));
    }

    @GetMapping("/availability")
    public ResponseEntity<AvailabilityResponse> checkInventory(@RequestParam List<String> itemIds) {
        return ResponseEntity.ok(estoqueService.checkInventory(itemIds));
    }
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Void> update(@PathVariable String itemId, @RequestBody @Valid EstoqueRequest request) {
        estoqueService.save(itemId, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{itemId}/increase")
    public ResponseEntity<Void> increase(@PathVariable String itemId, @RequestBody @Valid EstoqueRequest dto) {
        estoqueService.increase(itemId, dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{itemId}/decrease")
    public ResponseEntity<Void> decrease(@PathVariable String itemId, @RequestBody @Valid EstoqueRequest dto) {
        estoqueService.decrease(itemId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable String itemId) {
        estoqueService.delete(itemId);
        return ResponseEntity.noContent().build();
    }
}


