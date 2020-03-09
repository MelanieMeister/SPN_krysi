package control;

public class Controller {

    public boolean xOr(char firstChar, char secondChar){
        return !(firstChar == secondChar);
    }

    public String xOr(String value, String keyRound){
        char[] textChar = value.toCharArray();
        char[] keyRoundChar = keyRound.toCharArray();

        String newValue = "";
        int difference = value.length() - keyRound.length();

        for(int i=0; i<difference; i++){
            newValue = newValue + textChar[i];
        }

        for(int i=0; i<keyRound.length();i++){
            try{
                if(xOr(textChar[i+difference], keyRoundChar[i])){
                    newValue = newValue + "1";
                }else{
                    newValue = newValue + "0";
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println();
            }

        }
        return newValue;
    }

}
