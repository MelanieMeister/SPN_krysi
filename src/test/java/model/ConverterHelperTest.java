package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ConverterHelperTest {
    String formatter = "%04d";

    @Test
    public void formatBitChain() {
        Assert.assertEquals("0010", ConverterHelper.formatBitChain("10",formatter));
    }

    @Test
    public void convertNumberToBinary() {

        Assert.assertEquals("1000", ConverterHelper.convertHexToBinary("8"));
    }

    @Test
    public void convertToBinaryAndFormat() {

        Assert.assertEquals("0011", ConverterHelper.convertHexToBinaryAndFormat("3",formatter));
    }

    @Test
    public void splitInBitBlock() {
        String input = "1001 1111 1100 0010".replaceAll(" ","");
        List<String> strings = ConverterHelper.splitInBitBlock(input,4);

        Assert.assertEquals("1001", strings.get(0));
        Assert.assertEquals("1111", strings.get(1));
        Assert.assertEquals("1100", strings.get(2));
        Assert.assertEquals("0010", strings.get(3));
    }

    @Test
    public  void  addZerosTest(){
        Assert.assertEquals("0000010011010011", ConverterHelper.addZeros("10011010011", 16));
    }
    @Test
    public void convertBinaryToHey() {
        Assert.assertEquals("1010",ConverterHelper.convertBinaryToHex("A","%04d"));
        Assert.assertEquals("1011",ConverterHelper.convertBinaryToHex("B","%04d"));
        Assert.assertEquals("1100",ConverterHelper.convertBinaryToHex("C","%04d"));
        Assert.assertEquals("1101",ConverterHelper.convertBinaryToHex("D","%04d"));
        Assert.assertEquals("1110",ConverterHelper.convertBinaryToHex("E","%04d"));
        Assert.assertEquals("1111",ConverterHelper.convertBinaryToHex("F","%04d"));
        Assert.assertEquals("0100",ConverterHelper.convertBinaryToHex("4","%04d"));
    }

    @Test
    public void convertBinaryToDec() {
        Assert.assertEquals("13",ConverterHelper.convertBinaryToDec("1101", "%04d"));
    }
}