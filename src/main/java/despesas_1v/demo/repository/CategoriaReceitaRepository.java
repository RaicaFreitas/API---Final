package despesas_1v.demo.repository;

import despesas_1v.demo.model.CategoriaReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaReceitaRepository extends JpaRepository<CategoriaReceita, Integer> {
}