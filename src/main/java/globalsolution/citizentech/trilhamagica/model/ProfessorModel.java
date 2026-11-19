package globalsolution.citizentech.trilhamagica.model;

import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import globalsolution.citizentech.trilhamagica.enuns.TurmaEnum;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_professor")
public class ProfessorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long professorId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    // Um professor pode cadastrar vários materiais/peças
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MaterialModel> materialCadastro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurmaEnum turma;

    @Column(nullable = false)
    private MateriaEnum materia;

    // Um professor tem vários registros de histórico de bem-estar
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private List<BemEstarModel> bemEstar;

}
