package Lada303;

import java.util.Scanner;
/* RLE - самый простой алгоритм сжатия. Его суть состоит в замене повторяющихся данных образцом, и
количеством повтора образца. Алгоритм подходит для сжатия данных, имеющих большое количество повторений.
При сжатии учитывайте регистр.
Напишите программу, которая реализует RLE для строк, состоящих из букв латинского алфавита, не имеющих пробелы.
Во входных данных только одна строка.
Например: DDDDFFFFHHHHk → 4D,4F,4H,1k*/
public class Task4 {

    public static void main (String[] args) throws java.lang.Exception {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        String line= sc.nextLine();
        char letter = line.charAt(0);
        int counterLetter = 1;
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == letter) {
                counterLetter++;
            } else {
                System.out.print("" + counterLetter + letter + ",");
                letter = line.charAt(i);
                counterLetter = 1;
            }
            if (i == line.length() - 1) {
                System.out.print("" + counterLetter + letter);
            }
        }
        System.out.println();
    }
}
