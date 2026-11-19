package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.dto.MatemagicamenteRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.MatemagicamenteRespondeDto;
import globalsolution.citizentech.trilhamagica.model.AtividadeModel;
import globalsolution.citizentech.trilhamagica.model.MatemagicamenteModel;
import globalsolution.citizentech.trilhamagica.model.ProfessorModel;
import globalsolution.citizentech.trilhamagica.repository.AtividadeRepository;
import globalsolution.citizentech.trilhamagica.repository.MatemagicamenteRepository;
import globalsolution.citizentech.trilhamagica.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatemagicamenteService {

    @Autowired
    private MatemagicamenteRepository matemagicamenteRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<MatemagicamenteRespondeDto> listarTodos() {
        return matemagicamenteRepository.findAll().stream()
                .map(MatemagicamenteRespondeDto::new)
                .collect(Collectors.toList());
    }

    public Optional<MatemagicamenteRespondeDto> buscarPorId(Long id) {
        return matemagicamenteRepository.findById(id).map(MatemagicamenteRespondeDto::new);
    }

    public MatemagicamenteRespondeDto salvar(MatemagicamenteRecebeDto dto) {
        AtividadeModel atividade = atividadeRepository.findById(dto.getAtividadeId())
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com o ID: " + dto.getAtividadeId()));

        MatemagicamenteModel matemagicamente = new MatemagicamenteModel();
        matemagicamente.setDesafio(dto.getDesafio());
        matemagicamente.setPontosObtidos(dto.getPontosObtidos());
        matemagicamente.setAtividade(atividade);

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            matemagicamente.setProfessorCadastrou(professor);
        }

        MatemagicamenteModel salvo = matemagicamenteRepository.save(matemagicamente);
        return new MatemagicamenteRespondeDto(salvo);
    }

    public MatemagicamenteRespondeDto atualizar(Long id, MatemagicamenteRecebeDto dto) {
        MatemagicamenteModel matemagicamente = matemagicamenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro Matemagicamente não encontrado com o ID: " + id));

        AtividadeModel atividade = atividadeRepository.findById(dto.getAtividadeId())
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com o ID: " + dto.getAtividadeId()));

        matemagicamente.setDesafio(dto.getDesafio());
        matemagicamente.setPontosObtidos(dto.getPontosObtidos());
        matemagicamente.setAtividade(atividade);

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            matemagicamente.setProfessorCadastrou(professor);
        } else {
            matemagicamente.setProfessorCadastrou(null);
        }

        MatemagicamenteModel atualizado = matemagicamenteRepository.save(matemagicamente);
        return new MatemagicamenteRespondeDto(atualizado);
    }

    public void deletar(Long id) {
        matemagicamenteRepository.deleteById(id);
    }
}