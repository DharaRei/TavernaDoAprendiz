package globalsolution.citizentech.trilhamagica.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_bem_estar")
public class BemEstarModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String meditar;  // Ex: "Meditação guiada de 5 minutos concluída"
    private String respirar; // Ex: "Exercício de respiração 4-7-8 realizado"
    private String alongar;  // Ex: "Série de alongamento de ombros e pescoço"

    @Column(name = "registro_data_hora", nullable = false)
    private LocalDateTime dataHoraRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;

}
