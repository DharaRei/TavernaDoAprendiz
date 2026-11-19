package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaterialRecebeDto {

    @NotBlank(message = "O nome do material é obrigatório")
    private String nome;

    @NotNull(message = "A matéria é obrigatória")
    private MateriaEnum materia;

    private String enunciadoDaExperiencia;

    private Long professorId; // Opcional
}