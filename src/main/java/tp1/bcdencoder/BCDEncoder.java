package tp1.bcdencoder;

public class BCDEncoder implements tp1.interfaces.BCDEncoder {
    @Override
    public String encode(int a) {
        String solution = "";
        String stringNumber = Integer.toString(a);
        solution = solution + convertSingleDigitToBCD(stringNumber.charAt(0)); // The first one doesnt need a space.
        for(int i = 1; i<stringNumber.length(); i++){
            solution = solution + " " + convertSingleDigitToBCD(stringNumber.charAt(i));
        }
        return solution;
    }

    @Override
    public int decode(String a) {
        String solution = "";
        String aux = "";
        int counter = 0;
        while (true) {
            if(counter == a.length()){
                solution = solution + Integer.parseInt(aux, 2);
                break;
            }
            char charAtPosicion = a.charAt(counter);
            if (charAtPosicion == ' ') {
                solution = solution + Integer.parseInt(aux, 2);
                aux = "";
                counter++;
            } else {
                aux = aux + a.charAt(counter);
                counter++;
            }
        }

        return Integer.parseInt(solution);
    }


    public String completeWithZeros(String binary){
        //It completes the byte whit ceros
        int len = binary.length();
        if(len == 4){
            //best case, already complete
            return binary;
        }
        else{
            StringBuilder extra_zeros = new StringBuilder();
            int diference = 4 - len; //if its not complete, diference > 0
            while(diference>0){
                extra_zeros.append("0");
                diference--;
            }
            return extra_zeros + binary;
        }
    }

    private String convertSingleDigitToBCD(char digit){
        int num = Integer.parseInt(String.valueOf(digit));
        String binaryNum = Integer.toBinaryString(num);
        return completeWithZeros(binaryNum);
    }
}
