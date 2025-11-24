package despesas_1v.demo.controller;

import despesas_1v.demo.model.FormaRecebimento;
import despesas_1v.demo.service.FormaRecebimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/formas-recebimento")
public class FormaRecebimentoController {

    @Autowired
    private FormaRecebimentoService formaRecebimentoService;

    @GetMapping
    public ResponseEntity<List<FormaRecebimento>> listarTodas() {
        return ResponseEntity.ok(formaRecebimentoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaRecebimento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(formaRecebimentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FormaRecebimento> criar(@Valid @RequestBody FormaRecebimento formaRecebimento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formaRecebimentoService.criar(formaRecebimento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaRecebimento> atualizar(@PathVariable Integer id,
                                                      @Valid @RequestBody FormaRecebimento formaRecebimento) {
        return ResponseEntity.ok(formaRecebimentoService.atualizar(id, formaRecebimento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        formaRecebimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
