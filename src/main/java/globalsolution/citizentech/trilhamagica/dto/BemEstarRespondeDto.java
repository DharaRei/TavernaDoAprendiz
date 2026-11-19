package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.model.BemEstarModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BemEstarRespondeDto {

    private Long id;
    private String meditar;
    private String respirar;
    private String alongar;
    private LocalDateTime dataHoraRegistro;
    private Long professorId;

    public BemEstarRespondeDto(BemEstarModel model) {
        this.id = model.getId();
        this.meditar = model.getMeditar();
        this.respirar = model.getRespirar();
        this.alongar = model.getAlongar();
        this.dataHoraRegistro = model.getDataHoraRegistro();

        if (model.getProfessor() != null) {
            this.professorId = model.getProfessor().getProfessorId();
        }
    }
}