package com.linsysdev.portoevents;

import com.linsysdev.portoevents.utilidades.*;
import com.linsysdev.portoevents.autenticacao.*;
import com.linsysdev.portoevents.eventos.Eventos;
import com.linsysdev.portoevents.eventos.EventosUtil;
import com.linsysdev.portoevents.usuarios.Usuarios;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean exit = false;
        boolean loggedIn = false;
        String currentUser = "";

        Scanner sc = new Scanner(System.in, "Cp850");

        while (!exit) {
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

                        Autenticacao auth = new Autenticacao(input_cpf, input_senha);

                        if (auth.autenticar() == false) {
                            System.out.println();
                            System.out.println("Aperte enter para continuar.");
                            sc.nextLine();
                            continue;
                        } else {
                            loggedIn = true;
                            currentUser = auth.getCpf();
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
                Utilidades.boasVindasLogado();
                System.out.printf("--> ");
                String input = sc.nextLine();

                switch (input) {
                    case "1":
                        Eventos event = new Eventos(currentUser);
                        event.criarEvento(sc);
                        break;
                    case "2":
                        System.out.println(System.lineSeparator().repeat(50));
                        System.out.println("Insira a opção desejada para continuar:");
                        System.out.println();
                        System.out.println("(1) - Exibir meus eventos futuros");
                        System.out.println("(2) - Exibir meus eventos passados");
                        System.out.println("(3) - VOLTAR AO MENU");
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
                                System.out.println();
                                System.out.println("Aperte enter para retornar ao menu.");
                                sc.nextLine();
                                break;
                            default:
                                System.out.println(">> OPÇÃO DIGITADA INVÁLIDA.");
                        }
                        System.out.println(System.lineSeparator().repeat(50));
                        break;
                    case "3":
                        System.out.println(System.lineSeparator().repeat(50));
                        EventosUtil.participarEvento(currentUser, sc);
                        System.out.println("Aperte enter para retornar ao menu.");
                        sc.nextLine();
                        break;
                    case "4":
                        System.out.println(System.lineSeparator().repeat(50));
                        EventosUtil.exibirEventosFuturos();
                        System.out.println("Aperte enter para retornar ao menu.");
                        sc.nextLine();
                        break;
                    case "5":

                        System.out.println(System.lineSeparator().repeat(50));
                        EventosUtil.exibirEventosPassados();
                        System.out.println("Aperte enter para retornar ao menu.");
                        sc.nextLine();
                        break;

                    case "6":
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
