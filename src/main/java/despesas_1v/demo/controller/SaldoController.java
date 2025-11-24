package despesas_1v.demo.controller;

import despesas_1v.demo.service.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/saldo")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/periodo")
    public ResponseEntity<BigDecimal> calcularSaldoPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(saldoService.calcularSaldoPeriodo(dataInicio, dataFim));
    }

    @GetMapping("/mes-atual")
    public ResponseEntity<BigDecimal> calcularSaldoMesAtual() {
        return ResponseEntity.ok(saldoService.calcularSaldoMesAtual());
    }

    @GetMapping("/mes/{ano}/{mes}")
    public ResponseEntity<BigDecimal> calcularSaldoMes(
            @PathVariable int ano,
            @PathVariable int mes) {
        return ResponseEntity.ok(saldoService.calcularSaldoMes(ano, mes));
    }

    @GetMapping("/resumo")
    public ResponseEntity<SaldoService.ResumoFinanceiro> obterResumoFinanceiro(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(saldoService.obterResumoFinanceiro(dataInicio, dataFim));
    }
}