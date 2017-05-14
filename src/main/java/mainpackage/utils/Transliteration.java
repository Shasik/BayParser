package mainpackage.utils;

import java.util.HashMap;
import java.util.Map;

public class Transliteration {
    private static final Map<Character, String> charMap = new HashMap<Character, String>();

    static {
        charMap.put('�', "A");
        charMap.put('�', "B");
        charMap.put('�', "V");
        charMap.put('�', "G");
        charMap.put('�', "D");
        charMap.put('�', "E");
        charMap.put('�', "Yo");
        charMap.put('�', "Zh");
        charMap.put('�', "Z");
        charMap.put('�', "I");
        charMap.put('�', "Y");
        charMap.put('�', "K");
        charMap.put('�', "L");
        charMap.put('�', "M");
        charMap.put('�', "N");
        charMap.put('�', "O");
        charMap.put('�', "P");
        charMap.put('�', "R");
        charMap.put('�', "S");
        charMap.put('�', "T");
        charMap.put('�', "U");
        charMap.put('�', "F");
        charMap.put('�', "H");
        charMap.put('�', "C");
        charMap.put('�', "Ch");
        charMap.put('�', "Sh");
        charMap.put('�', "Shc");
        charMap.put('�', "^");
        charMap.put('�', "I");
        charMap.put('�', "'");
        charMap.put('�', "E");
        charMap.put('�', "Y");
        charMap.put('�', "Ya");
        charMap.put('�', "a");
        charMap.put('�', "b");
        charMap.put('�', "v");
        charMap.put('�', "g");
        charMap.put('�', "d");
        charMap.put('�', "e");
        charMap.put('�', "yo");
        charMap.put('�', "zh");
        charMap.put('�', "z");
        charMap.put('�', "i");
        charMap.put('�', "y");
        charMap.put('�', "k");
        charMap.put('�', "l");
        charMap.put('�', "m");
        charMap.put('�', "n");
        charMap.put('�', "o");
        charMap.put('�', "p");
        charMap.put('�', "r");
        charMap.put('�', "s");
        charMap.put('�', "t");
        charMap.put('�', "u");
        charMap.put('�', "f");
        charMap.put('�', "h");
        charMap.put('�', "c");
        charMap.put('�', "ch");
        charMap.put('�', "sh");
        charMap.put('�', "shc");
        charMap.put('�', "^");
        charMap.put('�', "i");
        charMap.put('�', "'");
        charMap.put('�', "e");
        charMap.put('�', "y");
        charMap.put('�', "ya");
    }

    public static String transliterate(String string) {
        StringBuilder transliteratedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            String charFromMap = charMap.get(ch);
            if (charFromMap == null) {
                transliteratedString.append(ch);
            } else {
                transliteratedString.append(charFromMap);
            }
        }
        return transliteratedString.toString();
    }
}
