package model;

public class SBoxEntry {
    private String x;
    private String sx;
    private String xAsBit;
    private String sxAsBit;

    public SBoxEntry(String x, String sx, String formatter) {

        this.x = x;
        this.sx = sx;
        this.xAsBit = ConverterHelper.convertBinaryToHex(x, formatter);

        String number = ConverterHelper.convertBinaryToHex(sx, formatter);
        this.sxAsBit = ConverterHelper.formatBitChain(number, formatter);
    }

    public String getxAsBit() {
        return xAsBit;
    }

    public void setxAsBit(String xAsBit) {
        this.xAsBit = xAsBit;
    }

    public String getSxAsBit() {
        return sxAsBit;
    }

    public void setSxAsBit(String sxAsBit) {
        this.sxAsBit = sxAsBit;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }
}
