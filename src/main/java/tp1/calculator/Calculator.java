package tp1.calculator;

import java.util.Stack;

public class Calculator implements tp1.interfaces.Calculator {
    @Override
    public String sum(String a, String b) {
        StringBuilder builder = new StringBuilder();
        String strA = a;
        String strB = b;
        int carry = 0;
        int maxLength = 0;
        if (a.length() > b.length()) {
            maxLength = a.length();
            strB = addZeros(strB,maxLength);
        }
        else if(a.length() < b.length()){
            maxLength = b.length();
            strA = addZeros(strA,maxLength);
        }
        else {maxLength = a.length();}
        for(int i = maxLength - 1;i >= 0; i--){
            int binaryA = Character.getNumericValue(strA.charAt(i));
            int binaryB = Character.getNumericValue(strB.charAt(i));;
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
        return null;
    }

    @Override
    public String mult(String a, String b) {
        return null;
    }

    @Override
    public String div(String a, String b) {
        return null;
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
        return null;
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
}
