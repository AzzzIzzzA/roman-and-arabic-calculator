import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. Ввод строки и преобразование ее в массив
        System.out.println("Введите выражение для математического действия (+, -, *, /) с двумя римскими\nили арабскими числами (от 1 до 10) в формате строк: 7+8 и VI/II");
        Scanner scan = new Scanner(System.in);
        char[] arr = scan.nextLine().toCharArray();

        // 2. разбивка массива на числа и действия
        char deistvie = '0';
        int nomerinArray = -1;
        String pervoe = ""; String vtoroe = "";
        for (int i = 1; i < arr.length - 1; i++) { // цикл для поиска действия + - * /
            if (arr[i] == '+') {
                deistvie = arr[i]; nomerinArray = i; break;
            } else if (arr[i] == '-') {
                deistvie = arr[i]; nomerinArray = i; break;
            } else if (arr[i] == '*') {
                deistvie = arr[i]; nomerinArray = i; break;
            } else if (arr[i] == '/') {
                deistvie = arr[i]; nomerinArray = i; break;
            }
        }
        if (deistvie == '0') {
            System.out.println("Отсутствует арифметическая операция"); System.exit(0);
        }
        for (int i = 0; i < nomerinArray; i++) { // цикл для определения первого числа
            pervoe += arr[i];
        }
        for (int i = nomerinArray + 1; i < arr.length; i++) { // цикл для определения второго числа
            vtoroe += arr[i];
        }

        // 3. проверка условий по первым и вторым числам
        short RimOrArab = 0; // определяет римские или арабские оба числа
        String[] Arab = new String[] {"1","2","3","4","5","6","7","8","9","10"};
        String[] Rim = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        if (check(pervoe, Arab) && check(vtoroe, Arab)) {
            RimOrArab = 2;
        } else if (check(pervoe, Rim) && check(vtoroe, Rim)) {
            RimOrArab = 1;
        } else {
            System.out.println("Неправильный формат чисел, либо числа вне диапазона от 1 до 10, либо римские и арабские числа присутствуют одновременно"); System.exit(0);
        }

        // 4. Преобразовать римские числа и арабские в целые
        int PervoeInt = 0; int VtoroeInt = 0;
        if (RimOrArab == 1) {
            PervoeInt = convertRimArab(pervoe);
            VtoroeInt = convertRimArab(vtoroe);
        } else {
            PervoeInt = Integer.parseInt(pervoe);
            VtoroeInt = Integer.parseInt(vtoroe);
        }

        // 3. Совершить действия
        int res = 0;
        if (deistvie == '+') {
            res = PervoeInt + VtoroeInt;
        } else if (deistvie == '-') {
            res = PervoeInt - VtoroeInt;
        } else if (deistvie == '*') {
            res = PervoeInt * VtoroeInt;
        } else if (deistvie == '/') {
            res = PervoeInt / VtoroeInt;
        }

        // 4. Преобразовать арабские в римские (по необходимости) и ответ
        if (RimOrArab == 1 && res <= 0) {
            System.out.println("Решение - не положительное римское число"); System.exit(0);
        } else if (RimOrArab == 1) {
            System.out.println(convertArabRim(res));
        } else {
            System.out.println(res);
        }

    }
    // функция проверки, есть ли chislo в массиве massiv
    public static boolean check (String chislo, String[] massiv) {
        boolean check = false;
        for (int i = 0; i < massiv.length; i++) {
            if (chislo.equals(massiv[i])) {
                check = true; break;
            }
        }
        return check;
    }

    // функция преобразования римских чисел в арабские
    public static int convertRimArab (String Rimskaya) {
        String[] Rim = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        int convert = 0;
        for (int i = 0; i < Rim.length; i++) {
            if (Rimskaya.equals(Rim[i])) {
                convert = i + 1; break;
            }
        }
        return convert;
    }

    // функция преобразования арабских в римские
    public static String convertArabRim (int Arabskaya) {
        String Rimskaya = "", Desyatok = "", Edenic = "";
        String[] Rim1 = new String[] {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] Rim10 = new String[] {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        if (Arabskaya == 100) {
            Rimskaya = "C";
        } else {
            Desyatok = Rim10[Arabskaya / 10];
            Edenic = Rim1[Arabskaya % 10];
            Rimskaya = Desyatok + Edenic;
        }
        return Rimskaya;
    }
}