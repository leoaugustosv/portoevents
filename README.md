# PortoEvents

Um agendador de eventos feito em Java, seguindo princípios da programação orientada a objetos.

## Objetivo

Esse projeto é um dos requisitos principais para aprovação na UC "Programação de soluções computacionais".

### Requisitos

- O projeto deve ser desenvolvido em Console;

- Deve implementar um sistema de cadastro e notificação de eventos que estejam ocorrendo em Porto Alegre;

- O sistema deve prover um espaço para cadastro do usuário. Você deve definir os atributos do usuário, que devem ser no mínimo 3 (quanto mais completo, melhor);

- Deve ser possível cadastrar eventos, definindo um horário (dentre outros atributos). Estes eventos devem ter, obrigatoriamente, os atributos: nome, endereço, categoria, horário e descrição;

- Delimitar as categorias para criação de eventos;

- Deve ser possível consultar os eventos cadastrados e decidir participar de qualquer um que esteja listado;

- Da mesma forma, deve ser possível visualizar os eventos em que a presença do usuário foi confirmada, além de ser possível cancelar a participação;

- Através do horário, o programa deve ordenar os eventos mais próximos e informar se um evento está ocorrendo no momento (é desejável utilizar a estrutura DateTime para o controle de horários);

- O sistema também deve informar os eventos que já ocorreram;

- As informações dos eventos devem ser salvas em um arquivo de texto chamado events.data. Toda vez que o programa for aberto, deve carregar os eventos a partir da leitura deste arquivo;

- Na estrutura de software do projeto deve constar no mínimo uma classe abstrata e no mínimo 2 métodos virtuais (não necessariamente juntos).

## Como compilar e executar (Windows)

1. Abra uma janela do CMD e aponte-a para o diretório em que você deseja clonar este repo:
2. Execute os comandos abaixo para clonar e acessar a pasta raíz:

```
git clone https://github.com/leoaugustosv/portoevents.git
cd portoevents
```


3. Execute agora o comando abaixo para executar o script compilador disponibilizado neste repo ("compile.bat"):

`.\compile.bat`


**(OPCIONAL)** Se preferir, crie você o script compilador, salvando um arquivo .bat com o código abaixo e o colocando na raíz do projeto:

```@echo off
setlocal enabledelayedexpansion
set CP=
for /r %%f in (*.java) do (
    set CP=!CP! %%f
)
javac !CP!
endlocal
```

4. Após executar o arquivo .bat, agora basta executar o código abaixo para iniciar o sistema:

`java -cp src com.linsysdev.portoevents.main.Main`

Pronto, o programa foi compilado! :)