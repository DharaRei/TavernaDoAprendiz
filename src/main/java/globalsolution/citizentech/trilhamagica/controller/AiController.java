package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.dto.AiRequestDTO;
import globalsolution.citizentech.trilhamagica.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/adaptar")
    public ResponseEntity adaptarConteudo(
            @RequestParam("materia") String materia,
            @RequestParam("textoOriginal") String textoOriginal,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            AiRequestDTO request = new AiRequestDTO(textoOriginal, materia);
            String respostaIa = aiService.adaptarConteudoPedagogico(request);

            Map<String, String> resultado = new HashMap<>();
            resultado.put("respostaAdaptada", respostaIa);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("respostaAdaptada", "Erro ao processar com a Angel: " + e.getMessage());
            return ResponseEntity.status(500).body(erro);
        }
    }
}