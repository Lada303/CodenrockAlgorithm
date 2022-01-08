package Lada303;

import java.util.Scanner;

public class Task5 {
    public static void main (String[] args) {
        Scanner sc= new Scanner(System.in);
        int flagStart = 0;
        int flagWords = 0;
        int counterWords = 0;
        String nameSurname = "";
        while(sc.hasNext()){
            String line= sc.next();
            flagWords ++;
            if ((line.charAt(0) >=  1040 && line.charAt(0) <=  1071 || line.charAt(0) ==  1025) &&
                    line.matches("[^a-zA-Z]+")){
                counterWords++;
                if (counterWords == 2) {
                    if (!line.matches("[^.,!?:;]+")) {
                        line = line.substring(0, line.length()-1);
                    }
                    if (flagStart == 0) {
                        System.out.print(nameSurname + " " + line);
                        flagStart = 1;
                    } else {
                        System.out.print(", " + nameSurname + " " + line);
                    }
                    counterWords = 0;
                    flagWords = 0;
                }
                nameSurname = line;
                if (counterWords == 1 && !line.matches("[^.,!?:;]+")) {
                    counterWords = 0;
                    flagWords = 0;
                }
            }
            if (flagWords != counterWords) {
                flagWords = 0;
                counterWords = 0;
            }
        }
    }
}
