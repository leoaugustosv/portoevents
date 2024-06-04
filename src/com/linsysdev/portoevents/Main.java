package com.linsysdev.portoevents;

import com.linsysdev.portoevents.utilidades.*;
import com.linsysdev.portoevents.eventos.Eventos;
import com.linsysdev.portoevents.eventos.EventosUtil;
import com.linsysdev.portoevents.usuarios.Usuarios;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // INICIALIZAR VARIAVEIS GLOBAIS
        boolean exit = false;
        boolean loggedIn = false;
        String currentUser = "";

        Scanner sc = new Scanner(System.in, "ISO-8859-1");

        while (!exit) {

            // OPCOES DO USUARIO DESLOGADO
            if (!loggedIn) {
                Utilidades.boasVindas();
                System.out.printf("--> ");
                String input = sc.nextLine();

                switch (input) {
                    case "1":
                        System.out.println();
                        System.out.printf("CPF --> ");
                        String input_cpf = sc.nextLine();
                        System.out.printf("SENHA --> ");
                        String input_senha = sc.nextLine();

                        boolean auth = Usuarios.autenticar(input_cpf, input_senha);

                        if (auth == false) {
                            System.out.println();
                            System.out.println("Aperte enter p1 1ara continuar.");
                            sc.nextLine();
                            continue;
                        } else {
                            loggedIn = true;
                            currentUser = input_cpf;
                            System.out.println();
                            System.out.println("Aperte enter para continuar.");
                            sc.nextLine();
                        }
                        break;
                    case "2":
                        Usuarios newuser = new Usuarios();
                        if (newuser.cadastrar(sc) == false) {
                            continue;
                        } else {
                            loggedIn = true;
                            currentUser = newuser.getCpf();
                        }
                        break;
                    case "3":
                        System.out.println(System.lineSeparator().repeat(50));
                        exit = true;
                        System.out.println(">> PROGRAMA FINALIZADO.");
                        System.out.println();
                        break;
                    default:
                        System.out.println(">> OPÇÃO DIGITADA INVÁLIDA.");

                }

            } else if (loggedIn) {
                // OPCOES DO USUARIO LOGADO
                Utilidades.boasVindasLogado();
                System.out.printf("--> ");
                String input = sc.nextLine();

                switch (input) {

                    // CRIAR EVENTOS
                    case "1":
                        Eventos event = new Eventos(currentUser);
                        event.criarEvento(sc);
                        break;

                    // GERENCIAR MEUS EVENTOS
                    case "2":
                        System.out.println(System.lineSeparator().repeat(50));
                        System.out.println("Insira a opção desejada para continuar:");
                        System.out.println();
                        System.out.println("(1) - Exibir meus eventos futuros");
                        System.out.println("(2) - Exibir meus eventos passados");
                        System.out.println("(3) - Cancelar participação em um evento");
                        System.out.println("---");
                        System.out.println("(0) - VOLTAR AO MENU");
                        String inputMeusEventos = sc.nextLine();
                        switch (inputMeusEventos) {
                            case "1":
                                System.out.println(System.lineSeparator().repeat(50));
                                EventosUtil.exibirMeusEventosFuturos(currentUser);
                                System.out.println("Aperte enter para retornar ao menu.");
                                sc.nextLine();
                                break;
                            case "2":
                                System.out.println(System.lineSeparator().repeat(50));
                                EventosUtil.exibirMeusEventosPassados(currentUser);
                                System.out.println("Aperte enter para retornar ao menu.");
                                sc.nextLine();
                                break;
                            case "3":
                                System.out.println(System.lineSeparator().repeat(50));
                                EventosUtil.cancelarParticipacao(currentUser, sc);
                                System.out.println("Aperte enter para retornar ao menu.");
                                sc.nextLine();
                                break;
                            case "0":
                                System.out.println();
                                System.out.println("Aperte enter para retornar ao menu.");
                                sc.nextLine();
                                break;
                            default:
                                System.out.println(">> OPÇÃO DIGITADA INVÁLIDA.");
                        }
                        System.out.println(System.lineSeparator().repeat(50));
                        break;

                    // PARTICIPAR DE UM EVENTO
                    case "3":
                        System.out.println(System.lineSeparator().repeat(50));
                        EventosUtil.participarEvento(currentUser, sc);
                        System.out.println("Aperte enter para retornar ao menu.");
                        sc.nextLine();
                        break;

                    // EXIBIR EVENTOS CRIADOS, PASSADOS E ACONTECENDO AGORA
                    case "4":
                        System.out.println(System.lineSeparator().repeat(50));
                        System.out.println("Insira a opção desejada para continuar:");
                        System.out.println();
                        System.out.println("(1) - Exibir todos os eventos futuros");
                        System.out.println("(2) - Exibir todos os eventos em andamento");
                        System.out.println("(3) - Exibir todos os eventos passados");
                        System.out.println("---");
                        System.out.println("(0) - VOLTAR AO MENU");
                        String inputExibirEventos = sc.nextLine();
                        switch (inputExibirEventos) {
                            case "1":
                                System.out.println(System.lineSeparator().repeat(50));
                                EventosUtil.exibirEventosFuturos();
                                break;
                            case "2":
                                System.out.println(System.lineSeparator().repeat(50));
                                EventosUtil.exibirEventosAcontecendo();
                                break;
                            case "3":
                                System.out.println(System.lineSeparator().repeat(50));
                                EventosUtil.exibirEventosPassados();
                                break;
                            case "0":
                                break;

                        }
                        System.out.println("Aperte enter para retornar ao menu.");
                        sc.nextLine();
                        break;

                    // LOGOUT
                    case "5":
                        loggedIn = false;
                        currentUser = null;
                        System.out.println(System.lineSeparator().repeat(50));
                        System.out.println(">> CONTA DESLOGADA.");
                        System.out.println("Aperte enter para continuar.");
                        sc.nextLine();
                        break;
                    default:
                        System.out.println(">> OPÇÃO DIGITADA INVÁLIDA.");
                }
            }
        }
    }
}
