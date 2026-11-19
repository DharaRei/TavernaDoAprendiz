package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.ProgressoRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.ProgressoRespondeDto;
import globalsolution.citizentech.trilhamagica.service.ProgressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progressos")
public class ProgressoController {

    @Autowired
    private ProgressoService progressoService;

    @GetMapping
    public ResponseEntity<List<ProgressoRespondeDto>> listarTodos() {
        List<ProgressoRespondeDto> lista = progressoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressoRespondeDto> buscarPorId(@PathVariable Long id) {
        return progressoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping
    public ResponseEntity<ProgressoRespondeDto> salvar(@RequestBody ProgressoRecebeDto dto) {
        ProgressoRespondeDto novo = progressoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgressoRespondeDto> atualizar(@PathVariable Long id, @RequestBody ProgressoRecebeDto dto) {
        try {
            ProgressoRespondeDto atualizado = progressoService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            progressoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
