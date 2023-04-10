package tp1.calculator;

import java.util.Stack;

public class Calculator implements tp1.interfaces.Calculator {
    @Override
    public String sum(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        int maxLength = 0;
        if (a.length() > b.length()) {
            maxLength = a.length();
            b = addZeros(b,maxLength);
        }
        else if(a.length() < b.length()){
            maxLength = b.length();
            a = addZeros(a,maxLength);
        }
        else {maxLength = a.length();}
        for(int i = maxLength - 1;i >= 0; i--){
            int binaryA = Character.getNumericValue(a.charAt(i));
            int binaryB = Character.getNumericValue(b.charAt(i));;
            int sum =binaryA + binaryB + carry;
            carry = sum / 2;
            int valueToAdd = sum % 2;
            builder.insert(0,valueToAdd);
        }
        if (carry == 1){
            builder.insert(0,carry);
        }
        return builder.toString();
    }

    private String addZeros(String a, int maxlength){
        StringBuilder builder = new StringBuilder(a);
        while (builder.length() < maxlength){
            builder.insert(0,0);
        }
        return builder.toString();
    }

    @Override
    public String sub(String a, String b) {

        int carry = 0;
        int maxLength = 0;
        StringBuilder builder = new StringBuilder();
        if (a.length() > b.length()) {
            maxLength = a.length();
            b = addZeros(b,maxLength);
        }
        else if(a.length() < b.length()){
            maxLength = b.length();
            a = addZeros(a,maxLength);
        }
        else
            maxLength = a.length();

        try{
            for(int i = 0; i < maxLength;i++){
                if (Character.getNumericValue(a.charAt(i)) > Character.getNumericValue(b.charAt(i)))
                    break;
                else if (Character.getNumericValue(a.charAt(i)) < Character.getNumericValue(b.charAt(i)))
                    throw  new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            return "negative result";
        }

        String complementB = binaryComplement(b);
        for(int i = maxLength - 1;i >= 0; i--){
            int binaryA = Character.getNumericValue(a.charAt(i));
            int binaryB = Character.getNumericValue(complementB.charAt(i));;
            int sum =binaryA + binaryB + carry;
            carry = sum / 2;
            int valueToAdd = sum % 2;
            builder.insert(0,valueToAdd);
        }
        return builder.toString();


    }

    private String binaryComplement(String binary){
        StringBuilder builder = new StringBuilder();
        int carry = 1;
        for (int i =binary.length()-1;i>=0; i--){
            int binaryNumber = Character.getNumericValue(binary.charAt(i));
            // 1 - binaryNumber is the compliment value value at that position of the binary number
            int sum = carry+(1-binaryNumber);
            if (sum == 0)
                builder.insert(0,sum);
            else if (sum == 1){
                builder.insert(0,sum);
                carry = 0;
            }
            else{
                builder.insert(0,1);
                carry = 1;
            }
        }
        if (carry == 1)
            builder.insert(0,carry);
        return builder.toString();
    }

    @Override
    public String mult(String a, String b) {
        String partialMult = "0";
        for (int i = toDecimal(b);i >= 1;i--){
            partialMult = sum(partialMult,a);
        }
        return partialMult;
    }

    @Override
    public String div(String a, String b) {
        try{
            if (toDecimal(b) == 0){
                throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException e) {
            return "invalid value";
        }
        Integer counter = 0;
        String partialValue = "0";
        while (toDecimal(a) > toDecimal(partialValue)){
            partialValue = sum(partialValue,b);
            counter++;
        }
        return fromDecimal(counter).toString();
    }

    @Override
    public String toHex(String binary) {
        int decimal = this.toDecimal(binary);
        Stack<Integer> stack = new Stack<>();
        int numerator = decimal;
        int quotient = numerator/16;
        int remainder = numerator%16;
        while(numerator>=16){
            numerator = quotient;
            stack.push(remainder);
            quotient = numerator/16;
            remainder = numerator%16;
        }
        stack.push(remainder);
        StringBuilder hex = new StringBuilder();
        while(!stack.isEmpty()){//while stack has numbers
            int n = stack.pop();
            switch(n){
                case 10: hex.append("A");
                case 11: hex.append("B");
                case 12: hex.append("C");
                case 13: hex.append("D");
                case 14: hex.append("E");
                case 15: hex.append("F");
                default: hex.append(n);
            }
        }
        return hex.toString();
    }

    @Override
    public String fromHex(String hex) {
        String[] hexChars = {"0000", "0001", "0010", "0011",
                "0100", "0101", "0110", "0111",
                "1000", "1001", "1010", "1011",
                "1100", "1101", "1110", "1111"};

        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            char ch = hex.charAt(i);
            if (Character.isDigit(ch)) {
                int digit = Character.digit(ch, 16);
                binary.append(hexChars[digit]);
            } else if (Character.isLetter(ch)) {
                int asciiValue = (int) ch;
                int hexValue = Character.isUpperCase(ch) ? asciiValue - 'A' + 10 : asciiValue - 'a' + 10;
                binary.append(hexChars[hexValue]);
            } else {
                throw new IllegalArgumentException("Invalid hexadecimal input: " + hex);
            }
        }
        return binary.toString();
    }

    public Integer toDecimal(String binary){
        int sum = 0;
        int j = binary.length()-1;
        for(int i = 0; i<=j ; i++){
            char number = binary.charAt(binary.length()-1-i);
            if(number == '0'){continue;}
            else{sum = (int) (sum + Math.pow(2, i));}
        }
        return sum;
    }

    public String fromDecimal(Integer decimal){
        if (decimal == 0) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        while (decimal > 0) {
            builder.insert(0, decimal % 2);
            decimal = decimal / 2;
        }
        return builder.toString();
    }
}
