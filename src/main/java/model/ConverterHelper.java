package model;

import java.util.ArrayList;
import java.util.List;

public abstract class ConverterHelper {


    //(e.g. input is '11', the result is '0011')
    public static String formatBitChain(String numberAsString){
        int number = Integer.parseInt(numberAsString);
        return String.format ("%04d", number);
    }

    //(e.g. input is '3', the result is '11')
    public static String convertHexToBinary(String hex){
        return Integer.toBinaryString(Integer.parseInt(hex));
    }

    //(e.g. input is '3', the result is '0011)
    public static String convertHexToBinaryAndFormat(String hex){
        String binaryValue = convertHexToBinary(hex);
        return formatBitChain(binaryValue);
    }
    //(e.g. input is '1101', the result is '13')
    public static String convertBinaryToDec(String binary){
        String binaryValue = formatBitChain(binary);
        return Integer.parseInt(binaryValue,2)+"";
    }

    public static  List<String> splitInBitBlock(String text){
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < text.length()) {
            strings.add(text.substring(index, Math.min(index + 4,text.length())));
            index += 4;
        }
        return strings;
    }

    //(e.g. input is 'E', the result is '1110')
    public static String convertBinaryToHex(String numb){
        int num = (Integer.parseInt(numb, 16));
       return formatBitChain((Integer.toBinaryString(num)));
    }

}
