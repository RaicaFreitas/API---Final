package despesas_1v.demo.service;

import despesas_1v.demo.model.FormaRecebimento;
import despesas_1v.demo.repository.FormaRecebimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaRecebimentoService {

    @Autowired
    private FormaRecebimentoRepository formaRecebimentoRepository;

    public List<FormaRecebimento> listarTodas() {
        return formaRecebimentoRepository.findAll();
    }

    public FormaRecebimento buscarPorId(Integer id) {
        return formaRecebimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de recebimento não encontrada com ID: " + id));
    }

    public FormaRecebimento criar(FormaRecebimento formaRecebimento) {
        // SEM validação de nome duplicado
        return formaRecebimentoRepository.save(formaRecebimento);
    }

    public FormaRecebimento atualizar(Integer id, FormaRecebimento formaRecebimento) {
        FormaRecebimento formaExistente = buscarPorId(id);
        formaExistente.setNome(formaRecebimento.getNome());
        formaExistente.setDescricao(formaRecebimento.getDescricao());
        formaExistente.setAtivo(formaRecebimento.getAtivo());
        return formaRecebimentoRepository.save(formaExistente);
    }

    public void deletar(Integer id) {
        FormaRecebimento forma = buscarPorId(id);
        formaRecebimentoRepository.delete(forma);
    }
}