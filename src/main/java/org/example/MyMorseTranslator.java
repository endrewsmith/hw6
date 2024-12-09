package org.example;

import java.util.HashMap;
import java.util.Map;

public class MyMorseTranslator implements MorseTranslator {

    // Словарь для кодирования в морзе
    private static final Map<Character, String> charCode = new HashMap<>();
    // Словарь для декодирования из морзе
    private static final Map<String, Character> codeChar = new HashMap<>();

    // В конструкторе сразу заполняем словари
    public MyMorseTranslator() {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z', '1', '2', '3', '4',
                '5', '6', '7', '8', '9', '0'};
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.",
                "--.", "....", "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".--.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-",
                "-.--", "--..", ".----", "..---", "...--", "....-",
                ".....", "-....", "--...", "---..", "----.", "-----"};

        for (int i = 0; i < chars.length; i++) {
            charCode.put(chars[i], codes[i]);
            codeChar.put(codes[i], chars[i]);
        }
    }


    @Override
    public String decode(String morseCode) {

        String decodeStr = "";
        if (morseCode == null || morseCode.trim().isEmpty()) {
            return decodeStr;
        }

        // Слова разделены / разбиваем строку на массив слов
        String[] words = morseCode.split(" / ");
        for (String word : words) {
            // Символы разделены пробелом
            String[] chars = word.split(" ");
            for (String charTmp : chars) {
                // Достаем как объект, если нет в словаре, вернется null
                Character ch = codeChar.get(charTmp);
                if (ch != null) {
                    decodeStr += ch;
                }

            }
            // Вписали слово, поставили пробел
            decodeStr += " ";
        }

        return decodeStr.trim();
    }

    @Override
    public String encode(String source) {

        String encodeStr = "";
        if (source == null || source.trim().isEmpty()) {
            return encodeStr;
        }

        // После проверки на null переведем в верхний регистр, тк словарь только для него
        source = source.toUpperCase();
        char[] sourceChars = source.toCharArray();
        for (char ch : sourceChars) {
            if (!encodeStr.trim().isEmpty() && ch == ' ') {
                // В конце буквы будет записан пробел, поэтому нужен еще один после слеша
                encodeStr += "/ ";
            } else {
                String code = charCode.get(ch);
                if (code != null) {
                    encodeStr += code + " ";
                }
            }
        }
        return encodeStr.trim();
    }
}
