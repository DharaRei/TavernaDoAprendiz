package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import globalsolution.citizentech.trilhamagica.model.MaterialModel;
import lombok.Data;

@Data
public class MaterialRespondeDto {

    private Long materialId;
    private String nome;
    private MateriaEnum materia;
    private String enunciadoDaExperiencia;
    private Long professorId;

    public MaterialRespondeDto(MaterialModel model) {
        this.materialId = model.getMaterialId();
        this.nome = model.getNome();
        this.materia = model.getMateria();
        this.enunciadoDaExperiencia = model.getEnunciadoDaExperiencia();

        if (model.getProfessor() != null) {
            this.professorId = model.getProfessor().getProfessorId();
        }
    }
}
