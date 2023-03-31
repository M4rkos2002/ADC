package tp1.asciiencoder;


import java.util.Stack;

public class AsciiEncoder implements tp1.interfaces.AsciiEncoder {
    @Override
    public String encode(String binary) {
        StringBuilder ascii_msg = new StringBuilder();
        int first = 0;
        int last = 8;
        while(last<binary.length()+1){ //Last number in range is never includen in the substring
            String substring = binary.substring(first, last);
            int number = BinaryToDecimal(substring);
            ascii_msg.append(String.valueOf((char) number));//changes number to its character in ascii
            first = last;
            last = last + 8;
        }
        return ascii_msg.toString();
    }

    @Override
    public String decode(String ascii) {
        StringBuilder binary = new StringBuilder();
        int n = ascii.length();
        for(int i = 0; i<n; i++){
            char character = ascii.charAt(i);//peeks a character
            String bin_value = this.DecimalToBinary((int) character);
            binary.append(this.completeAsciiByte(bin_value));//gets int value of it and appened as binary value
        }
        return binary.toString();
    }

    //tested
    public Integer BinaryToDecimal(String binary){
        int sum = 0;
        int j = binary.length()-1;
        for(int i = 0; i<=j ; i++){
            char number = binary.charAt(binary.length()-1-i);
            if(number == '0'){continue;}
            else{sum = (int) (sum + Math.pow(2, i));}
        }
        return sum;
    }

    //tested
    public String DecimalToBinary(int number){
        Stack<Integer> stack = new Stack<>();
        int numerator = number;
        int quotient = numerator/2;
        int remainder = numerator%2;
        while(numerator>=2){
            numerator = quotient;
            stack.push(remainder);
            quotient = numerator/2;
            remainder = numerator%2;
        }
        stack.push(remainder);
        StringBuilder binary = new StringBuilder();
        while(!stack.isEmpty()){//while stack has numbers
            binary.append(String.valueOf((int) stack.pop()));
        }
        return binary.toString();
    }

    //tested
    public String completeAsciiByte(String binary){
        //It completes the byte whit ceros
        int len = binary.length();
        if(len == 8){
            //best case, already complete
            return binary;
        }
        else{
            StringBuilder extra_zeros = new StringBuilder();
            int diference = 8 - len; //if its not complete, diference > 0
            while(diference>0){
                extra_zeros.append("0");
                diference--;
            }
            return extra_zeros + binary;
        }
    }
}
