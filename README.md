**API de Controle de Despesas Pessoais**

API RESTful desenvolvida com Spring Boot para gerenciamento financeiro pessoal, permitindo cadastro e controle de despesas e receitas. O objetivo do projeto é fornecer uma ferramenta simples e eficiente para que o usuário consiga registrar, organizar e acompanhar todas as movimentações financeiras do dia a dia, obtendo uma visão ampla de suas economias.

Com este sistema, é possível categorizar despesas e receitas, controlar diferentes formas de pagamento e recebimento, e monitorar padrões de gastos e entradas de dinheiro. Dessa forma, o usuário pode identificar onde está gastando mais, planejar melhor suas finanças e tomar decisões conscientes sobre economia e investimentos.

O projeto também serve como base para aprendizado de boas práticas em desenvolvimento de APIs RESTful, uso de Spring Boot, integração com MySQL, validações de dados, tratamento de erros e organização de código em camadas (Controller, Service e Repository). Além disso, proporciona experiência prática com versionamento via Git e testes de endpoints com Postman.

**Entidades do Projeto**

**Categoria:** Armazena os tipos de despesas (ex.: Alimentação, Transporte).

**CategoriaReceita:** Armazena os tipos de receitas (ex.: Salário, Venda).

**Despesa:** Registra despesas do usuário, vinculadas a uma categoria e forma de pagamento.

**Receita:** Registra receitas do usuário, vinculadas a uma categoria de receita e forma de recebimento, podendo ser recorrente.

**FormaPagamento:** Armazena os métodos de pagamento disponíveis (ex.: Cartão, Dinheiro, Pix).

**FormaRecebimento:** Armazena os métodos de recebimento de receita (ex.: Transferência, Dinheiro, Pix).

**Banco de Dados**

O projeto utiliza MySQL com persistência via Spring Data JPA, criando automaticamente as tabelas a partir das entidades.

**Execução**

Configurar o banco de dados no application.properties.

Executar a aplicação com mvn spring-boot:run.

Testar endpoints via Postman.


**Exemplos para fazer requisições:

POST categoria:

http://localhost:8080/api/v1/categorias

{
  "nome": "Saúde",
  "descricao": "Farmácia, consultas médicas",
  "ativo": true
}

PUT categoria:

http://localhost:8080/api/v1/categorias/1

{
  "nome": "Alimentação e Bebidas",
  "descricao": "Gastos com alimentação, supermercado e bebidas",
  "ativo": true
}

DEL categoria (precisa ter mais categorias, apenas um exemplo)

http://localhost:8080/api/v1/categorias/1**

**POST forma_pagamento:

{
  "idFormaPagamento": 1,
  "nome": "Dinheiro",
  "descricao": "Pagamento em espécie",
  "ativo": true
}

POST despesas:

http://localhost:8080/api/v1/despesas

{
  "descricao": "Compras supermercado - Extra",
  "valor": 650.50,
  "dataDespesa": "2025-11-12",
  "observacao": "Compras do mês",
  "categoria": {
    "idCategoria": 1
  },
  "formaPagamento": {
    "idFormaPagamento": 2
  }
}

POST categoria_receita:

http://localhost:8080/api/v1/categorias-receita

{
  "nome": "Salário",
  "descricao": "Salário mensal CLT",
  "ativo": true
}

POST formas_recebimento:

http://localhost:8080/api/v1/formas-recebimento

{
  "nome": "Depósito Bancário",
  "descricao": "Transferência para conta corrente",
  "ativo": true
}

POST receitas:

http://localhost:8080/api/v1/receitas/1

{
  "descricao": "Salário Novembro 2025",
  "valor": 5000.00,
  "dataReceita": "2025-11-05",
  "observacao": "Salário mensal - Empresa XPTO",
  "categoriaReceita": {
    "idCategoriaReceita": 2
  },
  "formaRecebimento": {
    "idFormaRecebimento": 1
  },
  "recorrente": true
}

GET listar despesas por periodo:

http://localhost:8080/api/v1/despesas/periodo?dataInicio=2025-11-01&dataFim=2025-11-30

resposta da requisição de despesas por periodo:
[
    {
        "idDespesa": 1,
        "descricao": "Aluguel Novembro 2025 - PAGO",
        "valor": 1500.00,
        "dataDespesa": "2025-11-10",
        "observacao": "Aluguel quitado - Rua das Flores, 123",
        "categoria": {
            "idCategoria": 2,
            "nome": "Alimentação",
            "descricao": "Gastos com alimentação, supermercado e bebidas ",
            "ativo": true
        },
        "formaPagamento": {
            "idFormaPagamento": 3,
            "nome": "PIX",
            "descricao": "Transferência instantânea",
            "ativo": true
        },
        "dataCadastro": "2025-11-21T20:18:16.331365",
        "dataAtualizacao": "2025-11-21T20:41:42.43743"
    }
]**
