package model;

import control.Encrypter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PresentationModelTest {


    String[] sBoxValues = {"5", "4", "D", "1", "3", "C", "B", "8", "A", "2", "6", "F", "9", "E", "0", "7"};
    int[] bitPermutationValues = {4, 5, 8, 9, 0, 1, 10, 11, 2, 3, 6, 7};


    @Test
    public void getBitPermutation() {
        PresentationModel pm = new PresentationModel();

        int[] bitPermutationValue2 = {1, 0, 3, 2};
        pm.setBitPermutation(initBitPermutation("%02d", bitPermutationValue2));
        Assert.assertEquals("1", pm.getBitPermutationByX("0").getBetaX());
        Assert.assertEquals("0", pm.getBitPermutationByX("1").getBetaX());
        Assert.assertEquals("2", pm.getBitPermutationByX("3").getBetaX());
        Assert.assertEquals("3", pm.getBitPermutationByX("2").getBetaX());

        PresentationModel model = new PresentationModel();
        model.init();
        Assert.assertEquals("13", model.getBitPermutationByX("7").getBetaX());
        Assert.assertEquals("8", model.getBitPermutationByX("2").getBetaX());
        Assert.assertEquals("11", model.getBitPermutationByX("14").getBetaX());
    }

    @Test
    public void getSBoxEntryByX() {
        PresentationModel model = new PresentationModel();
        model.init();
        Assert.assertEquals("A", model.getSBoxEntryByX("1010").getX());
        Assert.assertEquals("3", model.getSBoxEntryByX("0011").getX());
    }

    @Test
    public void convertWithBitPermutation() {
        PresentationModel model = new PresentationModel();
        model.init();
        String input = "1001 1110 1010 1111 0000 1010 0001 1100";
        String output = "0110 1011 1010 1111 0000 1010 0100 0011";
        String newValue = model.convertWithBitPermutation(input.replaceAll(" ", ""));

        Assert.assertEquals(output.replaceAll(" ", ""), newValue);
    }


    @Test
    public void createCTRRound() {
        PresentationModel model = new PresentationModel();

        model.setR(2);
        String[] sBoxValues2 = {"0", "2", "1", "3"};
        model.setsBox(initSBox("%02d", sBoxValues2));
        model.setPlainText("0010");
        model.setBlockLength(2);
        model.setBitPermutationBlockLength(1);
        model.setFormatter("%02d");
        model.setBitFormatter("%01d");
        model.setBitPermutationBlockLength(1);

        int[] bitPermutationValue2 = {1, 0, 3, 2};
        model.setBitPermutation(initBitPermutation("%01d", bitPermutationValue2));
        List<String> keys = new ArrayList<>();
        model.setKey("0010".replaceAll(" ", ""));

        keys.add("1000".replaceAll(" ", ""));
        keys.add("1011".replaceAll(" ", ""));
        keys.add("1000".replaceAll(" ", ""));
        model.setKeys(keys);


        String value = model.createCTRRound(model.getPlainText(), false);

    }

    @Test
    public void createCTRRound2() {
        PresentationModel model = new PresentationModel();

        String x = "0001 0010 1000 1111".replaceAll(" ", "");
        String y = "1010 1110 1011 0100".replaceAll(" ", "");
        String key = "0001 0001 0010 1000 1000 1100 0000 0000".replaceAll(" ", "");


        String bitString = Encrypter.fillBitString(x);
        model.setKey(key);


        model.init();
        List<String> keys = model.getKeys();
        Assert.assertEquals(keys.get(0), "0001 0001 0010 1000 ".replaceAll(" ", ""));
        Assert.assertEquals(keys.get(1), "0001 0010 1000 1000 ".replaceAll(" ", ""));
        Assert.assertEquals(keys.get(2), "0010 1000 1000 1100  ".replaceAll(" ", ""));
        Assert.assertEquals(keys.get(3), "1000 1000 1100 0000    ".replaceAll(" ", ""));
        Assert.assertEquals(keys.get(4), "1000 1100 0000 0000 ".replaceAll(" ", ""));


        String v = model.createCTRRound(bitString, true);
        Assert.assertEquals(y, v);

    }

    @Test
    public void createCTRRoundByExample() {
        PresentationModel model = new PresentationModel();
        model.init();
        String t = model.createCTRRound("0001001010001111", false);



        //String v = model.createCTRRound(preparedMessage, true);
        //System.out.println(v);

    }


    private List<SBoxEntry> initSBox(String formatter, String... value) {
        List<SBoxEntry> sBox = new ArrayList<>();

        for (int i = 0; i < value.length; i++) {
            sBox.add(new SBoxEntry(i + "", value[i], formatter));
        }

//        String[] letters = {"A", "B", "C", "D", "E", "F",};
//        for (int i = 0; i < letters.length; i++) {
//            sBox.add(new SBoxEntry((letters[i] + ""), value[i]));
//        }
        return sBox;
    }

    /**
     * initialize a list with the BitPermutation-values.
     */
    private List<BitPermutationEntry> initBitPermutation(String formatter, int... numb) {
        List<BitPermutationEntry> bitPermutation = new ArrayList<>();
        for (int i = 0; i < numb.length; i++) {
            bitPermutation.add(new BitPermutationEntry(i + "", numb[i] + "", formatter));
        }
        return bitPermutation;

    }

}