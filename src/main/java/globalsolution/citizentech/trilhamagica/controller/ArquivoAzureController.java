package globalsolution.citizentech.trilhamagica.controller;

import globalsolution.citizentech.trilhamagica.model.ArquivoNuvemModel;
import globalsolution.citizentech.trilhamagica.service.AzureBlobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/arquivos")
public class ArquivoAzureController {

    private final AzureBlobService azureBlobService;

    public ArquivoAzureController(AzureBlobService azureBlobService) {
        this.azureBlobService = azureBlobService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ArquivoNuvemModel> uploadArquivo(@RequestParam("file") MultipartFile file) {

        ArquivoNuvemModel salvo = azureBlobService.uploadESalvar(file);
        return ResponseEntity.ok(salvo);
    }
}