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
    public void createSPNTest() {
        PresentationModel model = new PresentationModel();
        model.setKey("0001 0001 0010 1000 1000 1100 0000 0000".replaceAll(" ",""));
        model.init();
        String t = model.createSPN("0001 0010 1000 1111".replaceAll(" ",""));


        Assert.assertEquals("1010 1110 1011 0100".replaceAll(" ",""), t);
    }
    @Test
    public void createCTRTest() {
        PresentationModel model = new PresentationModel();
        model.init();
        String t = model.createSPN("0001 0010 1000 1111".replaceAll(" ",""));

        Assert.assertEquals("1111110110100110", t);
    }

    @Test
    public void dte(){
        PresentationModel model = new PresentationModel();
        model.init();
        model.decrypt();
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