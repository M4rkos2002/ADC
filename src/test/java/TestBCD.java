import org.junit.Assert;
import org.junit.Test;
import tp1.bcdencoder.BCDEncoder;

public class TestBCD {
    public BCDEncoder bcdEncoder = new BCDEncoder();


    @Test
    public void testEncodeMethod(){
        int a = 584;
        String solution = "0101 1000 0100";
        Assert.assertEquals(solution, bcdEncoder.encode(a));
    }

    @Test
    public void testDecode(){
        String a = "0101 1000 0100";
        int solution = 584;
        Assert.assertEquals(solution, bcdEncoder.decode(a));
    }
}
