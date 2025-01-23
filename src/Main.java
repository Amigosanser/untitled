import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int a = in.nextInt();
        var aa = String.valueOf(a);
        var len = String.valueOf(a).length();
        int sum = 0;

        for (int i = 0; i < len; i++) {
            sum += aa.charAt(i);
        }
        System.out.println("Сумма цифр в числе: " + sum);
    }
}