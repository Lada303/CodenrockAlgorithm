package Lada303;
/*
Бернард смотрит, что происходит в парке в разные моменты времени. Он хочет знать в каком состоянии
находились машины в нужное ему время. Они находятся в двух состояниях либо они играют GAME CONTINUES
Либо они находятся на починке GAME OVER
Вам будут даны интервалы времени, когда роботы находились на починке, и время, которое интересовало Бернарда.
Бернард смотрит данные за последний месяц. Поэтому он вводит число и время.
Например 2 -е число, 15 часов 13 минут:   2 15:13
Входные данные: Сначала идёт список роботов в виде: Имя | даты поломки через запятую в виду "DD HH:MM"
Потом идёт список времени, которое интересовало Бернарда в формате "DD HH:MM"
Кол-во роботов не превышает 10, кол-во дат в запросе не больше 10
Время ремонта робота включает в себя границы заданных промежутков времени
Добавлен идентификатор строки R - роботы, T - время
Пример входных данных:
R:Тедди| 4 18:12 - 6 19:32, 17 13:12 - 20 14:42
R:Долорес| 12 14:12 - 12 18:15
R:Мейв| 13 9:21 - 13 21:23, 14 7:23 - 15 12:12 , 17 18:00 - 19 23:22, 22 20:25 - 26 15:14
R:Питер| 8 9:05 - 10 4:55
R:Клементина| 15 4:00 - 16 14:43
T:8 14:21
T:17 19:17
Выходные данные: дата, состояния роботов
Пример выходных данных:
8 14:21
Тедди GAME CONTINUES
Долорес GAME CONTINUES
Мейв GAME CONTINUES
Питер GAME OVER
Клементина GAME CONTINUES
17 19:17
Тедди GAME OVER
Долорес GAME CONTINUES
Мейв GAME OVER
Питер GAME CONTINUES
Клементина GAME CONTINUES
*/
import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        List<String> robotList = new ArrayList<>();
        List<String> askList = new ArrayList<>();
        while(sc.hasNextLine()){
            String line= sc.nextLine();
            if (line.charAt(0) == 'R') {
                line = line.substring(2);
                robotList.add(line);
            } else {
                line = line.substring(2);
                askList.add(line);
            }
        }

        for (String ask : askList) {
            String[] arrAsk = ask.split(" ");
            System.out.println(ask);

            for (String robot : robotList) {
                String[] arrRobot = robot.split("[ ,\\-\\|]");
                boolean flagRepair = false;
                for (int i = 1; i < arrRobot.length; i++) {
                    if (arrRobot[i].isEmpty()) {
                        continue;
                    }
                    if (Integer.parseInt(arrAsk[0]) <= Integer.parseInt(arrRobot[i])) {
                        if (Integer.parseInt(arrAsk[0]) == Integer.parseInt(arrRobot[i])) {
                            i++;
                            if (arrAsk[1].compareToIgnoreCase(arrRobot[i]) != -1) {
                                flagRepair = !flagRepair;
                            }
                        }
                        System.out.println(arrRobot[0] + (flagRepair ? " GAME OVER" : " GAME CONTINUES"));
                        break;
                    } else {
                        flagRepair = !flagRepair;
                        i++;
                        if(i == arrRobot.length - 1) {
                            System.out.println(arrRobot[0] + (flagRepair ? " GAME OVER" : " GAME CONTINUES"));
                            break;
                        }
                    }
                }
            }
        }
    }


   /* public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        List<String> robotList = new ArrayList<>();
        List<String> askList = new ArrayList<>();
        while(sc.hasNextLine()){
            String line= sc.nextLine();
            if (line.charAt(0) == 'R') {
                robotList.add(line);
            } else {
                askList.add(line);
            }
        }

        int indexRobot = 0;
        String[][] repairCalendar = new String[robotList.size()][32];
        for (String robot : robotList) {
            String[] arr = robot.split("\\|");
            repairCalendar[indexRobot][0] = arr[0].substring(2);
            //System.out.println(repairCalendar[indexRobot][0]);
            String[] arrDateTime = arr[1].split("[ ,\\-]");
            // System.out.println(Arrays.toString(arrDateTime));
            int indexDateTime = 1;
            boolean flagRepair = false;
            for (int i = 1; i <= 31; i++) {
                if (i != Integer.parseInt(arrDateTime[indexDateTime])) {
                    repairCalendar[indexRobot][i] = flagRepair ? "O" : "C";
                } else {
                    indexDateTime++;
                    if (repairCalendar[indexRobot][i] == null) {
                        repairCalendar[indexRobot][i] = (flagRepair ? "O " : "C ") + arrDateTime[indexDateTime];
                    } else {
                        repairCalendar[indexRobot][i] += (flagRepair ? " O " : " C ") + arrDateTime[indexDateTime];
                    }
                    flagRepair = !flagRepair;
                    if (indexDateTime == arrDateTime.length - 1) {
                        indexDateTime--;
                        continue;
                    }
                    do {
                        indexDateTime++;
                    }
                    while (arrDateTime[indexDateTime].isEmpty());
                    if (i == Integer.parseInt(arrDateTime[indexDateTime])) {
                        i--;
                    }
                }
            }
            System.out.println(Arrays.toString(repairCalendar[indexRobot]));
            indexRobot++;
        }


    }*/
}
