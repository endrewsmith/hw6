package org.example;

import org.example.hm6.MyMorseTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

MyMorseTranslator translator = new MyMorseTranslator();

    @Test
    void encodeTest() {
        // Граничные условия:
        // Если вводим null на выходе пустая строка
        assertEquals("", translator.encode(null));
        // Если ввели пустую строку, на выходе пустая строка
        assertEquals("", translator.encode(""));
        // Обычные тесты
        assertEquals(".... . .-.. .-.. --- / .-- --- .-. .-.. -..", translator.encode("hello world"));
        assertEquals(".... --- -- . / .-- --- .-. -.- / -....", translator.encode("Home Work 6"));
        assertEquals("... --- ...", translator.encode("sos"));
        // Кодирование игнорирует незнакомые символы
        assertEquals("... --- ...", translator.encode("s&os"));
        // В условие слэш идет как разделитель между словами, если слов нет, то нечего разделять
        // (https://morsecodetranslator.com/ - работает по такой логике, но знает русские буквы)
        assertEquals("", translator.encode("   "));
        assertEquals("", translator.encode("привет мир"));
    }

    @Test
    void decodeTest() {
        // Граничные условия:
        assertEquals("", translator.decode(null));
        assertEquals("", translator.decode(""));
        // Обычные тесты
        // Декодирование происходит в верхнем регистре
        assertEquals("HELLO WORLD", translator.decode(".... . .-.. .-.. --- / .-- --- .-. .-.. -.."));
        assertEquals("HOME WORK 6", translator.decode(".... --- -- . / .-- --- .-. -.- / -...."));
        assertEquals("SOS", translator.decode("... --- ..."));
        // Декодирование игнорирует пробелы
        assertEquals("", translator.decode("    "));
        // Декодирование игнорирует слэши
        assertEquals("", translator.decode("/ /  /   /"));
        // Если таких кодов не в словаре, то возвращает пустую строку
        assertEquals("", translator.decode(".......- ............----"));
    }

}
