package despesas_1v.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "receita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receita")
    private Integer idReceita;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull(message = "Data da receita é obrigatória")
    @Column(name = "data_receita", nullable = false)
    private LocalDate dataReceita;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne(fetch = FetchType.EAGER) // ALTERADO: EAGER para carregar dados
    @JoinColumn(name = "id_categoria_receita", nullable = false)
    @NotNull(message = "Categoria é obrigatória")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CategoriaReceita categoriaReceita;

    @ManyToOne(fetch = FetchType.EAGER) // ALTERADO: EAGER para carregar dados
    @JoinColumn(name = "id_forma_recebimento", nullable = false)
    @NotNull(message = "Forma de recebimento é obrigatória")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FormaRecebimento formaRecebimento;

    @Column(name = "recorrente")
    private Boolean recorrente = false;

    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}