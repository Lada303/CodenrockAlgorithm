package Lada303;

import java.util.Arrays;
import java.util.Scanner;
/*
Дарлин замерила расстояние от входа в замочную скважину, до начала каждого барьера, они равны целым числам.
Чтобы взломать замок, достаточно вставить в каждый промежуток между барьерами,а так же до первого барьера и
после последнего палочки диаметром 1 и повернуть их одновременно.
Расстояние между барьерами во входных данных не может быть меньше чем 1. Каждый барьер толщиной 1.
Барьеров может быть от 1 до 3.
Ваша задача вывести модель самодельного ключа Дарлин. Где каждая палочка будет надета на основу,
равную длине замка, каждая палочка высотой 3, в каждом промежутке между барьерами каждая палочка будет
ближе к правой стороне.
Входные данные: расстояния от начала замочной скважины, до каждого барьера, и общая длина замочной скважины
Пример выходных данных: ,3,6,8
Выходные данные: нарисованная модель ключа Дарлин. Где X - ключ, 0 - пустое пространство.
Пример выходных данных:
X0X00X0X
X0X00X0X
X0X00X0X
XXXXXXXX
 */
public class Task3 {

    public static void main (String[] args)
    {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();
        String[] arrLock = input.split(",");

        char[] arrAnswer = new char[Integer.parseInt(arrLock[arrLock.length - 1])];
        Arrays.fill(arrAnswer, '0');
        for (int i = 0; i <= arrLock.length - 2; i++) {
            int barrier = Integer.parseInt(arrLock[i]);
            arrAnswer[barrier - 1] = 'X';
        }
        arrAnswer[arrAnswer.length - 1] = 'X';

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j < arrAnswer.length; j++) {
                System.out.print(i != 3 ? arrAnswer[j] : 'X');
            }
            System.out.println();
        }

    }
}
