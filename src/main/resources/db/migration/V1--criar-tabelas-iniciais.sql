-- 1. Tabela de Professores
CREATE TABLE tb_professor {
          professor_id BIGSERIAL PRIMARY KEY,
          nome VARCHAR(255) NOT NULL,
          email VARCHAR(255) NOT NULL UNIQUE,
          senha VARCHAR(255) NOT NULL,
          turma VARCHAR(50) NOT NULL,
          materia VARCHAR(100) NOT NULL
};

-- 2. Tabela de Registro de Bem-Estar do Professor
CREATE TABLE tb_bem_estar (
                              id BIGSERIAL PRIMARY KEY,
                              meditar VARCHAR(255),
                              respirar VARCHAR(255),
                              alongar VARCHAR(255),
                              registro_data_hora TIMESTAMP NOT NULL,
                              professor_id BIGINT NOT NULL,
                              CONSTRAINT fk_bem_estar_professor FOREIGN KEY (professor_id) REFERENCES tb_professor(professor_id) ON DELETE CASCADE
);

-- 3. Tabela de Alunos
CREATE TABLE tb_aluno (
                          aluno_id BIGSERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          atipico VARCHAR(50) NOT NULL,
                          idade INTEGER NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255),
                          turma VARCHAR(50) NOT NULL,
                          professor_id BIGINT,
                          CONSTRAINT fk_aluno_professor FOREIGN KEY (professor_id) REFERENCES tb_professor(professor_id) ON DELETE SET NULL
);

-- 4. Tabela de Atividades Pedagógicas
CREATE TABLE tb_atividade (
                              atividade_id BIGSERIAL PRIMARY KEY,
                              titulo VARCHAR(255) NOT NULL,
                              descricao_original TEXT NOT NULL,
                              conteudo_adaptado TEXT,
                              concluida BOOLEAN DEFAULT FALSE,
                              materia VARCHAR(100) NOT NULL,
                              aluno_id BIGINT NOT NULL,
                              CONSTRAINT fk_atividade_aluno FOREIGN KEY (aluno_id) REFERENCES tb_aluno(aluno_id) ON DELETE CASCADE
);

-- 5. Tabela de Insumos e Peças Livres (Cadastradas pelo Professor para laboratórios)
CREATE TABLE tb_material (
                             material_id BIGSERIAL PRIMARY KEY,
                             nome VARCHAR(255) NOT NULL,
                             categoria VARCHAR(100) NOT NULL,
                             materia VARCHAR(100) NOT NULL,
                             enunciado_experimento TEXT,
                             professor_id BIGINT,
                             CONSTRAINT fk_professor FOREIGN KEY (professor_id) REFERENCES tb_professor(professor_id) ON DELETE SET NULL
);

-- 6. Tabela do Laboratório de Química (Alquimista)
CREATE TABLE tb_alquimista (
                               id BIGSERIAL PRIMARY KEY,
                               nome_resultado VARCHAR(255) NOT NULL,
                               elemento_1 VARCHAR(255) NOT NULL,
                               elemento_2 VARCHAR(255) NOT NULL,
                               composto_resultante VARCHAR(255) NOT NULL,
                               explicacao_cientifica TEXT,
                               data_descoberta TIMESTAMP NOT NULL,
                               atividade_id BIGINT NOT NULL,
                               professor_id BIGINT,
                               CONSTRAINT fk_alquimista_atividade FOREIGN KEY (atividade_id) REFERENCES tb_atividade(atividade_id) ON DELETE CASCADE,
                               CONSTRAINT fk_alquimista_professor FOREIGN KEY (professor_id) REFERENCES tb_professor(professor_id) ON DELETE SET NULL
);

-- 7. Tabela do Laboratório de Física (Física Legal)
CREATE TABLE tb_fisica_legal (
                                 id BIGSERIAL PRIMARY KEY,
                                 nome_circuito VARCHAR(255) NOT NULL,
                                 sucesso BOOLEAN NOT NULL,
                                 data_realizacao TIMESTAMP NOT NULL,
                                 atividade_id BIGINT NOT NULL,
                                 professor_id BIGINT,
                                 CONSTRAINT fk_fisica_atividade FOREIGN KEY (atividade_id) REFERENCES tb_atividade(atividade_id) ON DELETE CASCADE,
                                 CONSTRAINT fk_fisica_professor FOREIGN KEY (professor_id) REFERENCES tb_professor(professor_id) ON DELETE SET NULL
);

-- 8. Tabela do Laboratório de Matemática (Matemagicamente)
CREATE TABLE tb_matemagicamente (
                                    id BIGSERIAL PRIMARY KEY,
                                    desafio VARCHAR(255) NOT NULL,
                                    pontos_obtidos INT NOT NULL,
                                    data_realizacao TIMESTAMP NOT NULL,
                                    atividade_id BIGINT NOT NULL,
                                    professor_id BIGINT,
                                    CONSTRAINT fk_matematica_atividade FOREIGN KEY (atividade_id) REFERENCES tb_atividade(atividade_id) ON DELETE CASCADE,
                                    CONSTRAINT fk_matematica_professor FOREIGN KEY (professor_id) REFERENCES tb_professor(professor_id) ON DELETE SET NULL
);