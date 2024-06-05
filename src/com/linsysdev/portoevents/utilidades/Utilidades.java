package com.linsysdev.portoevents.utilidades;

public class Utilidades {

        // LOGO DO PORTO EVENTS
        private static void exibirLogo() {

                

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

        // METODOS PARA EXIBICAO DE OPCOES

        private static void opcoesDeslogado() {
                System.out.println("(1) - Login");
                System.out.println("(2) - Criar conta");
                System.out.println("(3) - Sair");
                System.out.println();
        }

        private static void opcoesLogado() {
                System.out.println("(1) - Criar novo evento");
                System.out.println("(2) - Meus eventos");
                System.out.println("(3) - Participar de um evento");
                System.out.println("(4) - Ver todos os eventos");
                System.out.println("(5) - Deslogar");
                System.out.println();
        }

        public static void boasVindas() {
                exibirLogo();
                System.out.println("============================================");
                System.out.println("Bem-vindo ao sistema PortoEvents v1.0!");
                System.out.println("============================================");
                System.out.println();
                System.out.println("Insira a opção desejada para continuar:\n");
                opcoesDeslogado();
        }

        public static void boasVindasLogado() {
                System.out.println(System.lineSeparator().repeat(50));
                exibirLogo();
                System.out.println("============================================");
                System.out.println("Bem-vindo ao sistema PortoEvents v1.0!");
                System.out.println("============================================");
                System.out.println();
                System.out.println("Insira a opção desejada para continuar:\n");
                opcoesLogado();
        }
}
