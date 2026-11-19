package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.dto.AiRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AiService {

    private final RestClient restClient;

    @Value("${ai.api.key:}")
    private String apiKey;

    public AiService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
                .build();
    }

    public String adaptarConteudoPedagogico(AiRequestDTO request) {
        String prompt = String.format(
                "Aja como um assistente pedagógico inclusivo. Adapte a seguinte atividade escolar da matéria de %s para uma linguagem lúdica, simples e engajada para crianças: '%s'",
                request.materia(), request.textoOriginal()
        );

        String jsonBody = """
            {
              "contents": [{
                "parts": [{"text": "%s"}]
              }]
            }
            """.formatted(prompt.replace("\"", "\\\"").replace("\n", " "));

        try {
            String response = restClient.post()
                    .uri("?key=" + apiKey)
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                    .retrieve()
                    .body(String.class);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com o serviço de Inteligência Artificial: " + e.getMessage());
        }
    }
}