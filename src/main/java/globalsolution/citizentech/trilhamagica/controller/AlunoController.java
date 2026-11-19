package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.LoginDto;
import globalsolution.citizentech.trilhamagica.model.AlunoModel;
import globalsolution.citizentech.trilhamagica.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoModel>> listarTodos() {
        List<AlunoModel> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlunoModel> salvar(@RequestBody AlunoModel aluno) {
        AlunoModel novoAluno = alunoService.salvar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoModel> atualizar(@PathVariable Long id, @RequestBody AlunoModel aluno) {
        try {
            AlunoModel alunoAtualizado = alunoService.atualizar(id, aluno);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            alunoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint unificado e limpo para o Login do Aluno
    @PostMapping("/login")
    public ResponseEntity<AlunoModel> login(@RequestBody LoginDto loginDto) {
        try {
            AlunoModel alunoAutenticado = alunoService.validarLogin(loginDto.email(), loginDto.senha());
            return ResponseEntity.ok(alunoAutenticado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}