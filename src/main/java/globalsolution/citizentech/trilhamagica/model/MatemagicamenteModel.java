package globalsolution.citizentech.trilhamagica.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_matemagicamente")
public class MatemagicamenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "desafio", nullable = false)
    private String desafio; // Ex: "Soma com blocos lógicos: 5 + 3 = 8"

    @Column(name = "pontos_obtidos", nullable = false)
    private int pontosObtidos; // Pontuação lúdica conquistada pelo aluno

    @Column(name = "data_realizacao", nullable = false)
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
