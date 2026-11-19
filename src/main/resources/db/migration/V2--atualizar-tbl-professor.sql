-- 1. Tabela de Professores
CREATE TABLE tb_professor (
                              professor_id BIGSERIAL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL,
                              email VARCHAR(255) NOT NULL UNIQUE,
                              senha VARCHAR(255) NOT NULL,
                              turma VARCHAR(50) NOT NULL,
                              materia VARCHAR(100) NOT NULL
);