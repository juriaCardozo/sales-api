# Configuração do Ambiente

## 1. Banco de Dados
O banco de dados PostgreSQL deve estar configurado. Utilize o seguinte comando para criar o banco de dados e as tabelas necessárias, ele deve estar configurado na porta padrão (5432):

```sql
CREATE DATABASE sales_management;
```

## 2. Configuração do Projeto
Clone o repositório:

```bash
git clone https://github.com/juriaCardozo/sales-crud-test.git
```

Execute o comando para gerar o .war da aplicação:

```bash
mvn clean package -DskipTests
```

## 3. Construção e Execução com Docker
Construir a imagem Docker:

```bash
docker-compose build
```

Executar o Container:

```bash
docker-compose up
```

O servidor estará disponível na URL: http://localhost:8080

4. Executar Testes Unitários
Para rodar os testes unitários, utilize o comando Maven:

Maven:
```bash
mvn test
```
