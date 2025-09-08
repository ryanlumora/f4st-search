# F4st Search

## Problema de negócio
O sistema busca produtos da Fake Store API e gera insights relevantes:
- Produtos de **melhor valor** e **melhor avaliação**
- **Métricas por categoria**: preço médio, avaliação média
- **Top produtos por categoria**
- Histórico de preços para análise

---

## Arquitetura escolhida
**Hexagonal / Ports & Adapters**
- Separação entre **domínio** (services, model) e **infraestrutura** (adapters, repositories)
- Facilita **testes unitários**, manutenção e evolução do sistema

---

## Estrutura do projeto
```
src/
└─ main/
├─ java/com/f4stsearch/
│ ├─ adapter/out/ # Integração com API externa (Feign)
│ ├─ application/controller/ # Endpoints REST
| ├─ application/scheduler/ # Controle de cache
| ├─ config/ # Configuração do OpenAPI
│ ├─ domain/model/ # Modelos de domínio
│ ├─ domain/mapper/ # Lógica de mapeamento de entidade
│ ├─ domain/service/ # Lógica de negócio
│ ├─ domain/repository/ # Interfaces de persistência
│ └─ domain/port/ # Interfaces/ports
└─ resources/
└─ application.properties
```
---

## Tecnologias
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (local)
- OpenFeign (integração HTTP)
- Springdoc OpenAPI / Swagger (documentação)
- JUnit 5 + Mockito (testes unitários e integração)

---

## Como executar
1. Clonar o repositório:

```bash
git clone https://github.com/ryanlumora/f4st-search.git
cd f4st-search
```

2. Rodar via Maven:

```bash
./mvnw spring-boot:run
```

3. Acessar Swagger UI:

```bash
http://localhost:8080/swagger-ui.html
```

---

## Endpoints disponíveis

## Products

# Get all products

**GET /products**

Exemplo de resposta:
```
{
  "id": 1,
  "title": "Fjallraven - Foldsack No. 1 Backpack",
  "price": 109.95,
  "description": "Your perfect pack for everyday use and walks in the forest.",
  "category": "men's clothing",
  "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
  "rating": {
    "rate": 4.9,
    "count": 120
  },
  "externalLink": "https://fakestoreapi.com/products/1"
}
```

# Get product by ID

**GET /products/{id}**

Exemplo de resposta:
```
{
  "id": 1,
  "title": "Fjallraven - Foldsack No. 1 Backpack",
  "price": 109.95,
  "description": "Your perfect pack for everyday use and walks in the forest.",
  "category": "men's clothing",
  "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
  "rating": {
    "rate": 4.9,
    "count": 120
  },
  "externalLink": "https://fakestoreapi.com/products/1"
}
```
## Insights

# Best value products

**GET /insights/best-value?category=men's clothing&limit=5**

Exemplo de resposta:
```
  {
    "id": 1,
    "title": "Fjallraven - Foldsack No. 1 Backpack",
    "price": 109.95,
    "description": "Your perfect pack for everyday use and walks in the forest.",
    "category": "men's clothing",
    "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
    "rating": {
      "rate": 4.9,
      "count": 120
    },
    "externalLink": "https://fakestoreapi.com/products/1"
  }
```

# Best rated products

**GET /insights/best-rate?limit=3**


Exemplo de resposta:

```
  {
    "id": 1,
    "title": "Fjallraven - Foldsack No. 1 Backpack",
    "price": 109.95,
    "description": "Your perfect pack for everyday use and walks in the forest.",
    "category": "men's clothing",
    "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
    "rating": {
      "rate": 4.9,
      "count": 120
    },
    "externalLink": "https://fakestoreapi.com/products/1"
  }
```


# Average price per category

**GET /insights/average-price-by-category**


Exemplo de resposta:
```
{
  "men's clothing": 65.0,
  "jewelery": 520.0,
  "electronics": 199.99
}
```

# Average rating per category

**GET /insights/average-rating-by-category**

Exemplo de resposta:
```
{
  "men's clothing": 4.3,
  "jewelery": 4.7,
  "electronics": 4.2
}
```

# Top product by category

**GET /insights/top-by-category**

Exemplo de resposta:
```
{
  "men's clothing": {
    "id": 1,
    "title": "Fjallraven - Foldsack No. 1 Backpack",
    "price": 109.95,
    "description": "Your perfect pack for everyday use and walks in the forest.",
    "category": "men's clothing",
    "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
    "rating": {
      "rate": 4.9,
      "count": 120
    },
    "externalLink": "https://fakestoreapi.com/products/1"
  }
}
```

# Historical average price per product

**GET /insights/historical/average-price**

Exemplo de resposta:
```
{
  "1": 109.95,
  "2": 22.3,
  "3": 55.5
}
```
---

## Decisões técnicas e trade-offs

# Cache local (H2): armazena histórico e agiliza cálculos sem sobrecarregar a API externa.

# Hexagonal architecture: desacoplamento entre domínio e infraestrutura, facilita testes e manutenção.

# Feign Client: simplifica chamadas HTTP externas, reduz boilerplate.

# Cobertura de testes >80%: garante confiabilidade dos serviços e endpoints.

# Springdoc / Swagger: documentação interativa dos endpoints para consumo rápido.

# Histórico de snapshots: permite análises históricas de preços e métricas.

# Decisão de não implementar CRUD: dados são externos; foco em leitura, cache e insights.
