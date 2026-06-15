**Sistema de Biblioteca**
**Trabalho A1 – Programação Orientada a Objetos (Java)**

Este projeto foi desenvolvido com o objetivo de simular o funcionamento básico de uma biblioteca, permitindo o gerenciamento de livros, usuários, funcionários e empréstimos. A ideia foi criar um sistema simples, mas que aplicasse na prática os principais conceitos estudados na disciplina de Programação Orientada a Objetos.

**Dupla:** Gabriel Magalhães e Pedro Mesquita
3º período – Ciências da Computação

### Funcionalidades do sistema

O sistema permite:

* Cadastrar, consultar, editar e remover livros;
* Cadastrar, consultar, editar e remover usuários;
* Cadastrar, consultar, editar e remover funcionários;
* Registrar empréstimos e devoluções de livros;
* Calcular automaticamente multas por atraso na devolução, no valor de R$ 0,50 por dia;
* Salvar todas as informações em arquivos, mantendo os dados mesmo após o encerramento do programa;
* Registrar as operações realizadas em um arquivo de log para acompanhamento do uso do sistema.

### Organização do projeto

```
src/
├── Main.java
├── model/       → classes que representam as entidades do sistema
├── view/        → menus e interação com o usuário
├── controller/  → regras de negócio e controle das funcionalidades
└── util/        → classes auxiliares, como persistência e logs

dados/           → pasta criada automaticamente para armazenar os arquivos do sistema
```

### Conceitos de Programação Orientada a Objetos utilizados

Durante o desenvolvimento do projeto, foram aplicados diversos conceitos trabalhados em aula, como:

* **Herança:** a classe abstrata `Pessoa` serve de base para `Usuario` e `Funcionario`;
* **Interfaces:** a interface `Catalogavel` foi implementada pela classe `Livro`;
* **Polimorfismo:** utilização de sobrescrita e sobrecarga de métodos;
* **Encapsulamento:** atributos privados com acesso controlado por getters e setters;
* **Relacionamento entre entidades:** os empréstimos associam usuários e livros por meio de seus identificadores;
* **Serialização:** utilizada para salvar e recuperar os dados dos arquivos;
* **Registro de logs:** todas as operações importantes são registradas para fins de controle;
* **Arquitetura MVC:** separação entre dados, interface e lógica do sistema, facilitando a organização e manutenção do código.

O desenvolvimento deste trabalho permitiu colocar em prática os conteúdos da disciplina, proporcionando uma experiência mais próxima de uma aplicação real e reforçando conceitos importantes da Programação Orientada a Objetos em Java.


## Uso de IA

Usamos o ChatGPT e o Claude em alguns momentos do projeto, principalmente pra tirar dúvidas de sintaxe do Java e entender melhor como funciona a serialização. A lógica do sistema, a estrutura das classes e as decisões de design foram nossas — a IA ajudou mais como um "Stack Overflow conversacional" do que como geradora de código. No final, o importante pra nós foi entender o que cada parte faz, porque a defesa é individual.
