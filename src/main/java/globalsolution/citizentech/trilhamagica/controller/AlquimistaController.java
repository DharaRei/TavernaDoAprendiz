package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.model.AlquimistaModel;
import globalsolution.citizentech.trilhamagica.service.AlquimistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alquimistas")
public class AlquimistaController {

    @Autowired
    private AlquimistaService alquimistaService;

    @GetMapping
    public ResponseEntity<List<AlquimistaModel>> listarTodos() {
        List<AlquimistaModel> lista = alquimistaService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquimistaModel> buscarPorId(@PathVariable Long id) {
        return alquimistaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlquimistaModel> salvar(@RequestBody AlquimistaModel alquimista) {
        AlquimistaModel novo = alquimistaService.salvar(alquimista);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlquimistaModel> atualizar(@PathVariable Long id, @RequestBody AlquimistaModel alquimista) {
        try {
            AlquimistaModel atualizado = alquimistaService.atualizar(id, alquimista);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            alquimistaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}