package despesas_1v.demo.controller;

import despesas_1v.demo.model.CategoriaReceita;
import despesas_1v.demo.service.CategoriaReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias-receita")
public class CategoriaReceitaController {

    @Autowired
    private CategoriaReceitaService categoriaReceitaService;

    @GetMapping
    public ResponseEntity<List<CategoriaReceita>> listarTodas() {
        return ResponseEntity.ok(categoriaReceitaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaReceita> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaReceitaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaReceita> criar(@Valid @RequestBody CategoriaReceita categoriaReceita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaReceitaService.criar(categoriaReceita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaReceita> atualizar(@PathVariable Integer id,
                                                      @Valid @RequestBody CategoriaReceita categoriaReceita) {
        return ResponseEntity.ok(categoriaReceitaService.atualizar(id, categoriaReceita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        categoriaReceitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}