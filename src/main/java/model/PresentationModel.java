package model;

import control.Controller;
import control.Encrypter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class PresentationModel {
    public static String formatter = "%04d";
    public static String bitFormatter = "%04d";
    Controller controller = new Controller();
    String key = "0011 1010 1001 0100 1101 0110 0011 1111".replaceAll(" ", "");
    List<String> keys = new ArrayList<>();
    List<SBoxEntry> sBox = new ArrayList<>();
    List<BitPermutationEntry> bitPermutation = new ArrayList<>();
    private int r = 4, n = 4, m = 4, s = 32;
    private int round;
    private StringProperty cipherText = new SimpleStringProperty("00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000");
    private int blockLength = 4;
    private int bitPermutationBlockLength = 4;

    //init SBox and Permutations and generate key of rounds
    public void init() {
        initSBox();
        initBitPermutation();

        round = calculateCTRRounds(getCipherText());
        this.keys = Encrypter.generateKeysOfRounds(getKey(), r);
    }

    //decrypt the value
    public String decrypt() {
        List<String> cipherList = ConverterHelper
                .splitInBitBlock(getCipherText(), (n * m));

        List<String> spnList = new ArrayList<>();
        String currentValue = "";

        //CTR Rounds
        for (int i = 0; i < cipherList.size() - 1; i++) {
            String cipher = cipherList.get(i);
            if (i != 0) {
                cipher = Controller.increment(currentValue);
            }
            String modResult =
                    Controller.modulo(cipher);

            //convert it with the S-Box
            String spnConverted = createSPN(modResult);

            spnList.add(spnConverted);
            currentValue = modResult;
        }

        String plainText = "";
        for (int i = 0; i < cipherList.size() - 1; i++) {
            plainText = plainText +
                    controller.xOr(spnList.get(i), cipherList.get(i + 1));
        }

        String paddingRemovedString = encode(plainText);


        return ConverterHelper.convertBinaryStringToAscii(paddingRemovedString);
    }


    //remove the padding
    public String encode(String v) {
        char[] value = v.toCharArray();

        int stopper = 0;
        for (int i = value.length - 1; i > 0; i--) {
            char charI = value[i];
            if (charI == '1') {
                stopper = i;
                break;
            }
        }
        String result = "";
        for (int i = 0; i < stopper; i++) {
            result = result + value[i];
        }

        return result;
    }

    //create one SPN
    public String createSPN(String text) {
        String stringPermutation = text;
        for (int i = 0; i < getR(); i++) {
            //get the key (k0 - kr)
            String currentKey = keys.get(i);

            //key xOR text  (k xOr text)
            String eXORk = controller.xOr(stringPermutation, currentKey);

            //convert it with the S-Box
            String sBoxConverted = convertWithSBox(eXORk);

            // check if not last round
            if (i != (getR() - 1)) {
                //convert it according to the bitPermutation
                stringPermutation = convertWithBitPermutation(sBoxConverted);
                // System.out.println(stringPermutation);
            } else {
                //key xOR text  (k xOr text)
                stringPermutation = controller.xOr(sBoxConverted, keys.get(i + 1));
            }
        }

        return stringPermutation;
    }

    /**
     * @param value value bit values with a length of 16 (e.g. '1001111111000010')
     * @return
     */
    public String convertWithBitPermutation(String value) {
        int length = Integer.parseInt(String.valueOf(value.length()));
        char[] z = new char[length];

        char[] charArray = value.toCharArray();
        // for each char of value string
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            // get bit permutation value
            BitPermutationEntry e = getBitPermutationByX(Integer.toString(i));
            // set new value at position
            z[Integer.parseInt(e.betaX)] = c;
        }
        // return char array as string
        return String.valueOf(z);
    }

    /**
     * check if the number of the bitPermutationEntry is the same like
     * in the bitPermutation and if it is true, return the bitPermutationEntry
     *
     * @param hex the number as hex of the x of the bitPermutation (e.g. '7')
     * @return bitPermutationEntry with the input as x-value
     */
    public BitPermutationEntry getBitPermutationByX(String hex) {
        for (BitPermutationEntry entry : bitPermutation) {
            if (entry.getX().equals(hex)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * @param value bit values with a length of 16 (e.g. '1001111111000010')
     * @return
     */
    public String convertWithSBox(String value) {
        String newValue = "";
        //generate a list where each value has 4 digits (e.g. 0000)
        List<String> bitBlocks = ConverterHelper.splitInBitBlock(value, getBlockLength());
        for (String bit : bitBlocks) {
            //get the SBox whit the bit as x-value
            SBoxEntry e = getSBoxEntryByX(bit + "");
            //add the x-value (as bit) to the new value
            newValue = newValue + e.getSxAsBit();

        }

        return newValue;
    }

    /**
     * @param bit x-value as bit (e.g. '1001)
     * @return
     */
    public SBoxEntry getSBoxEntryByX(String bit) {
        for (SBoxEntry entry : sBox) {
            if (entry.getxAsBit().equals(bit)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * initialize a list with the SBox-values.
     */
    private void initSBox() {
        sBox.add(new SBoxEntry("0", "E", getFormatter()));
        sBox.add(new SBoxEntry("1", "4", getFormatter()));
        sBox.add(new SBoxEntry("2", "D", getFormatter()));
        sBox.add(new SBoxEntry("3", "1", getFormatter()));
        sBox.add(new SBoxEntry("4", "2", getFormatter()));
        sBox.add(new SBoxEntry("5", "F", getFormatter()));
        sBox.add(new SBoxEntry("6", "B", getFormatter()));
        sBox.add(new SBoxEntry("7", "8", getFormatter()));
        sBox.add(new SBoxEntry("8", "3", getFormatter()));
        sBox.add(new SBoxEntry("9", "A", getFormatter()));
        sBox.add(new SBoxEntry("A", "6", getFormatter()));
        sBox.add(new SBoxEntry("B", "C", getFormatter()));
        sBox.add(new SBoxEntry("C", "5", getFormatter()));
        sBox.add(new SBoxEntry("D", "9", getFormatter()));
        sBox.add(new SBoxEntry("E", "0", getFormatter()));
        sBox.add(new SBoxEntry("F", "7", getFormatter()));
    }

    /**
     * initialize a list with the BitPermutation-values.
     */
    private void initBitPermutation() {
        bitPermutation.add(new BitPermutationEntry("0", "0", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("1", "4", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("2", "8", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("3", "12", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("4", "1", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("5", "5", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("6", "9", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("7", "13", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("8", "2", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("9", "6", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("10", "10", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("11", "14", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("12", "3", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("13", "7", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("14", "11", getFormatter()));
        bitPermutation.add(new BitPermutationEntry("15", "15", getFormatter()));
    }


    //calculate the number of CTR rounds
    private int calculateCTRRounds(String s) {
        return (s.length() / (n * m));
    }

    public String getCipherText() {
        return cipherText.get();
    }

    public void setCipherText(String cipherText) {
        this.cipherText.set(cipherText);
    }

    public StringProperty cipherTextProperty() {
        return cipherText;
    }


    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(int blockLength) {
        this.blockLength = blockLength;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public String getBitFormatter() {
        return bitFormatter;
    }

    public void setBitFormatter(String bitFormatter) {
        this.bitFormatter = bitFormatter;
    }

    public List<SBoxEntry> getsBox() {
        return sBox;
    }

    public void setsBox(List<SBoxEntry> sBox) {
        this.sBox = sBox;
    }

    public List<BitPermutationEntry> getBitPermutation() {
        return bitPermutation;
    }

    public void setBitPermutation(List<BitPermutationEntry> bitPermutation) {
        this.bitPermutation = bitPermutation;
    }


    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public int getBitPermutationBlockLength() {
        return bitPermutationBlockLength;
    }

    public void setBitPermutationBlockLength(int bitPermutationBlockLength) {
        this.bitPermutationBlockLength = bitPermutationBlockLength;
    }
}
