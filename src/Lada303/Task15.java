package Lada303;

import java.util.*;
/*
Ваша задача написать решатель нонограммы размером 5 на 5.
Входные данные: сначала горизонтальные подсказки, потом вертикальные
При горизонтальных подсказках, если пишется несколько цифр, то сначала написано количество закрашенных
клеток, что расположены выше, потом ниже. При вертикальных, сначала слева, потом справа
Пример входных данных:
4
1,1
5
1,1
3
3
1,3
3,1
1,3
1
Выходные данные: получившаяся картинка записанная в строчку, в пикселях, где X - это буквы эмблемы,
пустое пространство обозначается прочерком -. Пример выходных данных:
XXX--
X-XXX
XXX-X
X-XXX
--X--
*/
public class Task15 {
    private final static int SIZE = 5;
    static List<int[]> verticalList = new ArrayList<>();
    static List<int[]> horizontalList = new ArrayList<>();
    static String[][] arrField = new String[SIZE][SIZE];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int countV = 0;
        int countH = 0;
        String line;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.isEmpty() || line.equals(" ")) {
                continue;
            }
            int[] in = Arrays.stream(line.split("[, ]"))
                    .filter(s -> !s.equals(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (countV < SIZE) {
                verticalList.add(in);
                countV++;
            } else if (countH < SIZE) {
                horizontalList.add(in);
                countH++;
            }
        }
        //verticalList.forEach(strings -> System.out.print(Arrays.toString(strings) + " "));
        //System.out.println();
        //horizontalList.forEach(strings -> System.out.print(Arrays.toString(strings) + " "));
        //System.out.println();
        run();
    }

    private static void run() {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(arrField[i], " ");
        }
        for (int z = 0; z < 3; z++) {
            lookingInList('h');
            lookingInList('v');
            //printArrSq(arrField);
            //System.out.println();
        }
        printArrSq(arrField);
        System.out.println();
    }

    private static void lookingInList(char flag) {
        String[] arrLine = new String[SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                arrLine[j] = flag == 'v' ? arrField[j][i] : arrField[i][j];
            }
            //System.out.println(Arrays.toString(arrLine));
            //String [] str = fillingField(flag == 'v' ?  verticalList.get(i) : horizontalList.get(i), arrLine);
            fillingField(flag == 'v' ?  verticalList.get(i) : horizontalList.get(i), arrLine);
            String some;
            for (int j = 0; j < SIZE; j++) {
                switch (arrLine[j]) {
                    case "X":   some = "X";
                                break;
                    case "-":   some = "-";
                                break;
                    default:    some = " ";
                }
                if (flag == 'v') {
                    arrField[j][i] = some;
                }else {
                    arrField[i][j] = some;
                }
            }
        }
        //printArrSq(arrField);
        //System.out.println();
    }

    private static void fillingField(int[] intN, String[] arrLine) {
        if (intN.length == 1 && intN[0] == SIZE) {
            Arrays.fill(arrLine, intN[0] == 0 ? "-" : "X");
            return;
        }
        fromLeftToRight(intN, arrLine);
        searchFilledLines(intN, arrLine);
        //System.out.println(Arrays.toString(arrLine));
        fromRightToLeft(intN, arrLine);
        searchFilledLines(intN, arrLine);
        //System.out.println(Arrays.toString(arrLine));
    }

    private static void fromLeftToRight(int[] intN, String[] arrLine) {
        int start = 0;
        for (int j = 0; j < intN.length; j++) {
            boolean isX = false;
            for (int k = 0; k < intN[j]; k++) {
                if (k == 0 && arrLine[start + k].equals("X")) {
                    isX = true;
                }
                if (arrLine[start + k].equals("-")) {
                    start = start + k + 1;
                    k = -1;
                    //System.out.println(start + " " + k);
                    continue;
                }
                if (arrLine[start + k].equals("X") || isX) {
                    //System.out.println(start + " " + k);
                    arrLine[start + k] = "X";
                } else {
                    arrLine[start + k] = j + "." + intN[j];
                }
            }
            start += intN[j] + 1;
        }
        //System.out.println(Arrays.toString(arrLine));
    }

    private static void fromRightToLeft(int[] intN, String[] arrLine) {
        int start = SIZE - 1;
        for (int j = intN.length - 1; j >= 0; j--) {
            boolean isX = false;
            for (int k = 0; k < intN[j]; k++) {
                if (k == 0 && arrLine[start - k].equals("X")) {
                    isX = true;
                }
                if (arrLine[start - k].equals("-")) {
                    start = start - k - 1;
                    k = -1;
                    continue;
                }
                if (arrLine[start - k].equals(j + "." + intN[j]) || arrLine[start - k].equals("X") || isX) {
                    arrLine[start - k] = "X";
                } else {
                    arrLine[start - k] = j + "." + intN[j];
                }
            }
            start -= (intN[j] + 1);
        }
        //System.out.println(Arrays.toString(arrLine));
    }

    private static void searchFilledLines(int[] intN, String[] arrLine){
        int sumN = 0;
        int countX = 0;
        for (int j : intN) {
            sumN += j;
        }
        for (int i = 0; i < SIZE; i++) {
            if(arrLine[i].equals("X")) {
                countX++;
            }
        }
        if (sumN == countX) {
            for (int i = 0; i < SIZE; i++) {
                if (!arrLine[i].equals("X")) {
                    arrLine[i] = "-";
                }
            }
        }
    }

    private static void printArrSq(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
