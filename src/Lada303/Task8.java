package Lada303;
/*
Шифр четырех квадратов
 */

import java.util.Scanner;

public class Task8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        char[][] arrCipher = new char[10][10];
        int counterMatrix = 1;
        int counterRows = 0;
        int counterColumns = 0;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            if (line == null || line.isEmpty() || line == "") {continue;}
            if (counterRows == 10 && counterMatrix == 4) {
                arrCipher[6][9] = 'K';
                break;
            }
            int l = 0;
            for (int j = 0; j < 5; j++) {
                arrCipher[counterRows][j + counterColumns] = line.charAt(l);
                l += 2;
            }
            counterRows++;
            if (counterRows == 5 && counterMatrix == 1) {
                arrCipher[1][4] = 'K';
                counterMatrix++;
                counterRows = 0;
                counterColumns = 5;

                continue;
            }
            if (counterRows == 5 && counterMatrix == 2) {
                counterMatrix++;
                counterColumns = 0;
                continue;
            }
            if (counterRows == 10 && counterMatrix == 3) {
                counterMatrix++;
                counterRows = 5;
                counterColumns = 5;
                continue;
            }
        }
        //printArrSq(arrCipher);
        line = line.replace(" ", "");
        line = line.toUpperCase();
        //System.out.println(line);
        coding(line, arrCipher);
    }

    private static void coding (String line, char[][] arrCipher) {
        for (int z = 0; z < line.length() - 1; z = z + 2) {
            int y1 = -1;
            int x1 = -1;
            int y2 = -1;
            int x2 = -1;
            M: for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (line.charAt(z) == arrCipher[i][j]) {
                        y1 = i;
                        x1 = j;
                    }
                    if (line.charAt(z + 1) == arrCipher[i + 5][j + 5]) {
                        y2 = i;
                        x2 = j;
                    }
                    if (y1 != -1 && x1 != - 1 && y2 != -1 && x2 != -1) {
                        System.out.print("" + arrCipher[y1][x2 + 5] + arrCipher[y2 + 5][x1]);
                        break M;
                    }
                }
            }
        }
        if (line.length() % 2 != 0) {
            System.out.println(line.charAt(line.length()-1));
        }
    }


    private static void fillABCRus(char[][] arrCipher) {
        char letter = 'а';
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 1 && j == 0) {
                    arrCipher[i][j] = 'ё';
                    arrCipher[i + 6][j + 6] = 'ё';
                    continue;
                }
                arrCipher[i][j] = letter;
                arrCipher[i + 6][j + 6] = letter;
                letter++;
                if (letter > 1104) {
                    arrCipher[i][j] = '*';
                    arrCipher[i + 6][j + 6] = '*';
                }
            }
        }
    }

    private static void fillWordInArrRus(char[][] arrCipher, String word, int y, int x) {
        int index = 0;
        char letter = 'а';
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (index <= word.length()) {
                    if (index == word.length()) {
                        arrCipher[i + y][j + x] = 'ё';
                        index++;
                        continue;
                    }
                    arrCipher[i + y][j + x] = word.charAt(index);
                    index++;
                } else {
                    while (word.contains(Character.toString(letter))) {
                        letter++;
                    }
                    arrCipher[i + y][j + x] = letter;
                    letter++;
                    if (letter > 1104) {
                        arrCipher[i + y][j + x] = '*';
                    }
                }
            }
        }
    }

    private static void printArrSq(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
                if (j == 4) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            if (i == 4) {
                System.out.println(" ");
            }
        }
    }
}
