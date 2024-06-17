/*
* Desenvolvido pelos alunos:
* Elton Lopes de Meneses
* Guilherme Cabral Mendes Mariano
* Leonardo Augusto Silveira
* Data da última revisão: 16/06/2024
*/

package com.linsysdev.portoevents.eventos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EventosUtil extends Eventos {

    // EXIBIR TODOS OS EVENTOS FUTUROS, ORDENANDO DO MAIS RECENTE AO MAIS DISTANTE
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
            fsc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EXIBIR TODOS OS EVENTOS PASSADOS, ORDENANDO DO MAIS RECENTE AO MAIS DISTANTE
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
            fsc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // PARTICIPAR DE UM EVENTO FUTURO
    public static void participarEvento(String currentUser, Scanner sc) {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                int contagemLinhas = 0;
                List<Integer> eventosDisponiveis = new ArrayList<>();
                while (fsc.hasNextLine()) {
                    contagemLinhas++;
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    String[] participantes = new String[0];
                    try {
                        participantes = eventdata[12].split(",");
                    } catch (Exception e) {
                        
                    }
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

                }

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Não há novos eventos disponíveis para você participar.");
                    System.out.println();
                } else {

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
                            input = Integer.parseInt(sc.nextLine());

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

                        List<String> linhasEventos = Files.readAllLines(Paths.get("data/events.data"));
                        int linha = eventosDisponiveis.get(input - 1);
                        String nome = listaEventos.get(input - 1)[0];

                        linhasEventos.set(linha - 1, linhasEventos.get(linha - 1) + "," + currentUser);
                        Files.write(Paths.get("data/events.data"), linhasEventos);
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
            fsc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EXIBIR TODOS OS EVENTOS EM QUE O USUARIO JA PARTICIPOU, ORDENANDO DO MAIS RECENTE AO MAIS DISTANTE
    public static void exibirMeusEventosPassados(String currentUser) {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                while (fsc.hasNextLine()) {
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    String[] participantes = new String[0];
                    try {
                        participantes = eventdata[12].split(",");
                    } catch (Exception e) {
                        
                        continue;
                    }
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
            fsc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EXIBIR TODOS OS EVENTOS EM QUE O USUARIO PARTICIPARÁ, ORDENANDO DO MAIS PRÓXIMO AO MAIS DISTANTE
    public static void exibirMeusEventosFuturos(String currentUser) {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                while (fsc.hasNextLine()) {
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    String[] participantes = new String[0];
                    try {
                        participantes = eventdata[12].split(",");
                    } catch (Exception e) {
                        
                        continue;
                    }
                    
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

                    System.out.println(">> INFO: Eventos com a participação confirmada: " + contagemEventos + ".");
                    System.out.println();
                }
            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
            }
            fsc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EXIBIR TODOS OS EVENTOS EM QUE O USUARIO PARTICIPARÁ, E PERMITIR CANCELAR PARTICIPACAO EM UM DELES
    public static void cancelarParticipacao(String currentUser, Scanner sc) {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                int contagemLinhas = 0;
                List<Integer> eventosDisponiveis = new ArrayList<>();
                while (fsc.hasNextLine()) {
                    contagemLinhas++;
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    String[] participantes = new String[0];
                    try {
                        participantes = eventdata[12].split(",");
                    } catch (Exception e) {
                        
                        continue;
                    }
                    boolean estaParticipando = false;

                    for (String p : participantes) {
                        if (p.equals(currentUser)) {
                            estaParticipando = true;
                            break;
                        }
                    }
                    if (estaParticipando && LocalDateTime.parse(eventdata[9]).isAfter(LocalDateTime.now())) {
                        eventosDisponiveis.add(contagemLinhas);
                        listaEventos.add(eventdata);
                        contagemEventos++;
                    }

                }

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Você ainda não está participando de nenhum evento.");
                    System.out.println();
                } else {

                    System.out.println("========= EVENTOS COM PRESENÇA CONFIRMADA =========");
                    System.out
                            .println(
                                    "Aqui estão os detalhes dos eventos disponíveis em que você vai participar:");
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
                    System.out.println(">> INFO: Eventos em que participarei: " + contagemEventos + ".");
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
                        System.out.println(
                                "Insira o número correspondente ao evento que você deseja cancelar a participação: ");
                        System.out.println();
                        System.out.printf("Nº do evento: --> ");
                        try {
                            input = Integer.parseInt(sc.nextLine());

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

                        List<String> linhasEventos = Files.readAllLines(Paths.get("data/events.data"));
                        int linha = eventosDisponiveis.get(input - 1);
                        String nome = listaEventos.get(input - 1)[0];

                        linhasEventos.set(linha - 1, linhasEventos.get(linha - 1).replace("," + currentUser, ""));
                        Files.write(Paths.get("data/events.data"), linhasEventos);
                        System.out.println();
                        System.out.println();
                        System.out.println(
                                ">> CONCLUÍDO: Participação cancelada no evento \"" + nome + "\" (id: " + linha
                                        + ") com sucesso.");
                        System.out.println();
                    }
                }

            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
            }
            fsc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EXIBIR TODOS OS EVENTOS QUE ESTAO ACONTECENDO AGORA
    public static void exibirEventosAcontecendo() {
        try {

            Scanner fsc = new Scanner(createEventsData());

            if (fsc.hasNextLine()) {
                List<String[]> listaEventos = new ArrayList<>();
                int contagemEventos = 0;
                while (fsc.hasNextLine()) {
                    String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));
                    LocalDateTime horaInicio = LocalDateTime.parse(eventdata[9]);
                    LocalDateTime horaFim = LocalDateTime.parse(eventdata[9])
                            .plusMinutes(Integer.parseInt(eventdata[10]));
                    LocalDateTime horaAtual = LocalDateTime.now();

                    if ((horaInicio.isBefore(horaAtual) || horaInicio.isEqual(horaAtual)) &&
                            (horaFim.isAfter(horaAtual) || horaFim.isEqual(horaAtual))) {
                        listaEventos.add(eventdata);
                        contagemEventos++;
                    }

                }

                if (listaEventos.isEmpty()) {
                    System.out.println(">> INFO: Não há nenhum evento ocorrendo no momento.");
                    System.out.println();
                } else {

                    listaEventos.sort((String[] o1, String[] o2) -> o1[9].compareTo(o2[9]));

                    System.out.println("========= EVENTOS OCORRENDO AGORA =========");
                    System.out
                            .println("Aqui estão os eventos que estão ocorrendo agora:");
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

                    System.out.println(">> INFO: Eventos acontecendo agora: " + contagemEventos + ".");
                    System.out.println();
                }
            } else {
                System.out.println(">> INFO: Não existem eventos cadastrados em sistema.");
                System.out.println();
            }
            fsc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
