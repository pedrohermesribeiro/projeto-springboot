# projeto-springboot
Aplicação de e-commerce com gerenciamento de pedidos, itens de pedido, produtos, categorias, usuários e pagamentos, utilizando Spring Boot e Hibernate como ORM
E-Commerce Order Management
Bem-vindo ao projeto E-Commerce Order Management, uma aplicação backend para gerenciamento de pedidos em um sistema de comércio eletrônico. Este projeto foi desenvolvido utilizando Spring Boot e Hibernate para persistência de dados, oferecendo uma API RESTful para operações relacionadas a pedidos, produtos, categorias, usuários e pagamentos.

Visão Geral
A aplicação permite:

Criar, listar e gerenciar pedidos (Order).
Associar itens a pedidos (OrderItem), incluindo produtos, quantidades e preços.
Gerenciar informações de produtos (Product) e suas categorias (Category).
Armazenar dados de usuários (User) como clientes.
Registrar pagamentos (Payment) associados aos pedidos.
A estrutura do banco de dados é baseada em tabelas relacionais, mapeadas via Hibernate, com consultas otimizadas para recuperação de dados.

Tecnologias Utilizadas
Java: Linguagem de programação principal.
Spring Boot: Framework para criação de aplicações web e APIs REST.
Hibernate: ORM para mapeamento objeto-relacional e persistência de dados.
H2/PostgreSQL/MySQL: Banco de dados (configurável).
Maven: Gerenciamento de dependências.
Estrutura do Banco de Dados: PostgreSQL.
A aplicação utiliza as seguintes tabelas principais:

tb_order: Armazena informações dos pedidos (ID, data/hora, status, cliente).
tb_order_item: Relaciona pedidos a produtos, com quantidade e preço.
tb_product: Contém dados dos produtos (nome, descrição, preço, URL da imagem, categoria).
tb_category: Categorias dos produtos.
tb_user: Dados dos usuários/clientes (email, nome, senha, telefone).
tb_payment: Informações de pagamento associadas aos pedidos.
Exemplo de Consulta Hibernate
sql

Recolher

Encapsular

Copiar
select p1_0.order_id, p1_0.moment, o1_0.id, c1_0.id, c1_0.email, c1_0.name, c1_0.password, 
       c1_0.phone, o1_0.instante, o1_0.order_status, oi1_0.order_id, oi1_0.product_id, 
       p2_0.id, c2_0.id, c2_0.name, p2_0.description, p2_0.imgurl, p2_0.name, 
       p2_0.price, oi1_0.price, oi1_0.quantity 
from tb_payment p1_0 
join tb_order o1_0 on o1_0.id = p1_0.order_id 
left join tb_user c1_0 on c1_0.id = o1_0.client_id 
left join tb_order_item oi1_0 on o1_0.id = oi1_0.order_id 
left join tb_product p2_0 on p2_0.id = oi1_0.product_id 
left join tb_category c2_0 on c2_0.id = p2_0.category_id 
where p1_0.order_id = ?
Pré-requisitos
Java 11+: Certifique-se de ter o JDK instalado.
Maven: Para gerenciamento de dependências e build.
Banco de dados configurado (ex.: H2 para testes ou PostgreSQL para produção).
Instalação
Clone o repositório:
bash

Recolher

Encapsular

Copiar
git clone https://github.com/seu-usuario/ecommerce-order-management.git
Navegue até o diretório do projeto:
bash

Recolher

Encapsular

Copiar
cd ecommerce-order-management
Configure o banco de dados no arquivo application.properties:
properties

Recolher

Encapsular

Copiar
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
Compile e execute a aplicação:
bash

Recolher

Encapsular

Copiar
mvn spring-boot:run
Endpoints da API
Pedidos
GET /orders: Lista todos os pedidos.
POST /orderitems: Adiciona um item a um pedido.
Exemplo de Requisição POST
json

Recolher

Encapsular

Copiar
{
  "produto": "Mouse Option Dell",
  "quantity": 2,
  "price": 200.0
}
Resposta
text

Recolher

Encapsular

Copiar
OrderItem cadastrada com sucesso
Execução e Logs
A aplicação gera logs detalhados das consultas Hibernate e das operações da API. Exemplo:

text

Recolher

Encapsular

Copiar
2025-03-20T16:58:07.233-03:00 DEBUG 9720 --- [io-8080-exec-10] o.s.web.servlet.DispatcherServlet : Completed 200 OK
Contribuição
Faça um fork do projeto.
Crie uma branch para sua feature (git checkout -b feature/nova-funcionalidade).
Commit suas alterações (git commit -m 'Adiciona nova funcionalidade').
Push para o repositório remoto (git push origin feature/nova-funcionalidade).
Abra um Pull Request.
