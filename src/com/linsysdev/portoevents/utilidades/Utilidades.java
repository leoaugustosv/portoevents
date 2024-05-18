package com.linsysdev.portoevents.utilidades;

import java.util.Scanner;

public class Utilidades {

        public static void exibirAscii() {

                // Ascii Art
                System.out.println(
                                "                                __                                                          __                    ");
                System.out.println(
                                "                                /  |                                                        /  |                   ");
                System.out.println(
                                "  ______    ______    ______   _$$ |_     ______    ______   __     __  ______   _______   _$$ |_    _______       ");
                System.out.println(
                                " /      \\  /      \\  /      \\ / $$   |   /      \\  /      \\ /  \\   /  |/      \\ /       \\ / $$   |  /       |      ");
                System.out.println(
                                "/$$$$$$  |/$$$$$$  |/$$$$$$  |$$$$$$/   /$$$$$$  |/$$$$$$  |$$  \\ /$$//$$$$$$  |$$$$$$$  |$$$$$$/  /$$$$$$$/       ");
                System.out.println(
                                "$$ |  $$ |$$ |  $$ |$$ |  $$/   $$ | __ $$ |  $$ |$$    $$ | $$  /$$/ $$    $$ |$$ |  $$ |  $$ | __$$      \\       ");
                System.out.println(
                                "$$ |__$$ |$$ \\__$$ |$$ |        $$ |/  |$$ \\__$$ |$$$$$$$$/   $$ $$/  $$$$$$$$/ $$ |  $$ |  $$ |/  |$$$$$$  |      ");
                System.out.println(
                                "$$    $$/ $$    $$/ $$ |        $$  $$/ $$    $$/ $$       |   $$$/   $$       |$$ |  $$ |  $$  $$//     $$/       ");
                System.out.println(
                                "$$$$$$$/   $$$$$$/  $$/          $$$$/   $$$$$$/   $$$$$$$/     $/     $$$$$$$/ $$/   $$/    $$$$/ $$$$$$$/        ");
                System.out.println(
                                "$$ |                                                                                                               ");
                System.out.println(
                                "$$ |                                                                                                               ");
                System.out.println(
                                "$$/\n                                                                                                                ");
        }

        public static void opcoesDeslogado() {
                System.out.println("(1) - Login");
                System.out.println("(2) - Criar conta");
                System.out.println();
        }

        public static void opcoesLogado() {
                System.out.println("(1) - Exibir eventos");
                System.out.println("(2) - Cadastrar eventos");
                System.out.println("(3) - Exibir eventos");
                System.out.println("(4) - Exibir eventos");
                System.out.println("(5) - Exibir eventos");
                System.out.println("(6) - Exibir eventos");
                System.out.println("(7) - Sair");
                System.out.println();
        }

        public static void boasVindas() {
                exibirAscii();
                System.out.println("============================================");
                System.out.println("Bem-vindo ao sistema PortoEvents v1.0!");
                System.out.println("============================================");
                System.out.println();
                System.out.println("Insira a opção desejada para continuar:\n");
                opcoesDeslogado();
        }

        public static void boasVindasLogado() {
                System.out.println(System.lineSeparator().repeat(50));
                exibirAscii();
                System.out.println("============================================");
                System.out.println("Bem-vindo ao sistema PortoEvents v1.0!");
                System.out.println("============================================");
                System.out.println();
                System.out.println("Insira a opção desejada para continuar:\n");
                opcoesLogado();
        }
}
