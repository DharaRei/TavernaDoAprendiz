package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import globalsolution.citizentech.trilhamagica.model.AtividadeModel;
import lombok.Data;

@Data
public class AtividadeRespondeDto {

    private Long atividadeId;
    private String titulo;
    private String descricaoOriginal;
    private String conteudoAdaptado;
    private MateriaEnum materia;
    private boolean concluida;
    private Long alunoId;
    private String nomeAluno; // Facilita a leitura no front/Insomnia

    // Construtor que converte o Model em DTO automaticamente
    public AtividadeRespondeDto(AtividadeModel model) {
        this.atividadeId = model.getAtividadeId();
        this.titulo = model.getTitulo();
        this.descricaoOriginal = model.getDescricaoOriginal();
        this.conteudoAdaptado = model.getConteudoAdaptado();
        this.materia = model.getMateria();
        this.concluida = model.isConcluida();
        if (model.getAluno() != null) {
            this.alunoId = model.getAluno().getAlunoId();
            this.nomeAluno = model.getAluno().getNome(); // Supondo que Aluno tenha getNome()
        }
    }
}