package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.LoginDto;
import globalsolution.citizentech.trilhamagica.model.ProfessorModel;
import globalsolution.citizentech.trilhamagica.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
@CrossOrigin(origins = "*")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorModel>> listarTodos() {
        List<ProfessorModel> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorModel> buscarPorId(@PathVariable Long id) {
        return professorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody LoginDto credenciais) {
        Optional<ProfessorModel> professorOpt = professorService.buscarPorEmail(credenciais.email());

        if (professorOpt.isPresent()) {
            ProfessorModel professor = professorOpt.get();

            // Valida a senha
            if (professor.getSenha().equals(credenciais.senha())) {
                return ResponseEntity.ok(professor); // Sucesso! Retorna os dados do professor
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha de professor inválidos.");
    } // <--- Chave que faltava fechar aqui

    @PostMapping
    public ResponseEntity<ProfessorModel> salvar(@RequestBody ProfessorModel professor) {
        ProfessorModel novoProfessor = professorService.salvar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorModel> atualizar(@PathVariable Long id, @RequestBody ProfessorModel professor) {
        try {
            ProfessorModel professorAtualizado = professorService.atualizar(id, professor);
            return ResponseEntity.ok(professorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            professorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar-senha")
    public ResponseEntity<?> atualizarSenha(@RequestBody LoginDto dadosNovos) {
        boolean atualizado = professorService.atualizarSenhaPorEmail(dadosNovos.email(), dadosNovos.senha());

        if (atualizado) {
            return ResponseEntity.ok("Senha mágica atualizada com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail não encontrado no reino.");
    }





}