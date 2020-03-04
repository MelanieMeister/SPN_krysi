package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ConverterHelperTest {

    @Test
    public void formatBitChain() {
        Assert.assertEquals("0010", ConverterHelper.formatBitChain("10"));
    }

    @Test
    public void convertNumberToBinary() {

        Assert.assertEquals("1000", ConverterHelper.convertHexToBinary("8"));
    }

    @Test
    public void convertToBinaryAndFormat() {

        Assert.assertEquals("0011", ConverterHelper.convertHexToBinaryAndFormat("3"));
    }

    @Test
    public void splitInBitBlock() {
        String input = "1001 1111 1100 0010".replaceAll(" ","");
        List<String> strings = ConverterHelper.splitInBitBlock(input);

        Assert.assertEquals("1001", strings.get(0));
        Assert.assertEquals("1111", strings.get(1));
        Assert.assertEquals("1100", strings.get(2));
        Assert.assertEquals("0010", strings.get(3));
    }

    @Test
    public void convertBinaryToHey() {
        Assert.assertEquals("1010",ConverterHelper.convertBinaryToHex("A"));
        Assert.assertEquals("1011",ConverterHelper.convertBinaryToHex("B"));
        Assert.assertEquals("1100",ConverterHelper.convertBinaryToHex("C"));
        Assert.assertEquals("1101",ConverterHelper.convertBinaryToHex("D"));
        Assert.assertEquals("1110",ConverterHelper.convertBinaryToHex("E"));
        Assert.assertEquals("1111",ConverterHelper.convertBinaryToHex("F"));
        Assert.assertEquals("0100",ConverterHelper.convertBinaryToHex("4"));
    }

    @Test
    public void convertBinaryToDec() {
        Assert.assertEquals("13",ConverterHelper.convertBinaryToDec("1101"));
    }
}