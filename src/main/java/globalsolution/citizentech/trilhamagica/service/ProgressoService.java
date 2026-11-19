package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.dto.ProgressoRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.ProgressoRespondeDto;
import globalsolution.citizentech.trilhamagica.model.AlunoModel;
import globalsolution.citizentech.trilhamagica.model.ProgressoModel;
import globalsolution.citizentech.trilhamagica.repository.AlunoRepository;
import globalsolution.citizentech.trilhamagica.repository.ProgressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgressoService {

    @Autowired
    private ProgressoRepository progressoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<ProgressoRespondeDto> listarTodos() {
        return progressoRepository.findAll().stream()
                .map(ProgressoRespondeDto::new)
                .collect(Collectors.toList());
    }

    public Optional<ProgressoRespondeDto> buscarPorId(Long id) {
        return progressoRepository.findById(id).map(ProgressoRespondeDto::new);
    }

    public ProgressoRespondeDto salvar(ProgressoRecebeDto dto) {
        AlunoModel aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + dto.getAlunoId()));

        ProgressoModel progresso = new ProgressoModel();
        progresso.setProgressoAlunoId(aluno);
        progresso.setTurma(dto.getTurma());
        progresso.setProgressoAluno(dto.getProgressoAluno());

        ProgressoModel salvo = progressoRepository.save(progresso);
        return new ProgressoRespondeDto(salvo);
    }

    public ProgressoRespondeDto atualizar(Long id, ProgressoRecebeDto dto) {
        ProgressoModel progresso = progressoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progresso não encontrado com o ID: " + id));

        AlunoModel aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + dto.getAlunoId()));

        progresso.setProgressoAlunoId(aluno);
        progresso.setTurma(dto.getTurma());
        progresso.setProgressoAluno(dto.getProgressoAluno());

        ProgressoModel atualizado = progressoRepository.save(progresso);
        return new ProgressoRespondeDto(atualizado);
    }

    public void deletar(Long id) {
        progressoRepository.deleteById(id);
    }
}