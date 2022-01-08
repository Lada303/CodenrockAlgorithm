package Lada303;
/*
Долорес с Уильямом попали в необычное минное поле. Поле прямоугольное, поделено на меньшие клетки,
мины расположены в клетках в шахматном порядке.
Мины могут ранить только Долорес, но ни Уильяма. Мины взрываются от Уильяма, но не от Долорес.
Если на мину наступит Уильям, то эта мина взорвется и взорвутся все по диагонали от нее.
В первый момент все мины целы, после чего Долорес и Уильям делают шаг, каждый в заданную им сторону,
при этом Долорес может ранить, если она окажется в клетке со взываемой миной.
Изначально Уильям и Долорес стоят на пустом пространстве. Ваша задача найти ранит ли мина Долорес.
Входные данные: минное поле, вместе с расположением Долорес и Уйльяма
Шаги в одном из направлений: right, left, up, down. Сначала шаг Долорес, потом Уйльяма
Шаг делается только один
Обозначения: Минное поле размеров до 10 на 10
0 - пустое пространство
X - изображены мины
D - местоположение Долорес
W - местоположение Уильяма
Пример входных данных:
X0X0X0X0X
0X0X0X0X0
XWX0X0X0X
0X0X0X0X0
X0X0X0X0X
0X0X0X0X0
X0X0X0X0X
0X0X0X0X0
X0X0XDX0X
right
left
Выходные данные: Yes, если ранило, No если нет.
*/
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Task11 {
    private static int yW = -1;
    private static int xW = -1;
    private static int yD = -1;
    private static int xD = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;

        Queue<String> queue = new ArrayDeque<>();
        while(sc.hasNext()) {
            line = sc.next();
            if (line.isEmpty()) {
                continue;
            }
            queue.add(line);
        }
        //System.out.println(queue);
        fillXYDW(queue);
        //System.out.println("D: " + yD + " " + xD + "\nW: " + yW + " " + xW);

        newCell (queue.poll(), 'D');
        newCell (queue.poll(), 'W');

        //System.out.println("D: " + yD + " " + xD + "\nW: " + yW + " " + xW);

        System.out.println(isDolInjured() ? "Yes" : "No");

    }

    private static boolean isDolInjured() {
        return Math.abs(xD - xW) == Math.abs(yD - yW);
    }

    private static void fillXYDW(Queue<String> queue) {
        String line;
        int y = queue.size() - 2;
        for (int i = 0; i < y; i++) {
            line = queue.remove();
            if (yW != -1 && xW != -1 && yD != -1 && xD != -1) {
                continue;
            }
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case 'W': yW = i; xW = j; break;
                    case 'D': yD = i; xD = j;
                }

            }
        }
        if (yW == -1 || xW == -1 || yD == -1 || xD == -1) {
            System.out.println("no W or D");
        }

    }

    private static void newCell(String step, char name) {
        int x = 0;
        int y = 0;
        switch (step) {
            case "right": x++; break;
            case "left": x--; break;
            case "up": y++; break;
            case "down": y--; break;
            default:
                System.out.println("wrong x or y");
        }
        if (name == 'D') {xD += x; yD -= y;}
        else {xW += x; yW -= y;}
     }

    private static void printArrSq(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

}
