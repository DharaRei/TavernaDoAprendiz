# 🧙‍♂️ Taverna do Aprendiz — Trilha Mágica

Plataforma educacional gamificada voltada à gestão centrada no professor, inclusão escolar, adaptação pedagógica com inteligência artificial (**Angel**) e bem-estar docente, desenvolvida para o **Global Solution — CitizenTech (FIAP)**.

---

## ✨ Visão Geral & Funcionalidades

A **Taverna do Aprendiz** combina uma identidade acolhedora e lúdica — unindo o clima de taverna medieval-futurista a interfaces em tons pastel — com recursos práticos para docentes e estudantes:

- **Painel do Mestre (`painel-professor.html`):** Central de navegação para progresso da turma, criação/listagem de atividades, bem-estar e adaptação com IA.
- **Cadastro e Acesso (`cadastroProfessor.html` / `index.html`):** Registro de professores e acesso ao ambiente de gestão.
- **Atividades e Missões (`cadastrar-atividade.html`):** Cadastro de atividades direcionadas às turmas e listagem das atividades cadastradas.
- **Progresso da Turma (`progresso.html` / `progresso-trilhaProfessor.html`):** Registro, consulta, atualização e exclusão do acompanhamento de progresso dos aprendizes.
- **Adaptação com IA — Angel (`tela-adaptar-ia.html`):** Geração e adaptação de conteúdos pedagógicos para alunos atípicos com apoio da **Gemini API**.
- **Bem-Estar Docente (`bemestar-professor.html`):** Espaço voltado ao autocuidado e pausa do professor.

---

## 🛠️ Stack Tecnológica

### Front-end
- **HTML5**, **Tailwind CSS** e **JavaScript** (`fetch` API)
- Fontes: **Playfair Display**, **Inter** e **Plus Jakarta Sans**

### Back-end, Dados & IA
- **Java** & **Spring Boot** (API configurada na porta `8081`)
- **PostgreSQL** como banco de dados relacional
- **Flyway** para versionamento e migração do schema do banco
- **Integração com Gemini API** para adaptação de planos de aula e conteúdos inclusivos (**Angel**)

### Testes, Containers & CI/CD
- **Docker** para conteinerização e padronização do ambiente
- **Cucumber** para testes automatizados / BDD
- **GitHub Actions** para automação de workflow e integração contínua

---

## 🚀 Como Executar Localmente

### 1. Subir os Serviços com Docker
Com o Docker em execução, suba os containers necessários (como o banco **PostgreSQL**):

```bash
docker compose up -d
