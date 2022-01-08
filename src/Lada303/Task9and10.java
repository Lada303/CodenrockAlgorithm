package Lada303;
/*
Преобразование Барроуза - Уиллера
 */
import java.util.*;

public class Task9and10 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        //codingBWT(line);
        //codingBWT("abacaba");
        //String[] arrInput = sc.nextLine().split(" ");
        //decodingBWT("bcabaaa 2");
        decodingBWT2(line);

    }

    private static void codingBWT (String line) {
        Map<String, Integer> shiftMatrix = new TreeMap();
        for (int i = 0; i < line.length(); i++) {
            // System.out.println(line);
            shiftMatrix.put(line, i);
            line = line.substring(1) + line.charAt(0);
        }
        Iterator<Integer> iter = shiftMatrix.values().iterator();
        //  shiftMatrix.forEach((k, v) -> System.out.println(k + " " + v));
        List<Integer> arrIndex = List.copyOf(shiftMatrix.values());
        //System.out.println(arrIndex);
        for (int i = 0; i < line.length(); i++) {
            int index = arrIndex.get(i);
            System.out.print(line.charAt(index - 1 == -1 ? line.length() - 1 : index - 1));
        }
        System.out.println(" " + arrIndex.indexOf(0));
    }

    private static void decodingBWT(String line) {
        String[] arrInput = line.split(" ");
        Set<String> set = new TreeSet();
        for (int i = 0; i < arrInput[0].length(); i++) {
            set.add(Character.toString(arrInput[0].charAt(i)) + i);
        }
        //System.out.println(set);
        int[] inverseVector = new int[arrInput[0].length()];
        int index = 0;
        //int inverseKey = Integer.parseInt(arrInput[1]);
        int inverseKey = line.indexOf('|');
        Iterator<String> iterSet = set.iterator();
        while (iterSet.hasNext()) {
            String str =  iterSet.next();
            inverseVector[index++] = Integer.parseInt(str.substring(1));
        }
        //System.out.println(Arrays.toString(inverseVector));
        for (int i = 0; i < arrInput[0].length(); i++) {
            System.out.print(arrInput[0].charAt(inverseVector[inverseKey]));
            inverseKey = inverseVector[inverseKey];
        }
    }

    private static void decodingBWT2(String line) {
        Set<String> set = new TreeSet();
        for (int i = 0; i < line.length(); i++) {
            set.add(Character.toString(line.charAt(i)) + i);
        }
        int[] inverseVector = new int[line.length()];
        int index = 0;
        int inverseKey = line.indexOf('|');
        Iterator<String> iterSet = set.iterator();
        while (iterSet.hasNext()) {
            String str =  iterSet.next();
            inverseVector[index++] = Integer.parseInt(str.substring(1));
        }
        for (int i = 0; i < line.length(); i++) {
            System.out.print(line.charAt(inverseVector[inverseKey]));
            inverseKey = inverseVector[inverseKey];
        }
    }
}
