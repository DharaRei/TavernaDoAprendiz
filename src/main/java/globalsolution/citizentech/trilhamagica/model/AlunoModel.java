package globalsolution.citizentech.trilhamagica.model;

import globalsolution.citizentech.trilhamagica.enuns.AtipicoEnum;
import globalsolution.citizentech.trilhamagica.enuns.TurmaEnum;
import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_aluno")
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Long alunoId;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AtipicoEnum atipico;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    // Qual turma o aluno pertence
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurmaEnum turma;

    // Relacionamento com o Professor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;

}
