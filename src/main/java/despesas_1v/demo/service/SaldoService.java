package despesas_1v.demo.service;

import despesas_1v.demo.repository.DespesaRepository;
import despesas_1v.demo.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class SaldoService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    public BigDecimal calcularSaldoPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        BigDecimal totalReceitas = receitaRepository.calcularTotalPorPeriodo(dataInicio, dataFim);
        BigDecimal totalDespesas = despesaRepository.calcularTotalPorPeriodo(dataInicio, dataFim);

        if (totalReceitas == null) totalReceitas = BigDecimal.ZERO;
        if (totalDespesas == null) totalDespesas = BigDecimal.ZERO;

        return totalReceitas.subtract(totalDespesas);
    }

    public BigDecimal calcularSaldoMesAtual() {
        YearMonth mesAtual = YearMonth.now();
        LocalDate primeiroDia = mesAtual.atDay(1);
        LocalDate ultimoDia = mesAtual.atEndOfMonth();

        return calcularSaldoPeriodo(primeiroDia, ultimoDia);
    }

    public BigDecimal calcularSaldoMes(int ano, int mes) {
        YearMonth yearMonth = YearMonth.of(ano, mes);
        LocalDate primeiroDia = yearMonth.atDay(1);
        LocalDate ultimoDia = yearMonth.atEndOfMonth();

        return calcularSaldoPeriodo(primeiroDia, ultimoDia);
    }

    public ResumoFinanceiro obterResumoFinanceiro(LocalDate dataInicio, LocalDate dataFim) {
        BigDecimal totalReceitas = receitaRepository.calcularTotalPorPeriodo(dataInicio, dataFim);
        BigDecimal totalDespesas = despesaRepository.calcularTotalPorPeriodo(dataInicio, dataFim);

        if (totalReceitas == null) totalReceitas = BigDecimal.ZERO;
        if (totalDespesas == null) totalDespesas = BigDecimal.ZERO;

        BigDecimal saldo = totalReceitas.subtract(totalDespesas);

        return new ResumoFinanceiro(totalReceitas, totalDespesas, saldo, dataInicio, dataFim);
    }

    public record ResumoFinanceiro(
            BigDecimal totalReceitas,
            BigDecimal totalDespesas,
            BigDecimal saldo,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {}
}