package Lada303;
/*
Ричард и Эрлих идут на переговоры с k инвесторами. У них два метода ведения переговоров, либо вести
себя по-хамски, тем самым не дать прогнуть себя, либо вести себя прилично. У них только n дней, для того,
чтобы представить свой проект, всем инвесторам.
Они понимают, что если вести себя по-хамски, то инвесторы не будут посылать их на повторные переговоры,
но успех при этом будет сомнителен. Если же они будут себя вести прилично, то инвесторы будут наглеть и
устраивать больше встреч.
Они изначально составляют план, сколько времени они готовы тратить на каждого инвестора. Если они готовы
рискнуть, то они будут вести себя максимально неприлично, на этого инвестора планируют немного дней. Если
же инвестор стоит того, и они готовы тратить время, чтобы представлять проект снова и снова, то они будут
терпеливы, и планируют больше дней. Так как у них ограничено время, то они могут просто не пойти на встречу.
Ричард для каждого инвестора составил функцию зависимости вероятности успеха ведения переговоров от
количества дней, потраченных на инвестора. Функции неубывающие.  Смотрите таблицу. Таблица составлена для
5 дней, и 4 инвесторов.
        	0 	1 	2 	3 	4 	5 день
1 инвестор	0	1	2	3	4	6
2 инвестор	0	2	3	3	4	6
3 инвестор	0	3	4	5	5	5
4 инвестор	0	6	7	7	7	9
Ричард считает успешной стратегию, при которой сумма вероятностей успехов максимальна.
Для данных из таблицы выше успешная стратегия следующая:
 	Количество дней	Вероятность успеха
    1 инвестор  0	0
    2 инвестор  1	2
    3 инвестор  2	4
    4 инвестор  2	7
При этом максимальная суммарная вероятность равна 13.
Ваша задача найти максимальную суммарную вероятность, для входных данных.
Задача подразумевает решение методом динамического программирования.
Входные данные: k, n, функции успеха
Пример входных данных:
4
5
1,2,3,4,6
2,3,3,4,6
3,4,5,5,5
6,7,7,7,9
k ≤ 20, n ≤ 20
Выходные данные: максимальная суммарная вероятность
 */
import java.util.Arrays;
import java.util.Scanner;

public class Task14 {
    private static int countInvestors;
    private static int countDays;
    private static int[][] arrProbability;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        countInvestors = sc.nextInt();
        countDays = sc.nextInt();
        arrProbability = new int[countInvestors][countDays];
        int index = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            for (int j = 0; j < countDays; j++) {
                arrProbability[index][j] = Integer.parseInt(Character.toString(line.charAt(2 + j * 2)));
            }
            index++;
        }
        //System.out.println(Arrays.deepToString(arrProbability));
        run();
    }

    private static void run() {
        System.out.println(maxProbability(countDays - 1, arrProbability));
    }

    private static int maxProbability(int startDays, int[][] arrStart) {
        int[][] arrClone = arrClone(arrStart);
        //System.out.println(Arrays.deepToString(arrClone));
        int maxProbability = maxProbabilityInDay(startDays,arrClone)[0];
        if (startDays == 0) {
            return maxProbability;
        }
        for (int i = 1; i <= startDays; i++) {
            arrClone = arrClone(arrStart);
            int[] maxP1 = maxProbabilityInDay(startDays - i,arrClone);
            Arrays.fill(arrClone[maxP1[1]], 0);
            //System.out.println(Arrays.deepToString(arrClone));
            int maxP2 = maxProbability((startDays - (startDays - i) - 1),arrClone);
            //System.out.println("d" + (startDays - i) + "+d" + (startDays - (startDays - i) - 1) + " " + (maxP1[0] + maxP2));
            maxProbability = Math.max(maxProbability, maxP1[0] + maxP2);
        }
        //System.out.println(maxProbability);
        return maxProbability;
    }

    private static int[] maxProbabilityInDay(int days, int[][] arr) {
        int[] max = new int[2];
        Arrays.fill(max, -1);
        for (int i = 0; i < countInvestors; i++) {
            if (max[0] < arr[i][days]) {
                max[0] = Math.max(max[0],arr[i][days]);
                max[1] = i;
            }
        }
        return max;
    }

    private static int[][] arrClone(int[][] arr) {
        int[][] newArr = new int[arr.length][arr[0].length];
        for (int i = 0; i < countInvestors; i++) {
            for (int j = 0; j < countDays; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }

}
