<h1>SA6 MERCADO</h1>
Repositório destinado a atividade avaliativa final de JAVA do 2º semestre do curso de Desenvolvimento de Sistemas do SENAI.

<h2> 👨‍🏫 Professor Orientador:</h2>
Diogo Takamori / https://github.com/Diogotb

<h2>👩‍💻 Integrantes do grupo:</h2>

Rhuan Carlos / https://github.com/RhuanCarlos019 <br>
Yasmin Lima / https://github.com/Y45m1n

## Tecnologias Utilizadas
- **Java:** Linguagem de programação utilizada.
- **GUI Swing:** Biblioteca gráfica para construção da interface gráfica do usuário.
- **PostgreSQL:** Sistema de banco de dados utilizado.

## Estrutura do Projeto
O projeto é dividido em alguns pacotes principais:

1. **Gerenciamento.Control:** Contém classes responsáveis pelo controle e lógica de negócios do sistema.
2. **Gerenciamento.Connection:** Contém classes para interação com o banco de dados PostgreSQL.
3. **Gerenciamento.Model:** Contém classes de modelo que representam as entidades do sistema.
4. **Gerenciamento.View:** Contém classes relacionadas à interface gráfica do usuário.


## Manual de Uso

### 1. Configuração do Banco de Dados
Antes de executar o sistema, certifique-se de que o banco de dados PostgreSQL esteja configurado corretamente. As informações de conexão podem ser ajustadas no arquivo `ConnectionFactory.java` no pacote `Gerenciamento.Connection`.

### 2. Compilação e Execução
Certifique-se de ter um ambiente de desenvolvimento Java configurado. O projeto pode ser compilado e executado utilizando uma IDE Java como o Eclipse ou através de comandos no terminal.

### 3. Funcionalidades do Sistema

#### 3.1. Clientes
- **Cadastro de Cliente:** Permite cadastrar um novo cliente informando CPF, nome completo e idade.
- **Atualização de Cliente:** Permite atualizar informações de um cliente existente.
- **Exclusão de Cliente:** Remove um cliente do sistema.

#### 3.2. Estoque
- **Cadastro de Produto:** Permite cadastrar um novo produto no estoque informando nome, preço e quantidade.
- **Atualização de Produto:** Atualiza informações de um produto existente.
- **Exclusão de Produto:** Remove um produto do estoque.
- **Atualização de Quantidade:** Atualiza a quantidade de um produto no estoque.

#### 3.3. Vendas
- **Registro de Venda:** Permite registrar uma venda, incluindo cliente, valor, data e quantidade de produtos.

### 4. Considerações Finais
Este sistema foi desenvolvido como uma atividade educacional, e pode conter limitações ou ser expandido conforme necessário. Qualquer dúvida ou sugestão, entre em contato com os integrantes do grupo.
