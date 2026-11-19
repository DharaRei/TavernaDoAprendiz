package globalsolution.citizentech.trilhamagica.model;

import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_atividade")
public class AtividadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atividade_id")
    private Long atividadeId;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricaoOriginal; // Enunciado padrão

    @Column(columnDefinition = "TEXT", name = "conteudo_adaptado")
    private String conteudoAdaptado; // Dicas visuais personalizadas

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MateriaEnum materia;

    @Column(name = "concluida")
    private boolean concluida = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoModel aluno; // Relacionamento com o aluno

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlquimistaModel> alquimistas;

}

