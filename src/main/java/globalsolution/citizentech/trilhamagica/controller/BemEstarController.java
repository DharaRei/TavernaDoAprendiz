package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.BemEstarRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.BemEstarRespondeDto;
import globalsolution.citizentech.trilhamagica.service.BemEstarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bem-estar")
public class BemEstarController {

    @Autowired
    private BemEstarService bemEstarService;

    @GetMapping
    public ResponseEntity<List<BemEstarRespondeDto>> listarTodos() {
        List<BemEstarRespondeDto> lista = bemEstarService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BemEstarRespondeDto> buscarPorId(@PathVariable Long id) {
        return bemEstarService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BemEstarRespondeDto> salvar(@RequestBody BemEstarRecebeDto dto) {
        BemEstarRespondeDto novo = bemEstarService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BemEstarRespondeDto> atualizar(@PathVariable Long id, @RequestBody BemEstarRecebeDto dto) {
        try {
            BemEstarRespondeDto atualizado = bemEstarService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            bemEstarService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
