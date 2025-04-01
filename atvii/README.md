# Rodando o Projeto

## 📌 Instale o Maven

### Verifique se o Maven já está instalado
Abra um terminal e execute:

```sh
mvn -version
```

Se aparecer um erro como **"mvn não é reconhecido como um comando interno ou externo"**, siga os passos abaixo para instalar.

---
### Passo 1: Baixar o Maven
1. Acesse o site oficial: [Maven Download](https://maven.apache.org/download.cgi).
2. Baixe a versão **Binary zip archive**.
3. Extraia o conteúdo para uma pasta, por exemplo: `C:\apache-maven-3.x.x`.
---
### Passo 2: Configurar o PATH 
---
### Windows
1. Abra o **Explorador de Arquivos** e copie o caminho onde extraiu o Maven, por exemplo:
   ```
   C:\apache-maven-3.x.x\bin
   ```
2. No Windows, vá para:
   - **Este Computador** → **Propriedades** → **Configurações Avançadas do Sistema** → **Variáveis de Ambiente**.
   - Encontre a variável **Path**, clique em **Editar** e adicione o caminho acima.
---
### Linux/macOS
Se estiver no Linux ou macOS, edite o arquivo `~/.bashrc`, `~/.zshrc` ou `~/.bash_profile` e adicione:

```sh
export MAVEN_HOME=/caminho/para/maven
export PATH=$MAVEN_HOME/bin:$PATH
```
Depois, execute:

```sh
source ~/.bashrc
```
---


# 📌 Configuração de Ambiente
```sh
# Clone o repositório
git clone https://github.com/joaorossiferreira/autobots-gerson.git

# Entre na pasta do projeto
cd automanager

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```
---

## ✅ Tudo pronto!
### Só acessar a rota **http://localhost:8080/** para começar
<br><br>

[ Rotas usuário CRUD (usar para cadastro, edição, login, etc.) ](#rotas-usuário-crud)

[ Links Navegáveis HATEOAS ](#rotas-HATEOAS)
```sh
POST   http://localhost:8080/gerenciar/clientes/novo
DELETE http://localhost:8080/gerenciar/clientes/remover
GET    http://localhost:8080/gerenciar/clientes/todos
GET    http://localhost:8080/gerenciar/clientes/{id}
PUT    http://localhost:8080/gerenciar/clientes/modificar
```


<br><br>
# Utilizando as Rotas

### Rotas usuário CRUD

**POST:** Cadastre o usuário em http://localhost:8080/gerenciar/clientes/novo e formate o json da seguinte maneira:
```sh
{
  "nome": "Fulano de Tal",
  "nomeSocial": "Ciclano",
  "dataNascimento": "1990-01-01",
  "telefones": [
    {
      "ddd": "11",
      "numero": "987654321"
    }
  ],
  "endereco": {
    "estado": "SP",
    "cidade": "São Paulo",
    "bairro": "Centro",
    "rua": "Rua Principal",
    "numero": "123",
    "codigoPostal": "01001000"
  },
  "documentos": [
    {
      "tipo": "CPF",
      "numero": "12345678901"
    }
  ]
}
```

---
**DELETE:** Delete um usuário em http://localhost:8080/gerenciar/clientes/remover | Exemplo:
```sh
{
  "id": 1
}
```
---
**GET:** Puxe uma lista com todos os usuários em http://localhost:8080/gerenciar/clientes/todos
<details>
  <summary>Clique para ver o JSON retornada</summary>

```sh
[
	{
		"id": 1,
		"nome": "Fulano de Tal",
		"nomeSocial": "Ciclano",
		"dataNascimento": "1990-01-01T00:00:00.000+00:00",
		"dataCadastro": null,
		"documentos": [
			{
				"id": 1,
				"tipo": "CPF",
				"numero": "12345678901"
			}
		],
		"endereco": {
			"id": 1,
			"estado": "SP",
			"cidade": "São Paulo",
			"bairro": "Centro",
			"rua": "Rua Principal",
			"numero": "123",
			"codigoPostal": "01001000",
			"informacoesAdicionais": null
		},
		"telefones": [
			{
				"id": 1,
				"ddd": "11",
				"numero": "987654321"
			}
		]
	},
	{
		"id": 2,
		"nome": "Ciclano que não é Fulano",
		"nomeSocial": "Fulano",
		"dataNascimento": "2000-01-01T00:00:00.000+00:00",
		"dataCadastro": null,
		"documentos": [
			{
				"id": 2,
				"tipo": "CPF",
				"numero": "333333301"
			}
		],
		"endereco": {
			"id": 2,
			"estado": "SP",
			"cidade": "São Paulo",
			"bairro": "Centro",
			"rua": "Rua Principal",
			"numero": "123",
			"codigoPostal": "01001000",
			"informacoesAdicionais": null
		},
		"telefones": [
			{
				"id": 2,
				"ddd": "12",
				"numero": "1212121212"
			}
		]
	}
]
```

</details>

---
**GET:** Puxe as informações de um usuário específico em http://localhost:8080/gerenciar/clientes/id (no lugar do id, coloque o número, por exemplo /gerenciar/clientes/1)
<details>
    <summary>Clique para ver o JSON retornado</summary>

```sh
[
	{
		"id": 1,
		"nome": "Fulano de Tal",
		"nomeSocial": "Ciclano",
		"dataNascimento": "1990-01-01T00:00:00.000+00:00",
		"dataCadastro": null,
		"documentos": [
			{
				"id": 1,
				"tipo": "CPF",
				"numero": "12345678901"
			}
		],
		"endereco": {
			"id": 1,
			"estado": "SP",
			"cidade": "São Paulo",
			"bairro": "Centro",
			"rua": "Rua Principal",
			"numero": "123",
			"codigoPostal": "01001000",
			"informacoesAdicionais": null
		},
		"telefones": [
			{
				"id": 1,
				"ddd": "11",
				"numero": "987654321"
			}
		]
	}
]
```

</details>

---
**PUT:** Edite as informações do usuário em http://localhost:8080/gerenciar/clientes/modificar e formate seu JSON da seguinte maneira:
```sh
{
  "id": 1,
  "nome": "Nome Atualizado",
  "nomeSocial": "Novo Apelido"
}
```
---


### Rotas HATEOAS
```sh
{
  "id": 1,
  "nome": "Fulano de Tal",
  "_links": {
    "self": {
      "href": "http://localhost:8080/gerenciar/clientes/1"
    },
    "update": {
      "href": "http://localhost:8080/gerenciar/clientes/modificar"
    },
    "delete": {
      "href": "http://localhost:8080/gerenciar/clientes/remover"
    }
  }
}
```