package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.dto.FisicaLegalRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.FisicaLegalRespondeDto;
import globalsolution.citizentech.trilhamagica.model.AtividadeModel;
import globalsolution.citizentech.trilhamagica.model.FisicaLegalModel;
import globalsolution.citizentech.trilhamagica.model.ProfessorModel;
import globalsolution.citizentech.trilhamagica.repository.AtividadeRepository;
import globalsolution.citizentech.trilhamagica.repository.FisicaLegalRepository;
import globalsolution.citizentech.trilhamagica.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FisicaLegalService {

    @Autowired
    private FisicaLegalRepository fisicaLegalRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<FisicaLegalRespondeDto> listarTodos() {
        return fisicaLegalRepository.findAll().stream()
                .map(FisicaLegalRespondeDto::new)
                .collect(Collectors.toList());
    }

    public Optional<FisicaLegalRespondeDto> buscarPorId(Long id) {
        return fisicaLegalRepository.findById(id).map(FisicaLegalRespondeDto::new);
    }

    public FisicaLegalRespondeDto salvar(FisicaLegalRecebeDto dto) {
        AtividadeModel atividade = atividadeRepository.findById(dto.getAtividadeId())
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com o ID: " + dto.getAtividadeId()));

        FisicaLegalModel fisica = new FisicaLegalModel();
        fisica.setNomeCircuito(dto.getNomeCircuito());
        fisica.setSucesso(dto.isSucesso());
        fisica.setAtividade(atividade);

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            fisica.setProfessorCadastrou(professor);
        }

        FisicaLegalModel salvo = fisicaLegalRepository.save(fisica);
        return new FisicaLegalRespondeDto(salvo);
    }

    public FisicaLegalRespondeDto atualizar(Long id, FisicaLegalRecebeDto dto) {
        FisicaLegalModel fisica = fisicaLegalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de Física Legal não encontrado com o ID: " + id));

        AtividadeModel atividade = atividadeRepository.findById(dto.getAtividadeId())
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com o ID: " + dto.getAtividadeId()));

        fisica.setNomeCircuito(dto.getNomeCircuito());
        fisica.setSucesso(dto.isSucesso());
        fisica.setAtividade(atividade);

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            fisica.setProfessorCadastrou(professor);
        } else {
            fisica.setProfessorCadastrou(null);
        }

        FisicaLegalModel atualizado = fisicaLegalRepository.save(fisica);
        return new FisicaLegalRespondeDto(atualizado);
    }

    public void deletar(Long id) {
        fisicaLegalRepository.deleteById(id);
    }
}