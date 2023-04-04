import org.junit.Assert;
import org.junit.Test;
import tp1.calculator.Calculator;

public class calculator{
    public Calculator calc = new Calculator();

    @Test
    public void testSumEqualLength(){
        String a = "101";
        String b = "001";
        String result = calc.sum(a,b);
        Assert.assertEquals(result,"110");
    }
    @Test
    public void testSumDifferentLength(){
        String a = "1001";
        String b = "101";
        String result = calc.sum(a,b);
        Assert.assertEquals(result,"1110");
    }
    @Test
    public void testSumFinalCarry(){
        String a = "1";
        String b = "1";
        String result = calc.sum(a,b);
        Assert.assertEquals(result,"10");
    }
    @Test
    public void testSumWithVoidElement(){
        String a = "1";
        String b = "";
        String result = calc.sum(a,b);
        Assert.assertEquals(result,"1");
    }
    @Test
    public void testSumWithVoidElements(){
        String a = "";
        String b = "";
        String result = calc.sum(a,b);
        Assert.assertEquals(result,"");
    }
}