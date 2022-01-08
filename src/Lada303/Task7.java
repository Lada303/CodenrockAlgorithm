package Lada303;
/*
Литорея - метод шифрования русского алфавита, где Е и Ё объединяются. Остальные 32 символа разделяют
на 2 группы по 16 штук, внутри группы производится зеркальная замена букв, что ближе к концу по
местоположению в группе на те, что ближе к началу и наоборот.
Например: 1-я буква заменяется на 16-ю, а - п, 3-я буква заменяется на 14-ю в-н/
Учитывайте регистр. Реализуйте данный метод шифрования.
АБВгдежз ийКЛМноп рсТуФхцч ШЩЪыьЭюЯ
ПОНмлкйи зжЕДГвба яюЭьЫъщш ЧЦХфуТсР
Входные данные: строка до 100 символов, на русском языке
Пример входных данных:  Мир это опасное место
Выходные данные: зашифрованная строка
Пример выходных данных: Гзя тэб бапювбк гкюэб
 */
import java.util.Scanner;

public class Task7 {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String line= sc.nextLine();
        char letter;
        boolean isUpperCase;
        for (int i = 0; i < line.length(); i++) {
            letter = line.charAt(i);
            if (letter == ' ') {
                System.out.print(" ");
                continue;
            }
            if (Character.isUpperCase(letter)) {
                isUpperCase = true;
                letter = Character.toLowerCase(letter);
            } else {
                isUpperCase = false;
            }
            letter = letter - 1072 < 16 ? (char) (1072 + 16 - (letter - 1072) - 1) :
                    (char) (1072 + (32 - (letter - 1072 -16) - 1));
            System.out.print(isUpperCase ? Character.toUpperCase(letter) : letter);
        }
    }
}
