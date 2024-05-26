package com.linsysdev.portoevents.eventos;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EventosUtil extends Eventos {
    public static void exibirEventosFuturos() {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                while (fsc.hasNextLine()) {
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));

                    if (LocalDateTime.parse(eventdata[9]).isAfter(LocalDateTime.now())) {
                        listaEventos.add(eventdata);
                        contagemEventos++;
                    }

                }

                listaEventos.sort((String[] o1, String[] o2) -> o1[9].compareTo(o2[9]));

                System.out.println("========= PRÓXIMOS EVENTOS =========");
                System.out.println("Aqui estão os próximos eventos, do mais próximo ao mais distante: ");
                System.out.println();
                int numeroEvento = 1;

                for (String[] e : listaEventos) {
                    System.out.println("EVENTO " + (numeroEvento));
                    System.out.println("==================================");
                    System.out.println("> NOME: " + e[0]);
                    System.out.println(
                            "> ENDEREÇO: " + e[1] + ", " + e[2] + " (" + e[3] + ") - " + e[8] + " - " + e[4] + " - "
                                    + e[5] + ", " + e[6]);
                    System.out.println();
                    System.out.println("> HORA DE INÍCIO: " + e[9]);
                    System.out.println(
                            "> HORA DO TÉRMINO: " + LocalDateTime.parse(e[9]).plusMinutes(Integer.parseInt(e[10])));
                    System.out.println();
                    System.out.println("> CATEGORIA: " + e[8]);
                    System.out.println("> DESCRIÇÃO: " + e[11]);
                    System.out.println("==================================");
                    System.out.println();
                    numeroEvento++;
                }

                System.out.println(">> INFO: Eventos futuros: " + contagemEventos + ".");
                System.out.println();
            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exibirEventosPassados() {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                while (fsc.hasNextLine()) {
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));

                    if (LocalDateTime.parse(eventdata[9]).isBefore(LocalDateTime.now())) {
                        listaEventos.add(eventdata);
                        contagemEventos++;
                    }

                }

                listaEventos.sort((String[] o1, String[] o2) -> o2[9].compareTo(o1[9]));

                System.out.println("========= EVENTOS PASSADOS =========");
                System.out.println("Aqui estão os próximos eventos, do mais próximo ao mais distante: ");
                System.out.println();
                int numeroEvento = 1;

                for (String[] e : listaEventos) {
                    System.out.println("EVENTO " + (numeroEvento));
                    System.out.println("==================================");
                    System.out.println("> NOME: " + e[0]);
                    System.out.println(
                            "> ENDEREÇO: " + e[1] + ", " + e[2] + " (" + e[3] + ") - " + e[8] + " - " + e[4] + " - "
                                    + e[5] + ", " + e[6]);
                    System.out.println();
                    System.out.println("> HORA DE INÍCIO: " + e[9]);
                    System.out.println(
                            "> HORA DO TÉRMINO: " + LocalDateTime.parse(e[9]).plusMinutes(Integer.parseInt(e[10])));
                    System.out.println();
                    System.out.println("> CATEGORIA: " + e[8]);
                    System.out.println("> DESCRIÇÃO: " + e[11]);
                    System.out.println("==================================");
                    System.out.println();
                    numeroEvento++;
                }

                System.out.println(">> INFO: Eventos passados: " + contagemEventos + ".");
                System.out.println();
            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void participarEvento(String currentUser) {

    }

    public static void exibirMeusEventos(String currentUser) {

    }
}
