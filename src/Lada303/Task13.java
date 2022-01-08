package Lada303;

import java.util.*;

public class Task13 {

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
        run(queue);
    }

    private static void run(Queue<String> queue) {
        String word;
        char punctuation;
        while (queue.iterator().hasNext()) {
            word = queue.poll();
            punctuation = word.charAt(word.length() - 1);
            if ( punctuation == '.' || punctuation == ',') {
                word = word.substring(0, word.length() - 1);
            }
            if (word.length() > 1) {
                word = rearrangementOfLetters(word);
            }
            //System.out.println(word);
            System.out.print(cipherCaesar(word) + (punctuation == '.' || punctuation == ',' ?
                    punctuation + " " : " "));

        }
    }

    private static String cipherCaesar(String word) {
        String newWord ="";
        int step = (int) Character.toLowerCase(word.charAt(0)) - 'a' + 1;
        //System.out.println(step);
        for (int i = 0; i < word.length(); i++) {
            newWord +=  Character.toLowerCase(word.charAt(i)) + step > 'z' ?
                    (char) (word.charAt(i) + step - 26) : (char) (word.charAt(i) + step);
        }
        return newWord;
    }

    private static String rearrangementOfLetters(String word) {
        char[] arr = new char[word.length()];
        List<Integer> listFreeIndex = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            listFreeIndex.add(i);
        }
        int indexArr = 1;
        int delta = 1;
        for (int i = 0; i < word.length(); i++) {
            if (i == word.length() - 1) {
                arr[listFreeIndex.get(0)] = word.charAt(i);
                break;
            }
            arr[listFreeIndex.get(indexArr)] = word.charAt(i);
            listFreeIndex.remove(indexArr);
            indexArr += delta;
            if (indexArr >= listFreeIndex.size()) {
                indexArr = listFreeIndex.size() - 2;
                delta = -2;
            }
            if (indexArr < 0) {
                indexArr = 1;
                delta = 1;
            }
        }
        return String.copyValueOf(arr);
    }

}
