package control;

public class Controller {

    public boolean xOr(char firstChar, char secondChar){
        return !(firstChar == secondChar);
    }

    public String xOr(String value, String keyRound){
        char[] textChar = value.toCharArray();
        char[] keyRoundChar = keyRound.toCharArray();

        String newValue = "";

        for(int i=0; i<keyRound.length();i++){
            if(xOr(textChar[i], keyRoundChar[i])){
                newValue = newValue + "1";
            }else{
                newValue = newValue + "0";
            }
        }
        return newValue;
    }

}
