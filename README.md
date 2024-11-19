# API Produtos
API RESTful desenvolvida em Spring Boot que fornece um CRUD para gerenciamento de produtos.

## Tecnologias utilizadas
- Java 21
- Spring Boot
- Maven
- Spring Data JPA
- Hibernate
- PostgreSQL
- Swagger
- Lombok
- Model Mapper
- Bean Validation
- MockMvc
- JUnit

## Endpoints 
| Método | Endpoint             | Descrição                   |
|--------|-----------------------|-----------------------------|
| POST   | `/api/produtos`      | Cadastra um novo produto      |
| GET    | `/api/produtos`      | Consulta todos os produtos   |
| GET    | `/api/produtos/{id}` | Consulta um produto por ID  |
| PUT    | `/api/produtos/{id}` | Atualiza um produto       |
| DELETE | `/api/produtos/{id}` | Remove um produto     |

```json
{
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "nome": "string",
    "preco": 0,
    "quantidade": 0
}
```

## Instalação

#### Requisitos
- Java 21 ou superior
- Spring Boot 3.3.5
- Maven 3.8+
- PostgreSQL 16

#### 1. Clone o repositório:
```bash
   git clone https://github.com/samuelmsilva2v/apiProdutos.git
   cd apiProdutos
```
#### 2. Instalando as dependências e compilando o projeto com Maven:
```bash
./mvnw clean install
```
#### 3. Executando a aplicação:
```bash
./mvnw spring-boot:run
```

### 5. Acesse a aplicação:
  - Documentação da API: http://localhost:8080/swagger-ui/index.html

### Autor
- Samuel Maciel da Silva
  - [LinkedIn](https://www.linkedin.com/in/samuelmsilva2v/)
  - [E-mail](mailto:samuelmsilva@outlook.com.br)


