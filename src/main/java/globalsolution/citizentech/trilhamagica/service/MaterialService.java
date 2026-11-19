package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.dto.MaterialRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.MaterialRespondeDto;
import globalsolution.citizentech.trilhamagica.model.MaterialModel;
import globalsolution.citizentech.trilhamagica.model.ProfessorModel;
import globalsolution.citizentech.trilhamagica.repository.MaterialRepository;
import globalsolution.citizentech.trilhamagica.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<MaterialRespondeDto> listarTodos() {
        return materialRepository.findAll().stream()
                .map(MaterialRespondeDto::new)
                .collect(Collectors.toList());
    }

    public Optional<MaterialRespondeDto> buscarPorId(Long id) {
        return materialRepository.findById(id).map(MaterialRespondeDto::new);
    }

    public MaterialRespondeDto salvar(MaterialRecebeDto dto) {
        MaterialModel material = new MaterialModel();
        material.setNome(dto.getNome());
        material.setMateria(dto.getMateria());
        material.setEnunciadoDaExperiencia(dto.getEnunciadoDaExperiencia());

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            material.setProfessor(professor);
        }

        MaterialModel salvo = materialRepository.save(material);
        return new MaterialRespondeDto(salvo);
    }

    public MaterialRespondeDto atualizar(Long id, MaterialRecebeDto dto) {
        MaterialModel material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado com o ID: " + id));

        material.setNome(dto.getNome());
        material.setMateria(dto.getMateria());
        material.setEnunciadoDaExperiencia(dto.getEnunciadoDaExperiencia());

        if (dto.getProfessorId() != null) {
            ProfessorModel professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + dto.getProfessorId()));
            material.setProfessor(professor);
        } else {
            material.setProfessor(null);
        }

        MaterialModel atualizado = materialRepository.save(material);
        return new MaterialRespondeDto(atualizado);
    }

    public void deletar(Long id) {
        materialRepository.deleteById(id);
    }
}