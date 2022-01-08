package Lada303;
/*
Зишифруйте сообщение меняя буквы на их порядковый номер в алфавите. Пробелы при этом не учитывать.
Строки будут даны без знаков препинания, только с пробелами. Регистр не учитывать.
Входные данные: шифруемая строка, длиной до 1000 символов, на латинице
Пример входных данных: MR Robot
Выходные данные: через запятую порядковый номер букв в алфавите
Пример выходных данных: 13,18,18,15,2,15,20
 */
import java.util.Scanner;
public class Task1 {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        int flag = 0;
        while(sc.hasNextLine()){ // get the input
            String line= sc.nextLine();
            line = line.toLowerCase();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) >= 97 && line.charAt(i) <= 122) {
                    System.out.print( flag == 0 ? (int)(line.charAt(i) - 96) : "," + (int)(line.charAt(i) - 96));
                    flag++;
                }
            }
        }
    }
}
