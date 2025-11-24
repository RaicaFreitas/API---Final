package despesas_1v.demo.controller;

import despesas_1v.demo.model.Receita;
import despesas_1v.demo.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<Receita>> listarTodas() {
        return ResponseEntity.ok(receitaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Receita>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(receitaService.listarPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping("/categoria/{idCategoriaReceita}")
    public ResponseEntity<List<Receita>> listarPorCategoria(@PathVariable Integer idCategoriaReceita) {
        return ResponseEntity.ok(receitaService.listarPorCategoria(idCategoriaReceita));
    }

    @GetMapping("/total-periodo")
    public ResponseEntity<Map<String, Object>> calcularTotalPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        BigDecimal total = receitaService.calcularTotalPorPeriodo(dataInicio, dataFim);
        Map<String, Object> response = new HashMap<>();
        response.put("dataInicio", dataInicio);
        response.put("dataFim", dataFim);
        response.put("total", total);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Receita> criar(@Valid @RequestBody Receita receita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.criar(receita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Integer id,
                                             @Valid @RequestBody Receita receita) {
        return ResponseEntity.ok(receitaService.atualizar(id, receita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
