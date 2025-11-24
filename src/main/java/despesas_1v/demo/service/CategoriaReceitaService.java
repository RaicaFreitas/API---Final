package despesas_1v.demo.service;

import despesas_1v.demo.model.CategoriaReceita;
import despesas_1v.demo.repository.CategoriaReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaReceitaService {

    @Autowired
    private CategoriaReceitaRepository categoriaReceitaRepository;

    public List<CategoriaReceita> listarTodas() {
        return categoriaReceitaRepository.findAll();
    }

    public CategoriaReceita buscarPorId(Integer id) {
        return categoriaReceitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria de receita não encontrada com ID: " + id));
    }

    public CategoriaReceita criar(CategoriaReceita categoriaReceita) {
        // SEM validação de nome duplicado
        return categoriaReceitaRepository.save(categoriaReceita);
    }

    public CategoriaReceita atualizar(Integer id, CategoriaReceita categoriaReceita) {
        CategoriaReceita categoriaExistente = buscarPorId(id);
        categoriaExistente.setNome(categoriaReceita.getNome());
        categoriaExistente.setDescricao(categoriaReceita.getDescricao());
        categoriaExistente.setAtivo(categoriaReceita.getAtivo());
        return categoriaReceitaRepository.save(categoriaExistente);
    }

    public void deletar(Integer id) {
        CategoriaReceita categoria = buscarPorId(id);
        categoriaReceitaRepository.delete(categoria);
    }
}