import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tp1.asciiencoder.AsciiEncoder;

public class ascii {

    public AsciiEncoder encoder = new AsciiEncoder();

    @Test
    public void testBinaryToDecimal(){
        String binary = "00110";
        Integer number = encoder.BinaryToDecimal(binary);
        System.out.println(number);
        Assert.assertEquals(Integer.valueOf(6), number);
    }

    @Test
    public void testEncode(){
        String binary = "01001000011011110110110001100001001000000100110101100101001000000100110001101100011000010110110101101111001000000100101001101111011100100110011101100101";
        String encoded = encoder.encode(binary);
        Assert.assertEquals(encoded, "Hola Me Llamo Jorge");
    }

    @Test
    public void testDecimalToBinary(){
        int number = 127;
        String binary = encoder.DecimalToBinary(number);
        Assert.assertEquals(binary, "1111111");
    }

    @Test
    public void testCompleteBinaryWithZeros(){
        int number = 40;
        String binary = encoder.DecimalToBinary(40);
        String completedBinary = encoder.completeAsciiByte(binary);
        Assert.assertEquals(completedBinary.length(), 8);
        Assert.assertEquals(completedBinary, "00101000");
        number = 127;
        binary = encoder.DecimalToBinary(number);
        completedBinary = encoder.completeAsciiByte(binary);
        Assert.assertEquals(completedBinary, "01111111");
    }

    @Test
    public void testDecode(){
        String msg = "Hola Me Llamo Jorge";
        String binary = "01001000011011110110110001100001001000000100110101100101001000000100110001101100011000010110110101101111001000000100101001101111011100100110011101100101";
        String encoded = encoder.encode(binary);
        String decoded = encoder.decode(encoded);
        Assert.assertEquals(encoded, msg);
        Assert.assertEquals(binary, decoded);
    }
}
