# language: pt
Funcionalidade: Cadastro de Atividades
  Como um usuário da plataforma Trilha Mágica
  Quero cadastrar e listar atividades
  Para que os alunos possam acompanhar seus desafios educacionais

  Cenário: Cadastrar uma nova atividade com sucesso
    Dado que eu tenho os dados de uma nova atividade com título "Matemática Lúdica", matéria "MATEMATICA", descrição "Exercícios interativos de contagem" e alunoId 1
    Quando eu envio uma requisição POST para "/atividades"
    Então o status da resposta deve ser 201 Created
    E a resposta deve conter o título "Matemática Lúdica"


  Cenario: Listar atividades cadastradas com sucesso
    Dado que eu tenho o sistema configurado com atividades
    Quando eu envio uma requisição GET para "/atividades"
    Então o status da resposta deve ser 200 OK
    E a lista de atividades não deve estar vazia