package control;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class Encrypter {
    //encrypt the input text
    public static String encrypt(String text){
        byte[] asciiByte = convertToAscii(text);
        String bitText = convertByteToBit(asciiByte);
        return fillBitString(bitText);
    }

    /**
     * convert the input text to ascii and
     * return tit as byteArray.
     *
     * @param text text which will be converted
     * @return ascii-code
     */
    public static byte[] convertToAscii(String text) {
        return text.getBytes(StandardCharsets.US_ASCII);
    }

    public static String convertByteToBit(byte[] bytes) {
        BigInteger bigInteger = new BigInteger(bytes);

        return bigInteger.toString(2);
    }

    //add '1' to a a string and fill the bitString
    // so that the length can be divided to 16
    public static String fillBitString(String msg) {
        String newString = msg + "1";

        while (newString.length() % 16 != 0.0) {
            newString = newString + "0";
        }

        return newString;
    }

    //generate the keys of round
    public static List<String> generateKeysOfRounds(String key, int r){
        List<String> keys = new ArrayList<>();
        for(int i=0; i<(r+1); i++){
            keys.add(getKeyOfRound(key, r, i));
        }

        return keys;
    }
    //generate one the keys of round
    public static String getKeyOfRound(String key, int r, int kPos) {
        char[] charArray = key.toCharArray();
        String newString = "";

        for(int j=(r*kPos); j<16+(kPos*r); j++) {
            newString = newString + charArray[j];
        }
        return newString;
    }
}
