package globalsolution.citizentech.trilhamagica.service;

import com.azure.storage.blob.*;
import globalsolution.citizentech.trilhamagica.model.ArquivoNuvemModel;
import globalsolution.citizentech.trilhamagica.repository.ArquivoNuvemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class AzureBlobService {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    private final ArquivoNuvemRepository arquivoRepository;

    public AzureBlobService(ArquivoNuvemRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    public ArquivoNuvemModel uploadESalvar(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "_" + originalFilename;

        try {
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient();

            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
            if (!containerClient.exists()) {
                containerClient.create();
            }

            BlobClient blobClient = containerClient.getBlobClient(fileName);
            blobClient.upload(file.getInputStream(), file.getSize(), true);

            String urlPublica = blobClient.getBlobUrl();

            // Salva as informações e a URL no banco PostgreSQL usando a instância minúscula
            ArquivoNuvemModel arquivoModel = new ArquivoNuvemModel(originalFilename, urlPublica);
            return arquivoRepository.save(arquivoModel);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar arquivo para o Azure Blob Storage", e);
        }
    }
}
