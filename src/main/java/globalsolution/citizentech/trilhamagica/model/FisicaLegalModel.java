package globalsolution.citizentech.trilhamagica.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_fisica_legal")
public class FisicaLegalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_circuito", nullable = false)
    private String nomeCircuito; // Ex: "Circuito em Série com LED e Resistor"

    @Column(name = "sucesso")
    private boolean sucesso; // Se o aluno conseguiu fechar o circuito corretamente

    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    private AtividadeModel atividade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorModel professorCadastrou; // O professor que criou a peça personalizada

    @PrePersist
    public void prePersist() {
        this.dataRealizacao = LocalDateTime.now();
    }
}
