package globalsolution.citizentech.trilhamagica.enuns;

public enum AtipicoEnum {
        TEA("Transtorno do Espectro Autista"),
        TDAH("Transtorno do Déficit de Atenção com Hiperatividade"),
        DISLEXIA("Dislexia"),
        DEFICIENCIA_VISUAL("Deficiência Visual"),
        DEFICIENCIA_AUDITIVA("Deficiência Auditiva"),
        OUTROS("Outros / Personalizado");


        private final String descricao;

        AtipicoEnum (String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }


    }

