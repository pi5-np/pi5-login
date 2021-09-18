# pi5-login
Microsserviço de cadastro/login - Newton Paiva


### Microsserviço de Login/Cadastro utilizando Kotlin + Spring Boot + Spring Security + PostgreSQL

Method: POST
-- URL local para CADASTRO: localhost:8080/api/admin
Obs.: Caso o Spring Boot esteja rodando em outra porta, substitua o 8080 pela porta escolhida

##### Exemplo de input para CADASTRO
{
    "username":"usuario",
    "password":"123456"
}
##### Exemplo de output do CADASTRO
{
    "username": "usuario",
    "password": "123456"
}
HTTP - 200 - OK
##### O microsserviço irá verificar se existe um username já cadastrado no Banco de Dados e caso exista ele irá criptografar a senha e persistir no Banco de Dados, caso não exista ele irá devolver um HTTP - 400 - Bad Request com a seguinte mensagem: "Já existe um usuário cadastrado com esse username!"

-- URL local para LOGIN: localhost:8080/api/login
Obs.: Caso o Spring Boot esteja rodando em outra porta, subistutua o 8080 pela porta escolhida

##### Exemplo de input para LOGIN
{
    "username":"usuario",
    "password":"123456"
}
##### Exemplo de output do LOGIN
{
    "success": true,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvIiwiaWF0IjoxNjMxMjI3NDk3LCJleHAiOjE2MzIwODc0OTd9.8LnEI799D4ztzpb3zJXhNwd_Dnk-yzIH84Il-Vu43Vw",
    "message": ""
}
##### O microsserviço irá consultar se existe o username no Banco de Dados e caso exista irá verificar a senha, se estiver tudo correto ele irá gerar um Token JWT e devolver no Body com um HTTP - 200 - OK, caso o usuario não esteja cadastrado ele irá devolver o nome passado no username e um HTTP - 400 - Bad Request; Caso a senha tenha sido passado incorretamente o sistema irá devolver a seguinte mensagem: "Usuário ou senha incorretos!" e um HTTP - 400 - Bad Requet.

#### O Token JWT será usado no Body das futuras requisições no outros microsserviços
