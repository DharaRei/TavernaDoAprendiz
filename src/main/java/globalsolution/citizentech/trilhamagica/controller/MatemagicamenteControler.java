package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.MatemagicamenteRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.MatemagicamenteRespondeDto;
import globalsolution.citizentech.trilhamagica.service.MatemagicamenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/matemagicamente")
public class MatemagicamenteControler {


    @Autowired
    private MatemagicamenteService matemagicamenteService;

    @GetMapping
    public ResponseEntity<List<MatemagicamenteRespondeDto>> listarTodos() {
        List<MatemagicamenteRespondeDto> lista = matemagicamenteService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatemagicamenteRespondeDto> buscarPorId(@PathVariable Long id) {
        return matemagicamenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MatemagicamenteRespondeDto> salvar(@RequestBody MatemagicamenteRecebeDto dto) {
        MatemagicamenteRespondeDto novo = matemagicamenteService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatemagicamenteRespondeDto> atualizar(@PathVariable Long id, @RequestBody MatemagicamenteRecebeDto dto) {
        try {
            MatemagicamenteRespondeDto atualizado = matemagicamenteService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            matemagicamenteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

