package globalsolution.citizentech.trilhamagica.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TurmaEnum {
    TURMA_A("Ensino Médio - 1º Ano A"),
    TURMA_B("Ensino Médio - 2º Ano B"),
    TURMA_C("Ensino Médio - 3º Ano C"),
    FUNDAMENTAL_6("Ensino Fundamental - 6ª Série"),
    FUNDAMENTAL_7("Ensino Fundamental - 7ª Série"),
    FUNDAMENTAL_8("Ensino Fundamental - 8ª Série"),
    FUNDAMENTAL_9("Ensino Fundamental - 9ª Série");

    private final String descricao;

    TurmaEnum(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TurmaEnum fromValue(String valor) {
        if (valor == null) return null;


        for (TurmaEnum turma : TurmaEnum.values()) {
            if (turma.name().equalsIgnoreCase(valor) || turma.descricao.equalsIgnoreCase(valor)) {
                return turma;
            }
        }

        // Fallback antigo caso venha genérico
        if (valor.equalsIgnoreCase("EnsinoMedio")) return TURMA_A;
        if (valor.equalsIgnoreCase("EnsinoFundamental")) return FUNDAMENTAL_6;

        throw new IllegalArgumentException("Turma inválida: " + valor);
    }
}