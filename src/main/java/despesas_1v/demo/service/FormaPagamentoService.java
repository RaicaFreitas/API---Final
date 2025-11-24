package despesas_1v.demo.service;

import despesas_1v.demo.exception.ResourceNotFoundException;
import despesas_1v.demo.model.FormaPagamento;
import despesas_1v.demo.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> listarTodas() {
        return formaPagamentoRepository.findAll();
    }

    public List<FormaPagamento> listarAtivas() {
        return formaPagamentoRepository.findByAtivoTrue();
    }

    public FormaPagamento buscarPorId(Integer id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forma de pagamento não encontrada com ID: " + id));
    }

    public FormaPagamento criar(FormaPagamento formaPagamento) {
        // REMOVIDO: validação de nome duplicado
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento atualizar(Integer id, FormaPagamento formaPagamentoAtualizada) {
        FormaPagamento formaPagamento = buscarPorId(id);

        // REMOVIDO: validação de nome duplicado
        formaPagamento.setNome(formaPagamentoAtualizada.getNome());
        formaPagamento.setDescricao(formaPagamentoAtualizada.getDescricao());
        formaPagamento.setAtivo(formaPagamentoAtualizada.getAtivo());

        return formaPagamentoRepository.save(formaPagamento);
    }

    public void deletar(Integer id) {
        FormaPagamento formaPagamento = buscarPorId(id);
        formaPagamentoRepository.delete(formaPagamento);
    }
}