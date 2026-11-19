package globalsolution.citizentech.trilhamagica.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_arquivos_nuvem")
public class ArquivoNuvemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeOriginal;

    @Column(length = 1000)
    private String urlAzure;

    private LocalDateTime dataUpload = LocalDateTime.now();

    // Construtores, Getters e Setters
    public ArquivoNuvemModel() {}

    public ArquivoNuvemModel(String nomeOriginal, String urlAzure) {
        this.nomeOriginal = nomeOriginal;
        this.urlAzure = urlAzure;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeOriginal() { return nomeOriginal; }
    public void setNomeOriginal(String nomeOriginal) { this.nomeOriginal = nomeOriginal; }

    public String getUrlAzure() { return urlAzure; }
    public void setUrlAzure(String urlAzure) { this.urlAzure = urlAzure; }

    public LocalDateTime getDataUpload() { return dataUpload; }
    public void setDataUpload(LocalDateTime dataUpload) { this.dataUpload = dataUpload; }
}