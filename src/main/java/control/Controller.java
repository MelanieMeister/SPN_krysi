package control;

import model.ConverterHelper;

public class Controller {

    //add 1 to the input value
    public static String increment(String value) {
        //add one to hex value
        int result = Integer.parseInt(value, 2) + 1;
        //convert it that it has the correct widht
        return ConverterHelper.addZeros(Integer.toBinaryString(result), 16);
    }

    //calculate input mod 2
    public static String modulo(String input) {
        int dividend = Integer.parseInt(input, 2);
        double result = (dividend % Math.pow(2, input.length()));


        return ConverterHelper.addZeros(Integer.toBinaryString((int) result), 16);
    }

    //firstChar xOR secondChar
    public boolean xOr(char firstChar, char secondChar) {
        return !(firstChar == secondChar);
    }

    //xOr the whole string
    public String xOr(String value, String keyRound) {
        char[] textChar = value.toCharArray();
        char[] keyRoundChar = keyRound.toCharArray();

        String newValue = "";
        int difference = value.length() - keyRound.length();

        for (int i = 0; i < difference; i++) {
            newValue = newValue + textChar[i];
        }

        for (int i = 0; i < keyRound.length(); i++) {
            if (xOr(textChar[i + difference], keyRoundChar[i])) {
                newValue = newValue + "1";
            } else {
                newValue = newValue + "0";
            }
        }
        return newValue;
    }
}
