package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.MaterialRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.MaterialRespondeDto;
import globalsolution.citizentech.trilhamagica.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<MaterialRespondeDto>> listarTodos() {
        List<MaterialRespondeDto> lista = materialService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialRespondeDto> buscarPorId(@PathVariable Long id) {
        return materialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MaterialRespondeDto> salvar(@RequestBody MaterialRecebeDto dto) {
        MaterialRespondeDto novo = materialService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialRespondeDto> atualizar(@PathVariable Long id, @RequestBody MaterialRecebeDto dto) {
        try {
            MaterialRespondeDto atualizado = materialService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            materialService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
