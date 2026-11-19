package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.dto.AtividadeRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.AtividadeRespondeDto;
import globalsolution.citizentech.trilhamagica.model.AlunoModel;
import globalsolution.citizentech.trilhamagica.model.AtividadeModel;
import globalsolution.citizentech.trilhamagica.repository.AlunoRepository;
import globalsolution.citizentech.trilhamagica.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AtividadeRespondeDto> listarTodas() {
        return atividadeRepository.findAll().stream()
                .map(AtividadeRespondeDto::new)
                .collect(Collectors.toList());
    }

    public Optional<AtividadeRespondeDto> buscarPorId(Long id) {
        return atividadeRepository.findById(id).map(AtividadeRespondeDto::new);
    }

    public AtividadeRespondeDto salvar(AtividadeRecebeDto dto) {
        AlunoModel aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + dto.getAlunoId()));

        AtividadeModel atividade = new AtividadeModel();
        atividade.setTitulo(dto.getTitulo());
        atividade.setDescricaoOriginal(dto.getDescricaoOriginal());
        atividade.setConteudoAdaptado(dto.getConteudoAdaptado());
        atividade.setMateria(dto.getMateria());
        atividade.setConcluida(dto.isConcluida());
        atividade.setAluno(aluno);

        AtividadeModel salva = atividadeRepository.save(atividade);
        return new AtividadeRespondeDto(salva);
    }

    public AtividadeRespondeDto atualizar(Long id, AtividadeRecebeDto dto) {
        AtividadeModel atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com o ID: " + id));

        AlunoModel aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + dto.getAlunoId()));

        atividade.setTitulo(dto.getTitulo());
        atividade.setDescricaoOriginal(dto.getDescricaoOriginal());
        atividade.setConteudoAdaptado(dto.getConteudoAdaptado());
        atividade.setMateria(dto.getMateria());
        atividade.setConcluida(dto.isConcluida());
        atividade.setAluno(aluno);

        AtividadeModel atualizada = atividadeRepository.save(atividade);
        return new AtividadeRespondeDto(atualizada);
    }

    public void deletar(Long id) {
        atividadeRepository.deleteById(id);
    }
}