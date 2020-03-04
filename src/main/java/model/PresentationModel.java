package model;

import control.Controller;
import control.Encrypter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class PresentationModel {
   Controller controller = new Controller();
   private int r = 4, n=4, m= 4, s=32;
   String key = "00010001001010001000110000000000";
   private int round;

   private StringProperty cipherText = new SimpleStringProperty("0001001010001111");
   private StringProperty plainText = new SimpleStringProperty();

   List<String> keys = new ArrayList<>();

   List<SBoxEntry> sBox = new ArrayList<>();
   List<BitPermutationEntry> bitPermutation = new ArrayList<>();


   public PresentationModel(){
      initSBox();
      initBitPermutation();

      calculateRound();
      round = 7;
      this.keys = Encrypter.generateKeysOfRounds(key, r);
   }

   public void encrypt(){
      String preparedMessage = Encrypter.encrypt("Gut gemacht");

      String chiffreText = preparedMessage;
      for(int i=0; i<=round;i++){
         String value;
         if(round == 6){
            value = createCTRRound(chiffreText, true);
         }else{
            value = createCTRRound(chiffreText, false);
         }

         chiffreText = value;
      }

   }

   public String createCTRRound(String text, boolean lastRound){
      String stringPermutation ="";
      for(int i=0; i<=r;i++){
         //get the key (k0 - kr)
         String currentKey =  keys.get(i);

         //key xOR text  (k xOr text)
         String eXORk = controller.xOr(text, currentKey);

         //convert it with the S-Box
         String sBoxConverted = convertWithSBox(eXORk);

         if(!lastRound){
            //convert it according to the bitPermutation
            stringPermutation = convertWithBitPermutation(sBoxConverted);
         }
         }

      return stringPermutation;
   }

   String mmm = "";

   /**
    *
    * @param value value bit values with a length of 16 (e.g. '1001111111000010')
    * @return
    */
   public String convertWithBitPermutation(String value){
      String newValue="";
      //generate a list where each value has 4 digits (e.g. 0000)
      List<String> bitBlocks = ConverterHelper.splitInBitBlock(value);
      for(String bit: bitBlocks){
         //convert bit to decimal (e.g. '1001' to 9)
         String decValue = ConverterHelper.convertBinaryToDec(bit);
         //search the BitPermutation by x-value
         BitPermutationEntry e = getBitPermutationByX(decValue);
         //add the x-value (as bit) to the new value
         newValue = newValue + e.getBetaXAsBit();
      }

      return newValue;
   }

   /**
    * check if the number of the bitPermutationEntry is the same like
    * in the bitPermutation and if it is true, return the bitPermutationEntry
    * @param hex the number as hex of the x of the bitPermutation (e.g. '7')
    * @return bitPermutationEntry with the input as x-value
    */
   public BitPermutationEntry getBitPermutationByX(String hex){
      for(BitPermutationEntry entry: bitPermutation){
         if(entry.getX().equals(hex)){
            return entry;
         }
      }
      return null;
   }

   /**
    *
    * @param value bit values with a length of 16 (e.g. '1001111111000010')
    * @return
    */
   public String convertWithSBox(String value){
      String newValue="";
      //generate a list where each value has 4 digits (e.g. 0000)
      List<String> bitBlocks = ConverterHelper.splitInBitBlock(value);
      for(String bit: bitBlocks){
         //get the SBox whit the bit as x-value
         SBoxEntry e = getSBoxEntryByX(bit+"");
         //add the x-value (as bit) to the new value
         newValue = newValue + e.getSxAsBit();
      }

      return newValue;
   }

   /**
    *
    * @param bit x-value as bit (e.g. '1001)
    * @return
    */
   public SBoxEntry getSBoxEntryByX(String bit){
      for(SBoxEntry entry: sBox){
         if(entry.getxAsBit().equals(bit)){
            return entry;
         }
      }
      return null;
   }


   /**
    * initialize a list with the SBox-values.
    */
   private void initSBox(){
      sBox.add(new SBoxEntry("0","E"));
      sBox.add(new SBoxEntry("1","4"));
      sBox.add(new SBoxEntry("2","D"));
      sBox.add(new SBoxEntry("3","1"));
      sBox.add(new SBoxEntry("4","2"));
      sBox.add(new SBoxEntry("5","F"));
      sBox.add(new SBoxEntry("6","B"));
      sBox.add(new SBoxEntry("7","8"));
      sBox.add(new SBoxEntry("8","3"));
      sBox.add(new SBoxEntry("9","A"));
      sBox.add(new SBoxEntry("A","6"));
      sBox.add(new SBoxEntry("B","C"));
      sBox.add(new SBoxEntry("C","5"));
      sBox.add(new SBoxEntry("D","9"));
      sBox.add(new SBoxEntry("E","0"));
      sBox.add(new SBoxEntry("F","7"));
   }

   /**
    * initialize a list with the BitPermutation-values.
    */
   private  void initBitPermutation(){
      bitPermutation.add(new BitPermutationEntry("0","0"));
      bitPermutation.add(new BitPermutationEntry("1","4"));
      bitPermutation.add(new BitPermutationEntry("2","8"));
      bitPermutation.add(new BitPermutationEntry("3","12"));
      bitPermutation.add(new BitPermutationEntry("4","1"));
      bitPermutation.add(new BitPermutationEntry("5","5"));
      bitPermutation.add(new BitPermutationEntry("6","9"));
      bitPermutation.add(new BitPermutationEntry("7","13"));
      bitPermutation.add(new BitPermutationEntry("8","2"));
      bitPermutation.add(new BitPermutationEntry("9","6"));
      bitPermutation.add(new BitPermutationEntry("10","10"));
      bitPermutation.add(new BitPermutationEntry("11","14"));
      bitPermutation.add(new BitPermutationEntry("12","3"));
      bitPermutation.add(new BitPermutationEntry("13","7"));
      bitPermutation.add(new BitPermutationEntry("14","11"));
      bitPermutation.add(new BitPermutationEntry("15","15"));
   }


   private void calculateRound(){
      //todo: round
      round = (getCipherText().length() / (n*m));
   }

   public String getCipherText() {
      return cipherText.get();
   }

   public StringProperty cipherTextProperty() {
      return cipherText;
   }

   public void setCipherText(String cipherText) {
      this.cipherText.set(cipherText);
   }

   public String getPlainText() {
      return plainText.get();
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

   public List<String> getKeys() {
      return keys;
   }

   public void setKeys(List<String> keys) {
      this.keys = keys;
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

   public StringProperty plainTextProperty() {
      return plainText;
   }

   public void setPlainText(String plainText) {
      this.plainText.set(plainText);
   }
}
