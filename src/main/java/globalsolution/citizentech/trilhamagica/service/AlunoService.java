package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.model.AlunoModel;
import globalsolution.citizentech.trilhamagica.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoModel> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<AlunoModel> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public AlunoModel salvar(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    //buscar o aluno por email
    public Optional<AlunoModel> buscarPorEmail(String email) {
        List<AlunoModel> alunos = alunoRepository.findByEmail(email);
        return alunos.isEmpty() ? Optional.empty() : Optional.of(alunos.get(0));
    }

    public AlunoModel atualizar(Long id, AlunoModel alunoAtualizado) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setSenha(alunoAtualizado.getSenha());
            aluno.setAtipico(alunoAtualizado.getAtipico());
            aluno.setTurma(alunoAtualizado.getTurma());
            aluno.setProfessor(alunoAtualizado.getProfessor());
            return alunoRepository.save(aluno);
        }).orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    public AlunoModel validarLogin(String email, String senha) {
        Optional<AlunoModel> alunoOpt = buscarPorEmail(email);

        if (alunoOpt.isPresent()) {
            AlunoModel aluno = alunoOpt.get();
            // Valida se a senha bate
            if (aluno.getSenha().equals(senha)) {
                return aluno;
            }
        }
        throw new RuntimeException("E-mail ou senha inválidos!");
    }



}