package Lada303;
/*
Вам будет дано представление дерева возможных вариантов разговора Мейв.
Ваша задача найти количество всевозможных диалогов, которые состоят из 6 узлов и более.
Корень дерева обозначается 1.
Диалог считается законченным, если при направлении движения от корня дерева, через родительские узлы
к дочерним, вы пришли к элементу, у которого нет дочерних узлов.
Например: на картинке изображено 3 диалога с длиной пути от 6-ти узлов и более.
1-3-5-8-10-12
1-3-5-13-14-15
1-3-5-13-14-16-17
Входные данные: диалоговое дерево, представленное в виде связи родительских узлов с дочерними,
где сначала указан родительский узел, через двоеточие его дочерние узлы, если узла нет среди родительских,
то значит у этого узла нет дочерних элементов
Пример выходных данных:
1:2,3
2:6
3:4,5
4:7
5:8,13
6:9
8:10,11
10:12
13:14
14:15,16
16:17
Пример выходных данных: 3
*/
import java.util.*;

public class Task12 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        Queue<String> queue = new ArrayDeque<>();
        while (sc.hasNext()) {
            line = sc.next();
            if (line.isEmpty()) {
                continue;
            }
            queue.add(line);
        }
        //System.out.println(queue);
        run(queue);
    }

    private static void run(Queue<String> queue) {
        Map<Integer, Integer> nodeAndHisParent = new TreeMap<>();
        Set<Integer> setParent = new TreeSet<>();
        int maxIdNode = 0;
        while (queue.iterator().hasNext()) {
           String[] arr = queue.poll().split("[ :,]");
            for (int i = 1; i < arr.length; i++) {
                nodeAndHisParent.put(Integer.parseInt(arr[i]), Integer.parseInt(arr[0]));
                setParent.add(Integer.parseInt(arr[0]));
            }
            if(queue.isEmpty()) {
                maxIdNode = Integer.parseInt(arr[arr.length - 1]);
            }
        }
        //System.out.println(setParent);
        //System.out.println(nodeAndHisParent);

        int countDialogs = 0;
        for (int i = maxIdNode; i > 0 ; i--) {
            int countNodes = 1;
            if (!setParent.contains(i)) {
                int parentId = i;
                while (parentId != 1) {
                    countNodes++;
                    parentId = nodeAndHisParent.get(parentId);
                }
            }
           // System.out.println(countNodes);
            if (countNodes >= 6) {
                countDialogs++;
            }
        }
        System.out.println(countDialogs);
    }

}
