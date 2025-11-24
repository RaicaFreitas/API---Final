package despesas_1v.demo.repository;

import despesas_1v.demo.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

    List<Receita> findByDataReceitaBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Receita> findByCategoriaReceitaIdCategoriaReceita(Integer idCategoriaReceita);

    @Query("SELECT SUM(r.valor) FROM Receita r WHERE r.dataReceita BETWEEN :dataInicio AND :dataFim")
    BigDecimal calcularTotalPorPeriodo(@Param("dataInicio") LocalDate dataInicio,
                                       @Param("dataFim") LocalDate dataFim);
}