import java.io.*;
import java.util.Scanner;

public class CaesarCipher {
    // Метод для шифрования текста
    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            // Шифруем только буквы
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            encrypted.append(ch);
        }
        return encrypted.toString();
    }

    // Метод для расшифровки текста
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26)); // Используем шифрование с обратным сдвигом
    }

    // Метод для обработки файла
    public static void processFile(String inputFilePath, String outputFilePath, int shift, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = encrypt ? encrypt(line, shift) : decrypt(line, shift);
                writer.write(processedLine);
                writer.newLine();
            }
            System.out.println("Файл успешно обработан: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка обработки файла: " + e.getMessage());
        }
    }

    // Метод для проверки входных данных
    public static int validateShift(String input) {
        try {
            int shift = Integer.parseInt(input);
            if (shift < 0) {
                throw new NumberFormatException();
            }
            return shift % 26; // Сдвиг должен быть в пределах 0-25
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пожалуйста, введите положительное целое число.");
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в Шифр Цезаря!");

        while (true) {
            System.out.print("Введите сдвиг (положительное целое число) или 'exit' для выхода: ");
            String shiftInput = scanner.nextLine();
            if (shiftInput.equalsIgnoreCase("exit")) {
                break;
            }

            int shift = validateShift(shiftInput);
            if (shift == -1) {
                continue; // Неверный ввод, переходим к следующей итерации
            }

            System.out.print("Введите путь к входному файлу: ");
            String inputFilePath = scanner.nextLine();

            System.out.print("Введите путь к выходному файлу: ");
            String outputFilePath = scanner.nextLine();

            System.out.print("Выберите режим (1 для шифрования, 2 для расшифровки): ");
            int mode = scanner.nextInt();
            scanner.nextLine(); // Чистим буфер после nextInt()

            if (mode == 1) {
                processFile(inputFilePath, outputFilePath, shift, true);
            } else if (mode == 2) {
                processFile(inputFilePath, outputFilePath, shift, false);
            } else {
                System.out.println("Некорректный режим. Пожалуйста, выберите 1 или 2.");
            }
        }

        scanner.close();
        System.out.println("Конец выполнения программы");
    }
}