package control;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EncrypterTest {
    String plainText = "Gut gemacht";
    String key = "00111010100101001101011000111111";

    @Test
    public void convertToAscii() {
        byte[] bytes = Encrypter.convertToAscii("Java");
        Assert.assertEquals(74, bytes[0]);
        Assert.assertEquals(97, bytes[1]);
        Assert.assertEquals(118, bytes[2]);
        Assert.assertEquals(97, bytes[3]);
    }

    @Test
    public void convertByteToBit(){
        byte[] bytes = Encrypter.convertToAscii("Java");
        String s = Encrypter.convertByteToBit(bytes);

        Assert.assertEquals("1001010011000010111011001100001", s);
    }

    @Test
    public void fillBitString(){
        String bitString = "11100110000000100";

        String output = Encrypter.fillBitString(bitString);
        Assert.assertEquals(bitString+"1"+"00000000000000", output);
    }

    @Test
    public void getKeyOfRound(){
        int r= 4;
        Assert.assertEquals("0011101010010100",   Encrypter.getKeyOfRound(key,4,0));
        Assert.assertEquals("1010100101001101",   Encrypter.getKeyOfRound(key,4,1));
        Assert.assertEquals("1001010011010110",   Encrypter.getKeyOfRound(key,4,2));
        Assert.assertEquals("0100110101100011",   Encrypter.getKeyOfRound(key,4,3));
        Assert.assertEquals("1101011000111111",   Encrypter.getKeyOfRound(key,4,4));
    }

    @Test
    public void generateKeysOfRounds(){
        int r= 4;
        List<String> output = Encrypter.generateKeysOfRounds(key, r);
        Assert.assertEquals("0011101010010100",   output.get(0));
        Assert.assertEquals("1010100101001101",   output.get(1));
        Assert.assertEquals("1001010011010110",   output.get(2));
        Assert.assertEquals("0100110101100011",   output.get(3));
        Assert.assertEquals("1101011000111111",   output.get(4));
    }
}