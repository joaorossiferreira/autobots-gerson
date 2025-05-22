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

## ✅ Novos Recursos Adicionados!
### Agora o sistema inclui gestão completa de:
- ➕ Endereços vinculados a clientes
- 📞 Múltiplos telefones por cliente
- 🔄 Atualização parcial de registros
- 🌐 HATEOAS para navegação entre entidades
<br>

## 🔄 Rotas Cliente CRUD
**POST** `/cliente/cadastro` (Novo formato):
```json
{
  "nome": "Fulano de Tal",
  "nomeSocial": "Ciclano",
  "CPF": "123.456.789-09",
  "RG": "12.345.678-9",
  "dataNascimento": "1990-01-01",
  "endereco": {
    "estado": "SP",
    "cidade": "São Paulo",
    "rua": "Av. Paulista",
    "numero": "1000"
  },
  "telefones": [
    {"ddd": "11", "numero": "987654321"}
  ]
}
```

**Novo PUT** `/cliente/atualizar` (Atualização parcial):
```json
{
  "id": 1,
  "nomeSocial": "Novo Apelido",
  "endereco": {"cidade": "Rio de Janeiro"}
}
```

## 📞 Novas Rotas Telefone
**Adicionar telefone** (POST `/telefone/{clienteId}`):
```json
{"ddd": "21", "numero": "998877665"}
```

**Listar telefones** (GET `/telefone/{clienteId}`):
```json
[
  {
    "id": 1,
    "ddd": "11",
    "numero": "987654321",
    "_links": {
      "self": "/telefone/1/1",
      "cliente": "/cliente/1"
    }
  }
]
```

## 🏠 Rotas Endereço
**Vincular endereço** (PUT `/endereco/cadastro/{clienteId}`):
```json
{
  "estado": "MG",
  "cidade": "Belo Horizonte",
  "bairro": "Savassi",
  "rua": "Rua da Bahia",
  "numero": "123"
}
```

**Consultar endereço** (GET `/endereco/{id}`):
```json
{
  "id": 1,
  "cidade": "São Paulo",
  "rua": "Av. Paulista",
  "_links": {
    "cliente": "/cliente/1",
    "editar": "/endereco/cadastro/1"
  }
}
```

## 🌐 HATEOAS Ampliado
Exemplo de resposta com links:
```json
{
  "id": 1,
  "nome": "Cliente Exemplo",
  "_links": {
    "self": {"href": "/cliente/1"},
    "telefones": {"href": "/telefone/1"},
    "endereco": {"href": "/endereco/1"},
    "delete": {"href": "/cliente/excluir/1"}
  }
}
```

[⬆️ Voltar ao topo](#rodando-o-projeto)