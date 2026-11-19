package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.dto.BemEstarRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.BemEstarRespondeDto;
import globalsolution.citizentech.trilhamagica.model.BemEstarModel;
import globalsolution.citizentech.trilhamagica.model.ProfessorModel;
import globalsolution.citizentech.trilhamagica.repository.BemEstarRepository;
import globalsolution.citizentech.trilhamagica.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BemEstarService {

    @Autowired
    private BemEstarRepository bemEstarRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<BemEstarRespondeDto> listarTodos() {
        return bemEstarRepository.findAll().stream()
                .map(BemEstarRespondeDto::new)
                .collect(Collectors.toList());
    }

    public Optional<BemEstarRespondeDto> buscarPorId(Long id) {
        return bemEstarRepository.findById(id).map(BemEstarRespondeDto::new);
    }

    public BemEstarRespondeDto salvar(BemEstarRecebeDto dto) {
        BemEstarModel bemEstar = new BemEstarModel();
        bemEstar.setMeditar(dto.getMeditar());
        bemEstar.setRespirar(dto.getRespirar());
        bemEstar.setAlongar(dto.getAlongar());
        bemEstar.setDataHoraRegistro(LocalDateTime.now());

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            bemEstar.setProfessor(professor);
        }

        BemEstarModel salvo = bemEstarRepository.save(bemEstar);
        return new BemEstarRespondeDto(salvo);
    }

    public BemEstarRespondeDto atualizar(Long id, BemEstarRecebeDto dto) {
        BemEstarModel bemEstar = bemEstarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de Bem-Estar não encontrado com o ID: " + id));

        bemEstar.setMeditar(dto.getMeditar());
        bemEstar.setRespirar(dto.getRespirar());
        bemEstar.setAlongar(dto.getAlongar());

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            bemEstar.setProfessor(professor);
        } else {
            bemEstar.setProfessor(null);
        }

        BemEstarModel atualizado = bemEstarRepository.save(bemEstar);
        return new BemEstarRespondeDto(atualizado);
    }

    public void deletar(Long id) {
        bemEstarRepository.deleteById(id);
    }
}