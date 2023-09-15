import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 2 + 3):");
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неправильный формат выражения");
        }

        int num1;
        int num2;
        try {
            num1 = RomanConverter.romanToArabic(tokens[0]);
            num2 = RomanConverter.romanToArabic(tokens[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Поддерживаются только арабские или римские числа");
        }

        char operator = tokens[1].charAt(0);
        int result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль запрещено");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }

        if (num1 >= 1 && num1 <= 10 && num2 >= 1 && num2 <= 10) {
            return RomanConverter.arabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }
}

class RomanConverter {
    private static final String[] ROMAN_NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private static final int[] ARABIC_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static int romanToArabic(String roman) {
        for (int i = 0; i < ROMAN_NUMERALS.length; i++) {
            if (ROMAN_NUMERALS[i].equals(roman)) {
                return ARABIC_VALUES[i];
            }
        }
        throw new NumberFormatException("Недопустимое римское число: " + roman);
    }

    public static String arabicToRoman(int arabic) {
        if (arabic < 1 || arabic > 10) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }
        return ROMAN_NUMERALS[arabic - 1];
    }
}