package control;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {
    String plainText = "0001001010001111";
    char[] plainChars = plainText.toCharArray();

    String cipherText = "1010111010110100";
    char[] cipherChars = cipherText.toCharArray();

    String key = "00010001001010001000110000000000";
    char[] keyChars = key.toCharArray();
    Controller controller = new Controller();

    @Test
    public void xOr(){
        Assert.assertEquals(false, controller.xOr('0','0'));
        Assert.assertEquals(true, controller.xOr('1','0'));
        Assert.assertEquals(true, controller.xOr('0','1'));
        Assert.assertEquals(false, controller.xOr('1','1'));
    }
    @Test
    public void xOrString(){
        String value = "11100010";
        String key = "01101000";

        Assert.assertEquals("10001010", controller.xOr(value, key));
    }

}