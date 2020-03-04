package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PresentationModelTest {

    PresentationModel model = new PresentationModel();

    String[] sBoxValues = {"5", "4", "D", "1", "3", "C", "B", "8", "A", "2", "6", "F", "9", "E", "0", "7"};
    int[] bitPermutationValues = {4,5,8,9,0,1,10,11,2,3,6,7};


    @Test
    public void getBitPermutation() {
        Assert.assertEquals("13", model.getBitPermutationByX("7").getBetaX());
        Assert.assertEquals("8", model.getBitPermutationByX("2").getBetaX());
        Assert.assertEquals("11", model.getBitPermutationByX("14").getBetaX());
    }

    @Test
    public void getSBoxEntryByX() {
        Assert.assertEquals("A", model.getSBoxEntryByX("1010").getX());
        Assert.assertEquals("3", model.getSBoxEntryByX("0011").getX());
    }

    @Test
    public void convertWithBitPermutation() {
        String input = "1001 1110 1010 1111 0000 1010 0001 1100";
        String output = "0110 1011 1010 1111 0000 1010 0100 0011";
        String newValue = model.convertWithBitPermutation(input.replaceAll(" ", ""));

        Assert.assertEquals(output.replaceAll(" ", ""), newValue);
    }


    @Test
    public void createCTRRound() {
//        model.setR(3);
//        model.setsBox(initSBox(sBoxValues));
//        model.setBitPermutation(initBitPermutation(bitPermutationValues));
//        List<String> keys = new ArrayList<>();
//        model.setKey("0001 1010 1111 1100 0000 01111".replaceAll(" ",""));
//
//        keys.add("0001 1010 1111".replaceAll(" ",""));
//        keys.add("1010 1111 1100".replaceAll(" ",""));
//        keys.add("1111 1100 0000".replaceAll(" ",""));
//        keys.add("1100 0000 0111".replaceAll(" ",""));
//        model.setKeys(keys);
//
//        model.createCTRRound("1111 0101 0110".replaceAll(" ",""));

        model.encrypt();
    }

    @Test
    public void encrypt() {
        model.encrypt();
    }

    private  List<SBoxEntry> initSBox(String... value) {
        List<SBoxEntry> sBox = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            sBox.add(new SBoxEntry(i + "", value[i]));
        }

        String[] letters = {"A", "B", "C", "D", "E", "F",};
        for (int i = 0; i < letters.length; i++) {
            sBox.add(new SBoxEntry((letters[i] + ""), value[i]));
        }
        return sBox;
    }

    /**
     * initialize a list with the BitPermutation-values.
     */
    private   List<BitPermutationEntry> initBitPermutation(int... numb) {
        List<BitPermutationEntry> bitPermutation = new ArrayList<>();
        for (int i = 0; i < numb.length; i++) {
            bitPermutation.add(new BitPermutationEntry(i + "", numb[i] + ""));
        }
        return bitPermutation;

    }

}