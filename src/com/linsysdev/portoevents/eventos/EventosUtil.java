package com.linsysdev.portoevents.eventos;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
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

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Não constam eventos futuros em sistema.");
                    System.out.println();
                } else {

                    listaEventos.sort((String[] o1, String[] o2) -> o1[9].compareTo(o2[9]));

                    System.out.println("========= PRÓXIMOS EVENTOS =========");
                    System.out
                            .println("Aqui estão os eventos que ainda acontecerão, do mais próximo ao mais distante:");
                    System.out.println();
                    int numeroEvento = 1;

                    for (String[] e : listaEventos) {
                        System.out.println("EVENTO " + (numeroEvento));
                        System.out.println("====================================================================");
                        System.out.println("> NOME: " + e[0]);
                        System.out.println(
                                "> ENDEREÇO: " + e[1] + ", " + e[2] + " (" + e[3] + ") - " + e[8] + " - " + e[4] + " - "
                                        + e[5] + ", " + e[6]);
                        System.out.println();
                        System.out.println("> HORA DE INÍCIO: "
                                + LocalDateTime.parse(e[9]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println(
                                "> HORA DO TÉRMINO: " + LocalDateTime.parse(e[9]).plusMinutes(Integer.parseInt(e[10]))
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println();
                        System.out.println("> CATEGORIA: " + e[8]);
                        System.out.println("> DESCRIÇÃO: " + e[11]);
                        System.out.println("====================================================================");
                        System.out.println();
                        numeroEvento++;
                    }

                    System.out.println(">> INFO: Eventos futuros: " + contagemEventos + ".");
                    System.out.println();
                }
            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
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

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Não existem eventos já ocorridos até o momento.");
                    System.out.println();
                } else {
                    listaEventos.sort((String[] o1, String[] o2) -> o2[9].compareTo(o1[9]));

                    System.out.println("========= EVENTOS PASSADOS =========");
                    System.out.println("Aqui estão os eventos que já aconteceram, do mais recente ao mais antigo:");
                    System.out.println();
                    int numeroEvento = 1;

                    for (String[] e : listaEventos) {
                        System.out.println("EVENTO " + (numeroEvento));
                        System.out.println("====================================================================");
                        System.out.println("> NOME: " + e[0]);
                        System.out.println(
                                "> ENDEREÇO: " + e[1] + ", " + e[2] + " (" + e[3] + ") - " + e[8] + " - " + e[4] + " - "
                                        + e[5] + ", " + e[6]);
                        System.out.println();
                        System.out.println("> HORA DE INÍCIO: "
                                + LocalDateTime.parse(e[9]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println(
                                "> HORA DO TÉRMINO: "
                                        + LocalDateTime.parse(e[9])
                                                .plusMinutes(Integer.parseInt(e[10]))
                                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println();
                        System.out.println("> CATEGORIA: " + e[8]);
                        System.out.println("> DESCRIÇÃO: " + e[11]);
                        System.out.println("====================================================================");
                        System.out.println();
                        numeroEvento++;
                    }

                    System.out.println(">> INFO: Eventos passados: " + contagemEventos + ".");
                    System.out.println();
                }

            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void participarEvento(String currentUser, Scanner sc) {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                int contagemLinhas = 0;
                List<Integer> eventosDisponiveis = new ArrayList<>();
                while (fsc.hasNextLine()) {

                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    String[] participantes = eventdata[12].split(",");
                    boolean estaParticipando = false;

                    for (String p : participantes) {
                        if (p.equals(currentUser)) {
                            estaParticipando = true;
                            break;
                        }
                    }
                    if (!estaParticipando && LocalDateTime.parse(eventdata[9]).isAfter(LocalDateTime.now())) {
                        eventosDisponiveis.add(contagemLinhas);
                        listaEventos.add(eventdata);
                        contagemEventos++;
                    }
                    contagemLinhas++;
                }

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Não há novos eventos disponíveis para você participar.");
                    System.out.println();
                } else {

                    listaEventos.sort((String[] o1, String[] o2) -> o1[9].compareTo(o2[9]));

                    System.out.println("========= EVENTOS DISPONÍVEIS =========");
                    System.out
                            .println(
                                    "Aqui estão os detalhes dos eventos disponíveis para participar:");
                    System.out.println();
                    int numeroEvento = 1;

                    for (String[] e : listaEventos) {
                        System.out.println("EVENTO " + (numeroEvento));
                        System.out.println("====================================================================");
                        System.out.println("> NOME: " + e[0]);
                        System.out.println(
                                "> ENDEREÇO: " + e[1] + ", " + e[2] + " (" + e[3] + ") - " + e[8] + " - " + e[4] + " - "
                                        + e[5] + ", " + e[6]);
                        System.out.println();
                        System.out.println("> HORA DE INÍCIO: "
                                + LocalDateTime.parse(e[9]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println(
                                "> HORA DO TÉRMINO: " + LocalDateTime.parse(e[9]).plusMinutes(Integer.parseInt(e[10]))
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println();
                        System.out.println("> CATEGORIA: " + e[8]);
                        System.out.println("> DESCRIÇÃO: " + e[11]);
                        System.out.println();
                        System.out.println("> STATUS: NÃO PARTICIPANDO");
                        System.out.println("====================================================================");
                        System.out.println();
                        numeroEvento++;
                    }
                    System.out.println(">> INFO: Eventos disponíveis: " + contagemEventos + ".");
                    System.out.println();
                    System.out.println("--------------------------------------------------");

                    for (int i = 0; i < eventosDisponiveis.size(); i++) {
                        System.out.println("(" + (i + 1) + ") - " + listaEventos.get(i)[0] + ": "
                                + LocalDateTime.parse(listaEventos.get(i)[9])
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                                + " - "
                                + LocalDateTime.parse(listaEventos.get(i)[9])
                                        .plusMinutes(Integer.parseInt(listaEventos.get(i)[10]))
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                    }
                    System.out.println("---");
                    System.out.println("(0) - MENU");
                    System.out.println("--------------------------------------------------");

                    int input = 0;
                    do {
                        System.out.println();
                        System.out.println();
                        System.out.println("Insira o número correspondente ao evento que você deseja participar: ");
                        System.out.println();
                        System.out.printf("Nº do evento: --> ");
                        try {
                            input = sc.nextInt();
                            sc.nextLine();

                        } catch (Exception e) {
                            System.out.println();
                            System.out.println("ERRO: Digite um número válido.");
                        }
                    } while (input != 0 && input > eventosDisponiveis.size());

                    if (input == 0) {
                        System.out.println();
                        System.out.println();
                        System.out.println(">> INFO: Voltando ao menu principal...");
                        System.out.println();
                    } else {

                        int linha = eventosDisponiveis.get(input - 1);
                        String nome = listaEventos.get(input - 1)[0];

                        System.out.println();
                        System.out.println();
                        System.out.println(
                                ">> CONCLUÍDO: Participação confirmada no evento \"" + nome + "\" (id: " + linha
                                        + ") com sucesso.");
                        System.out.println();
                    }
                }

            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exibirMeusEventosPassados(String currentUser) {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                while (fsc.hasNextLine()) {
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    String[] participantes = eventdata[12].split(",");
                    boolean estaParticipando = false;

                    for (String p : participantes) {
                        if (p.equals(currentUser)) {
                            estaParticipando = true;
                            break;
                        }
                    }

                    if (estaParticipando && LocalDateTime.parse(eventdata[9]).isBefore(LocalDateTime.now())) {
                        listaEventos.add(eventdata);
                        contagemEventos++;
                    }

                }

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Você ainda não participou de nenhum evento até o momento.");
                    System.out.println();
                } else {
                    listaEventos.sort((String[] o1, String[] o2) -> o2[9].compareTo(o1[9]));

                    System.out.println("========= MEUS EVENTOS PASSADOS =========");
                    System.out.println("Aqui estão os eventos em que você participou, do mais recente ao mais antigo:");
                    System.out.println();
                    int numeroEvento = 1;

                    for (String[] e : listaEventos) {
                        System.out.println("EVENTO " + (numeroEvento));
                        System.out.println("====================================================================");
                        System.out.println("> NOME: " + e[0]);
                        System.out.println(
                                "> ENDEREÇO: " + e[1] + ", " + e[2] + " (" + e[3] + ") - " + e[8] + " - " + e[4] + " - "
                                        + e[5] + ", " + e[6]);
                        System.out.println();
                        System.out.println("> HORA DE INÍCIO: "
                                + LocalDateTime.parse(e[9]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println(
                                "> HORA DO TÉRMINO: "
                                        + LocalDateTime.parse(e[9])
                                                .plusMinutes(Integer.parseInt(e[10]))
                                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println();
                        System.out.println("> CATEGORIA: " + e[8]);
                        System.out.println("> DESCRIÇÃO: " + e[11]);
                        System.out.println();
                        System.out.println("> STATUS: PARTICIPADO");
                        System.out.println("====================================================================");
                        System.out.println();
                        numeroEvento++;
                    }

                    System.out.println(">> INFO: Eventos já participados: " + contagemEventos + ".");
                    System.out.println();
                }

            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exibirMeusEventosFuturos(String currentUser) {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                while (fsc.hasNextLine()) {
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    String[] participantes = eventdata[12].split(",");
                    boolean estaParticipando = false;

                    for (String p : participantes) {
                        if (p.equals(currentUser)) {
                            estaParticipando = true;
                            break;
                        }
                    }
                    if (estaParticipando && LocalDateTime.parse(eventdata[9]).isAfter(LocalDateTime.now())) {
                        listaEventos.add(eventdata);
                        contagemEventos++;
                    }

                }

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Você ainda não está participando de nenhum evento.");
                    System.out.println();
                } else {

                    listaEventos.sort((String[] o1, String[] o2) -> o1[9].compareTo(o2[9]));

                    System.out.println("========= MEUS PRÓXIMOS EVENTOS =========");
                    System.out
                            .println("Aqui estão os eventos que você participará, do mais próximo ao mais distante:");
                    System.out.println();
                    int numeroEvento = 1;

                    for (String[] e : listaEventos) {
                        System.out.println("EVENTO " + (numeroEvento));
                        System.out.println("====================================================================");
                        System.out.println("> NOME: " + e[0]);
                        System.out.println(
                                "> ENDEREÇO: " + e[1] + ", " + e[2] + " (" + e[3] + ") - " + e[8] + " - " + e[4] + " - "
                                        + e[5] + ", " + e[6]);
                        System.out.println();
                        System.out.println("> HORA DE INÍCIO: "
                                + LocalDateTime.parse(e[9]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println(
                                "> HORA DO TÉRMINO: " + LocalDateTime.parse(e[9]).plusMinutes(Integer.parseInt(e[10]))
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                        System.out.println();
                        System.out.println("> CATEGORIA: " + e[8]);
                        System.out.println("> DESCRIÇÃO: " + e[11]);
                        System.out.println();
                        System.out.println("> STATUS: PARTICIPANTE");
                        System.out.println("====================================================================");
                        System.out.println();
                        numeroEvento++;
                    }

                    System.out.println(">> INFO: Eventos futuros: " + contagemEventos + ".");
                    System.out.println();
                }
            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
