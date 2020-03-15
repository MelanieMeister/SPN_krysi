package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ConverterHelper {
    //(e.g. input is '11', the result is '0011')
    public static String formatBitChain(String numberAsString, String formatter) {
        int number = Integer.parseInt(numberAsString);
        return String.format(formatter, number);
    }

    //add zeros so that the length of the output is the same like the input
    // (e.g. input is '11', the result is '0000 0011') with length = 8
    public static String addZeros(String bits, int length) {
        int positionsToAdd = length - bits.toCharArray().length;

        String prefix = "";
        for (int i = 0; i < positionsToAdd; i++) {
            prefix = prefix + "0";
        }

        return prefix + bits;
    }

    //(e.g. input is '3', the result is '11')
    public static String convertHexToBinary(String hex) {
        return Integer.toBinaryString(Integer.parseInt(hex));
    }

    //(e.g. input is '3', the result is '0011)
    public static String convertHexToBinaryAndFormat(String hex, String format) {
        String binaryValue = convertHexToBinary(hex);
        return formatBitChain(binaryValue, format);
    }

    //(e.g. input is '1101', the result is '13')
    public static String convertBinaryToDec(String binary, String formatter) {
        String binaryValue = formatBitChain(binary, formatter);
        return Integer.parseInt(binaryValue, 2) + "";
    }

    //split input text in parts of a list. the length is the count of chars in one part of the list
    public static List<String> splitInBitBlock(String text, int length) {
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < text.length()) {
            strings.add(text.substring(index, Math.min(index + length, text.length())));
            index += length;
        }
        return strings;
    }

    //(e.g. input is 'E', the result is '1110')
    public static String convertBinaryToHex(String numb, String formatter) {
        int num = (Integer.parseInt(numb, 16));
        return formatBitChain((Integer.toBinaryString(num)), formatter);
    }

    //convert a binary to ascii
    public static String convertBinaryStringToAscii(String binary) {
        // Binary input as String
        String input = binary;
        // Some place to store the chars
        StringBuilder sb = new StringBuilder();
        // Create a Stream
        Arrays.stream(
                // Splits the input string into 8-char-sections (Since a char has 8 bits = 1 byte)
                input.split("(?<=\\G.{8})")
        ).forEach(s -> // Go through each 8-char-section...
                // ...and turn it into an int and then to a char
                sb.append((char) Integer.parseInt(s, 2))
        );


        return sb.toString();
    }

}
