package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.AiRequestDTO;
import globalsolution.citizentech.trilhamagica.dto.AtividadeRecebeDto;
import globalsolution.citizentech.trilhamagica.dto.AtividadeRespondeDto;
import globalsolution.citizentech.trilhamagica.repository.AtividadeRepository;
import globalsolution.citizentech.trilhamagica.service.AiService;
import globalsolution.citizentech.trilhamagica.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;
    private final AiService aiService;
    private final AtividadeRepository atividadeRepository;

    public AtividadeController(AtividadeService atividadeService, AiService aiService, AtividadeRepository atividadeRepository) {
        this.atividadeService = atividadeService;
        this.aiService = aiService;
        this.atividadeRepository = atividadeRepository;
    }


    @GetMapping
    public ResponseEntity<List<AtividadeRespondeDto>> listarTodas() {
        List<AtividadeRespondeDto> atividades = atividadeService.listarTodas();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeRespondeDto> buscarPorId(@PathVariable Long id) {
        return atividadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtividadeRespondeDto> salvar(@RequestBody AtividadeRecebeDto dto) {
        AtividadeRespondeDto novaAtividade = atividadeService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAtividade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeRespondeDto> atualizar(@PathVariable Long id, @RequestBody AtividadeRecebeDto dto) {
        try {
            AtividadeRespondeDto atividadeAtualizada = atividadeService.atualizar(id, dto);
            return ResponseEntity.ok(atividadeAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            atividadeService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}/adaptar-ia")
    public ResponseEntity<String> adaptarAtividadeComIA(@PathVariable Long id) {
        globalsolution.citizentech.trilhamagica.model.AtividadeModel atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com o ID: " + id));

        AiRequestDTO dto = new AiRequestDTO(atividade.getDescricaoOriginal(), atividade.getMateria().name());
        String resultadoIA = aiService.adaptarConteudoPedagogico(dto);

        return ResponseEntity.ok(resultadoIA);
    }
}