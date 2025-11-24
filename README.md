** API de Controle de Despesas Pessoais**

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
