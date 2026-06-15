# Sistema de Biblioteca

Trabalho A1 da disciplina de Programação Orientada a Objetos em Java.  
O sistema simula o gerenciamento de uma biblioteca: cadastro de livros, usuários, funcionários e controle de empréstimos.

**Dupla:** [Seu nome] e [Nome do colega]  
**Período:** 3º período — Ciências da Computação  

---

## O que o sistema faz

- Cadastrar, editar, listar e remover livros
- Cadastrar, editar, listar e remover usuários
- Cadastrar, editar, listar e remover funcionários
- Realizar empréstimos de livros e registrar devoluções
- Calcular multa automática por atraso (R$ 0,50 por dia)
- Salvar todos os dados em arquivos (os dados persistem entre execuções)
- Registrar tudo que acontece em um arquivo de log

---

## Como rodar

Precisa ter o Java instalado (JDK 8 ou superior).

```bash
# Dentro da pasta do projeto, compile tudo de uma vez:
javac -d out src/util/*.java src/model/*.java src/controller/*.java src/view/*.java src/Main.java

# Depois é só rodar:
java -cp out Main
```

Se estiver usando IntelliJ ou Eclipse, basta importar o projeto e rodar o `Main.java`.

---

## Estrutura de pastas

```
src/
├── Main.java
├── model/          → as classes que representam os dados
├── view/           → os menus e interação com o usuário
├── controller/     → a lógica de cada funcionalidade
└── util/           → Logger e a classe de persistência
dados/              → arquivos gerados automaticamente ao rodar
```

---

## Requisitos técnicos implementados

- **Herança e classe abstrata:** `Pessoa` é abstrata, `Usuario` e `Funcionario` herdam dela
- **Interface:** `Catalogavel` é implementada por `Livro`
- **Polimorfismo:** sobrescrita do método `getTipo()` e sobrecarga do `getInfo()`
- **Encapsulamento:** todos os atributos são privados com getters e setters
- **3 CRUDs com relacionamento:** Livro, Usuario e Emprestimo (empréstimo usa os IDs de usuário e livro como chave estrangeira)
- **Serialização:** classe `Repositorio<T>` salva e carrega qualquer lista de objetos em arquivo `.dat`
- **Log:** classe `Logger` registra todas as operações em `dados/sistema.log`
- **Arquitetura MVC:** model, view e controller separados em pacotes diferentes

---

## Uso de IA

Usamos o ChatGPT e o Claude em alguns momentos do projeto, principalmente pra tirar dúvidas de sintaxe do Java e entender melhor como funciona a serialização. A lógica do sistema, a estrutura das classes e as decisões de design foram nossas — a IA ajudou mais como um "Stack Overflow conversacional" do que como geradora de código. No final, o importante pra nós foi entender o que cada parte faz, porque a defesa é individual.
