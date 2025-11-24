package despesas_1v.demo.service;

import despesas_1v.demo.exception.ResourceNotFoundException;
import despesas_1v.demo.model.Categoria;
import despesas_1v.demo.model.Despesa;
import despesas_1v.demo.model.FormaPagamento;
import despesas_1v.demo.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

    public Despesa buscarPorId(Integer id) {
        return despesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com ID: " + id));
    }

    public List<Despesa> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return despesaRepository.findByDataDespesaBetween(dataInicio, dataFim);
    }

    public List<Despesa> listarPorCategoria(Integer idCategoria) {
        return despesaRepository.findByCategoriaIdCategoria(idCategoria);
    }

    public BigDecimal calcularTotalPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        BigDecimal total = despesaRepository.calcularTotalPorPeriodo(dataInicio, dataFim);
        return total != null ? total : BigDecimal.ZERO;
    }

    public Despesa criar(Despesa despesa) {
        // CORREÇÃO: Busca e SETA os objetos completos
        Categoria categoria = categoriaService.buscarPorId(despesa.getCategoria().getIdCategoria());
        FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(despesa.getFormaPagamento().getIdFormaPagamento());

        despesa.setCategoria(categoria);
        despesa.setFormaPagamento(formaPagamento);

        return despesaRepository.save(despesa);
    }

    public Despesa atualizar(Integer id, Despesa despesaAtualizada) {
        Despesa despesa = buscarPorId(id);

        // CORREÇÃO: Busca os objetos completos e guarda nas variáveis
        Categoria categoria = categoriaService.buscarPorId(despesaAtualizada.getCategoria().getIdCategoria());
        FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(despesaAtualizada.getFormaPagamento().getIdFormaPagamento());

        despesa.setDescricao(despesaAtualizada.getDescricao());
        despesa.setValor(despesaAtualizada.getValor());
        despesa.setDataDespesa(despesaAtualizada.getDataDespesa());
        despesa.setObservacao(despesaAtualizada.getObservacao());
        despesa.setCategoria(categoria); // CORREÇÃO: Seta o objeto completo
        despesa.setFormaPagamento(formaPagamento); // CORREÇÃO: Seta o objeto completo

        return despesaRepository.save(despesa);
    }

    public void deletar(Integer id) {
        Despesa despesa = buscarPorId(id);
        despesaRepository.delete(despesa);
    }
}