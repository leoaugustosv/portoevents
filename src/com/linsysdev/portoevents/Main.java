package com.linsysdev.portoevents;

import com.linsysdev.portoevents.utilidades.*;
import com.linsysdev.portoevents.autenticacao.*;
import com.linsysdev.portoevents.usuarios.Usuarios;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean loggedIn = false;
        String currentUser;

        Scanner sc = new Scanner(System.in);

        while (true) {
            if (!loggedIn) {
                Utilidades.boasVindas();
                System.out.printf("--> ");
                int input = sc.nextInt();
                sc.nextLine();

                switch (input) {
                    case 1:
                        System.out.println();
                        System.out.printf("LOGIN --> ");
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
                    case 2:
                        Usuarios newuser = new Usuarios();
                        if (newuser.cadastrar(sc) == false) {
                            continue;
                        } else {
                            loggedIn = true;
                            currentUser = newuser.getCpf();
                        }
                        break;
                    default:
                        System.out.println(">> OPÇÃO DIGITADA INVÁLIDA.");

                }

            } else if (loggedIn) {
                Utilidades.boasVindasLogado();
                System.out.printf("--> ");
                int input = sc.nextInt();
                sc.nextLine();

                switch (input) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:
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
