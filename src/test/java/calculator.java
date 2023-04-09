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
    @Test
    public void testSubEqualLength(){
        String a = "010111";
        String b = "010001";
        String result = calc.sub(a,b);
        Assert.assertEquals(result,"000110");
    }
    @Test
    public void testSubDifferentLength(){
        String a = "010111";
        String b = "0111";
        String result = calc.sub(a,b);
        Assert.assertEquals(result,"010000");
    }
    @Test
    public void testSubWithVoidElement(){
        String a = "01";
        String b ="";
        String result = calc.sum(a,b);
        Assert.assertEquals(result,a);
    }
    @Test
    public void testSubWithVoidElements(){
        String a = "";
        String b = "";
        String result = calc.sum(a,b);
        Assert.assertEquals(result,a);
    }
    @Test
    public void testNegativeSub(){
        String a = "010110";
        String b = "011001";
        String result = calc.sub(a,b);
        Assert.assertEquals("111101",result);
    }
    @Test
    public void testMult(){
        String a = "10";
        String b = "11";
        String result = calc.mult(a,b);
        Assert.assertEquals("110",result);
    }
    @Test
    public void testMultByZero(){
        String a = "10";
        String b = "0";
        String result = calc.mult(a,b);
        Assert.assertEquals("0",result);
    }
    @Test
    public void testDivision(){
        String a = "110";
        String b = "10";
        String result = calc.div(a,b);
        Assert.assertEquals("11",result);
    }
    @Test
    public void testDivisionByZero(){
        String a = "1";
        String b = "0";
        String result = calc.div(a,b);
        Assert.assertEquals("invalid value",result);
    }
    @Test
    public void testToHex(){
        String binary = "10000";
        String result = calc.toHex(binary);
        Assert.assertEquals("10",result);
    }
    @Test
    public void testFromHex(){
        String hex = "f";
        String result = calc.fromHex(hex);
        Assert.assertEquals("1111",result);
    }
}