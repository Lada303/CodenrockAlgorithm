package Lada303;
/*
Зашифруйте сообщение с помощью перестановки букв внутри слов и шифра Цезаря
Дана строка, её надо разбить по словам. Каждое слово зашифровать подстановкой, для каждой буквы в слове
определить её порядок в алфавите. И потом сдвинуть на это число с помощью шифра Цезаря.
Чтобы совершить перестановку, условно берем пустое поле, состоящее из n клеток, где n - количество букв в
слове, добавляем в поле буквы шифрованного слова по алгоритму:
Для вставки каждой следующей буквы отсчитываем две пустые клетки в поле, относительно незаполненных
клеток, если при отсчете наткнусь на тупиковую клетку поля, то меняем направление отсчета, и отсчитываем
дальше по незаполненным клеткам.
Первая буква слова, идет на вторую позицию, вторую букву вставляем на две позиции дальше, относительно
незаполненных позиции. Последняя буква попадает, в оставшуюся пустой клетку.
То что получилось в поле и есть шифрованное слово.
Пример заполнения для слова из 3-x букв, цифра показывает номер буквы в слове по порядку:
123 → 213
Пример заполнения для слова из 4-х букв:
1234 → 3142
Пример перестановки для слова из 8 букв:
12345678  →  61825374
После чего каждое слово зашифруйте алгоритмом шифрования Цезаря. С шагом равному номеру в алфавите
первой буквы в слове получившемся в результате перестановки.
Для кодирования шифром цезаря, каждая буква кодируемого слова заменяется на букву получившеюся в
результате смещения на n заданных позиции вправо от первоначальной буквы, относительно алфавита.
Если смещение зашифрованной буквы больше, чем количество символов в алфавите, смещение продолжается
с нулевой позиции
Слово "something" после перестановки станет "nshoimteg" и мы букву "n" сдвигаем на его позицию - 14 и
"n" станет "b" получается bgvcwahsu
Регистр мы сохраняем во время всех манипуляций
Входные данные: кодируемая строка
Пример входных данных:
When you delete something, you are making a choice to destroy it. To never see it again.
Пример выходных данных:
jBsm dnj jijjyq bgvcwahsu, dnj jsw ntphur b jhnmht di jvqwlkg nc. dI jfnww jxj nc boouw.
 */
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
