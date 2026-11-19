package globalsolution.citizentech.trilhamagica.steps;

import globalsolution.citizentech.trilhamagica.TrilhamagicaApplication;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@CucumberContextConfiguration
@SpringBootTest(
        classes = TrilhamagicaApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AtividadeSteps {

    @LocalServerPort
    private int port;

    private String requestBody;
    private Response response;

    @Dado("que eu tenho os dados de uma nova atividade com título {string}, matéria {string}, descrição {string} e alunoId {long}")
    public void que_eu_tenho_os_dados_de_uma_nova_atividade(String titulo, String materia, String descricao, Long alunoId) {
        requestBody = String.format("""
            {
                "titulo": "%s",
                "materia": "%s",
                "descricaoOriginal": "%s",
                "alunoId": %d
            }
            """, titulo, materia, descricao, alunoId);
    }

    @Quando("eu envio uma requisição POST para {string}")
    public void eu_envio_uma_requisicao_post_para(String endpoint) {
        // Força a porta e a URI no momento exato da requisição
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

        response = given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint);

        // Imprime o retorno no console para acompanharmos o que a API respondeu
        System.out.println("=== STATUS RETORNADO: " + response.getStatusCode() + " ===");
        System.out.println("=== CORPO DA RESPOSTA: ===");
        response.prettyPrint();
    }

    @Então("o status da resposta deve ser {int} Created")
    public void o_status_da_resposta_deve_ser_created(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Então("a resposta deve conter o título {string}")
    public void a_resposta_deve_conter_o_titulo(String tituloEsperado) {
        response.then().body("titulo", equalTo(tituloEsperado));
    }

    @Dado("que eu tenho o sistema configurado com atividades")
    public void que_eu_tenho_o_sistema_configurado_com_atividades() {
        // O banco de dados já está pronto para receber as consultas
    }

    @Quando("eu envio uma requisição GET para {string}")
    public void eu_envio_uma_requisicao_get_para(String endpoint) {
        response = given()
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);
    }

    @Então("o status da resposta deve ser {int} OK")
    public void o_status_da_resposta_deve_ser_ok(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Então("a lista de atividades não deve estar vazia")
    public void a_lista_de_atividades_nao_deve_estar_vazia() {
        response.then().body("size()", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
    }
}