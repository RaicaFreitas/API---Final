package despesas_1v.demo.repository;

import despesas_1v.demo.model.FormaRecebimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaRecebimentoRepository extends JpaRepository<FormaRecebimento, Integer> {
}