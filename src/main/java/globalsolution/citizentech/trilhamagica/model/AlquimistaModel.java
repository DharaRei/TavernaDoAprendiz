package globalsolution.citizentech.trilhamagica.model;


import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_alquimista")
public class AlquimistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_resultado", nullable = false)
    private String nomeResultado; // Ex: "Sabão Artesanal", "Perfume de Lavanda"

    @Column(name = "elemento_1", nullable = false)
    private String elemento1; // Ex: "H" (Hidrogênio)

    @Column(name = "elemento_2", nullable = false)
    private String elemento2; // Ex: "O" (Oxigênio)

    @Column(name = "composto_resultante", nullable = false)
    private String compostoResultante; // Ex: "H2O - Poção da Água" ou explosão

    @Column(columnDefinition = "TEXT")
    private String explicacaoCientifica; // O texto inclusivo/curiosidade sobre a reação

    @Column(name = "data_descoberta")
    private LocalDateTime dataDescoberta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    private AtividadeModel atividade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorModel professorCadastrou; // Garante que o professor é o dono do cadastro

}
