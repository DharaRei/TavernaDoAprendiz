package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.model.ProfessorModel;
import globalsolution.citizentech.trilhamagica.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorModel> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<ProfessorModel> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public ProfessorModel salvar(ProfessorModel professor) {
        return professorRepository.save(professor);
    }

    public Optional<ProfessorModel> buscarPorEmail(String email) {
        List<ProfessorModel> professores = professorRepository.findByEmail(email); // Corrigido para usar a instância injetada
        return professores.isEmpty() ? Optional.empty() : Optional.of(professores.get(0));
    }

    public ProfessorModel atualizar(Long id, ProfessorModel professorAtualizado) {
        return professorRepository.findById(id).map(professor -> {
            professor.setNome(professorAtualizado.getNome());
            professor.setEmail(professorAtualizado.getEmail());
            professor.setSenha(professorAtualizado.getSenha()); // Adicionado para atualizar senha se necessário
            return professorRepository.save(professor);
        }).orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }


    public boolean atualizarSenhaPorEmail(String email, String novaSenha) {
        List<ProfessorModel> professores = professorRepository.findByEmail(email);

        if (!professores.isEmpty()) {
            ProfessorModel professor = professores.get(0);
            professor.setSenha(novaSenha);
            professorRepository.save(professor);
            return true; // Sucesso ao atualizar
        } else {
            return false; // E-mail não encontrado
        }
    }





}