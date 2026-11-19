package globalsolution.citizentech.trilhamagica.model;

import globalsolution.citizentech.trilhamagica.enuns.TurmaEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_progresso")
public class ProgressoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_progresso_aluno_por_id")
    private AlunoModel progressoAlunoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurmaEnum turma;

    @Column(name = "progresso_aluno", columnDefinition = "TEXT")
    private String progressoAluno;

}
