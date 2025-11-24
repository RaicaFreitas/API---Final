package despesas_1v.demo.service;

import despesas_1v.demo.model.Receita;
import despesas_1v.demo.repository.ReceitaRepository;
import despesas_1v.demo.repository.CategoriaReceitaRepository;
import despesas_1v.demo.repository.FormaRecebimentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CategoriaReceitaRepository categoriaReceitaRepository;

    @Autowired
    private FormaRecebimentoRepository formaRecebimentoRepository;

    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }

    public Receita buscarPorId(Integer id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada com ID: " + id));
    }

    public List<Receita> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return receitaRepository.findByDataReceitaBetween(dataInicio, dataFim);
    }

    public List<Receita> listarPorCategoria(Integer idCategoriaReceita) {
        return receitaRepository.findByCategoriaReceitaIdCategoriaReceita(idCategoriaReceita);
    }

    public BigDecimal calcularTotalPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        BigDecimal total = receitaRepository.calcularTotalPorPeriodo(dataInicio, dataFim);
        return total != null ? total : BigDecimal.ZERO;
    }

    // ------------------------------------------------------------
    //                     CRIAR RECEITA
    // ------------------------------------------------------------
    public Receita criar(Receita receita) {

        // Buscar categoria completa pelo ID enviado no JSON
        var categoria = categoriaReceitaRepository.findById(
                receita.getCategoriaReceita().getIdCategoriaReceita()
        ).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        // Buscar forma completa
        var forma = formaRecebimentoRepository.findById(
                receita.getFormaRecebimento().getIdFormaRecebimento()
        ).orElseThrow(() -> new RuntimeException("Forma de recebimento não encontrada"));

        // Substituir pelos objetos completos
        receita.setCategoriaReceita(categoria);
        receita.setFormaRecebimento(forma);

        return receitaRepository.save(receita);
    }

    // ------------------------------------------------------------
    //                     ATUALIZAR RECEITA
    // ------------------------------------------------------------
    public Receita atualizar(Integer id, Receita receita) {

        Receita receitaExistente = buscarPorId(id);

        receitaExistente.setDescricao(receita.getDescricao());
        receitaExistente.setValor(receita.getValor());
        receitaExistente.setDataReceita(receita.getDataReceita());
        receitaExistente.setObservacao(receita.getObservacao());
        receitaExistente.setRecorrente(receita.getRecorrente());

        // Buscar categoria
        var categoria = categoriaReceitaRepository.findById(
                receita.getCategoriaReceita().getIdCategoriaReceita()
        ).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        // Buscar forma
        var forma = formaRecebimentoRepository.findById(
                receita.getFormaRecebimento().getIdFormaRecebimento()
        ).orElseThrow(() -> new RuntimeException("Forma de recebimento não encontrada"));

        receitaExistente.setCategoriaReceita(categoria);
        receitaExistente.setFormaRecebimento(forma);

        return receitaRepository.save(receitaExistente);
    }

    // ------------------------------------------------------------
    //                     DELETAR RECEITA
    // ------------------------------------------------------------
    public void deletar(Integer id) {
        Receita receita = buscarPorId(id);
        receitaRepository.delete(receita);
    }
}
