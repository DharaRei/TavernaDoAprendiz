package globalsolution.citizentech.trilhamagica.dto;
import lombok.Data;

@Data
public class BemEstarRecebeDto {

    private String meditar;
    private String respirar;
    private String alongar;
    private Long professorId; // Opcional, caso queira vincular um professor
}
