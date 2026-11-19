package globalsolution.citizentech.trilhamagica.model;

import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import jakarta.persistence.*;
import lombok.*;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_material")
public class MaterialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long materialId;

    @Column(nullable = false)
    private String nome; // Nome dos componentes cadastrados

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MateriaEnum materia;  // Química, Física ou Matemática

    @Column(columnDefinition = "TEXT", name = "enunciado_experimento")
    private String enunciadoDaExperiencia;  // Enunciado lúdico para a turma

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;  // Professor responsável pelo cadastro

    }



