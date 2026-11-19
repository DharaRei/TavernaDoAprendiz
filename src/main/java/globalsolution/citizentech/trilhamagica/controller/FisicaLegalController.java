package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.FisicaLegalRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.FisicaLegalRespondeDto;
import globalsolution.citizentech.trilhamagica.service.FisicaLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fisica-legal")
public class FisicaLegalController {

    @Autowired
    private FisicaLegalService fisicaLegalService;

    @GetMapping
    public ResponseEntity<List<FisicaLegalRespondeDto>> listarTodos() {
        List<FisicaLegalRespondeDto> lista = fisicaLegalService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FisicaLegalRespondeDto> buscarPorId(@PathVariable Long id) {
        return fisicaLegalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FisicaLegalRespondeDto> salvar(@RequestBody FisicaLegalRecebeDto dto) {
        FisicaLegalRespondeDto novo = fisicaLegalService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FisicaLegalRespondeDto> atualizar(@PathVariable Long id, @RequestBody FisicaLegalRecebeDto dto) {
        try {
            FisicaLegalRespondeDto atualizado = fisicaLegalService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            fisicaLegalService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}