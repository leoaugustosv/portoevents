package com.linsysdev.portoevents;

import com.linsysdev.portoevents.utilidades.*;
import com.linsysdev.portoevents.autenticacao.*;
import com.linsysdev.portoevents.usuarios.Usuarios;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {

            Utilidades.boasVindas();
            System.out.printf("--> ");
            int input = sc.nextInt();
            sc.nextLine();
            if (input == 1) {
                System.out.printf("LOGIN --> ");
                String input_cpf = sc.nextLine();
                System.out.println();
                System.out.printf("SENHA --> ");
                String input_senha = sc.nextLine();

                Autenticacao auth = new Autenticacao(input_cpf, input_senha);

                if (auth.autenticar() == false) {
                    continue;
                } else {
                    break;
                }

            } else if (input == 2) {
                Usuarios newuser = new Usuarios();
                if (newuser.cadastrar(sc) == false) {
                    continue;
                } else {
                    break;
                }

            }

        }
    }
}
