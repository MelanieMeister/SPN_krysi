package model;

public class BitPermutationEntry {
    String x;
    String betaX;
    String xAsBit;
    String betaXAsBit;
    public BitPermutationEntry(String x, String betaX) {
        this.x = x;
        this.betaX = betaX;
        this.xAsBit =ConverterHelper.convertHexToBinaryAndFormat(x);
        this.betaXAsBit=ConverterHelper.convertHexToBinaryAndFormat(betaX);
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getxAsBit() {
        return xAsBit;
    }

    public void setxAsBit(String xAsBit) {
        this.xAsBit = xAsBit;
    }

    public String getBetaXAsBit() {
        return betaXAsBit;
    }

    public void setBetaXAsBit(String betaXAsBit) {
        this.betaXAsBit = betaXAsBit;
    }

    public String getBetaX() {
        return betaX;
    }

    public void setBetaX(String betaX) {
        this.betaX = betaX;
    }
}
