public class Calculator {
    boolean flag = false;
    String[] exc = new String[]{"\\Q**\\E", "\\Q++\\E", "--", "\\Q..\\E", "::", "==", "CC"};
    char[] exce = new char[]{'*','.','+','=','-',':','C'};


    public String handler(String inp){
        System.out.println("456");
        for(int i = 0;i<exc.length;i++){
            if(exc[i].length()>2) {
                inp = inp.replaceAll(exc[i], exc[i].substring(exc[i].length() - 4, exc[i].length() - 3));
            }
            else{
                inp = inp.replaceAll(exc[i], exc[i].substring(exc[i].length() - 2, exc[i].length() - 1));
            }


            }
        System.out.println("123");
        if(inp.charAt(inp.length()-1) == 'b'){
            if(inp.substring(0, inp.length() - 2) != null) {
                inp = inp.substring(0, inp.length() - 2);
            }
            else{
                inp = null;
            }
        }
        if(inp.charAt(inp.length()-1) == 'C'){
            inp = null;
        }

        return inp;
    }

    public String calculator(String equation){
        int a;
        for(int i = 0; i<exce.length;i++){
            if(equation.charAt(equation.length()-2) == exce[i]) {
                for (int d = 0; d < exce.length; d++) {
                    if(equation.charAt(equation.length()-1) == exce[d]) {
                        equation= equation.substring(0, equation.length()-1);
                        break;
                    }
                }
                break;
            }
        }
        if(equation.charAt(equation.length()-1) == '='){
            equation = equation.substring(0, equation.length()-1);
        }
        for(int l = 0; l < equation.length();l++) {
            switch(equation.charAt(l)) {
                case '+':
                 a = (Integer.parseInt(equation.substring(0, l))  +  Integer.parseInt(equation.substring(l+1)));
                equation = String.valueOf(a);
                case '-':
                     a = (Integer.parseInt(equation.substring(0, l))  -  Integer.parseInt(equation.substring(l+1)));
                    equation = String.valueOf(a);
                case ':':
                     a = (Integer.parseInt(equation.substring(0, l))  /  Integer.parseInt(equation.substring(l+1)));
                    equation = String.valueOf(a);
                case '*':
                     a = (Integer.parseInt(equation.substring(0, l))  *  Integer.parseInt(equation.substring(l+1)));
                    equation = String.valueOf(a);

            }

        }
        return equation;
    }

    public String ToSting(String input) {
        String out = null;
        Bot botStrings = new Bot();
        if (input.equals(botStrings.numberNull)) {
            out = "0";
        } else if (input.equals(botStrings.numberOne)) {
            out = "1";
        } else if (input.equals(botStrings.numberTwo)) {
            out = "2";
        } else if (input.equals(botStrings.numberThree)) {
            out = "3";
        } else if (input.equals(botStrings.numberFour)) {
            out = "4";
        } else if (input.equals(botStrings.numberFive)) {
            out = "5";
        } else if (input.equals(botStrings.numberSix)) {
            out = "6";
        } else if (input.equals(botStrings.numberSeven)) {
            out = "7";
        } else if (input.equals(botStrings.numberEight)) {
            out = "8";
        } else if (input.equals(botStrings.numberNine)) {
            out = "9";
        } else if (input.equals(botStrings.ok)) {
            out = "=";
        } else if (input.equals(botStrings.minus)) {
            out = "-";
        } else if (input.equals(botStrings.plus)) {
            out = "+";
        } else if (input.equals(botStrings.division)) {
            out = ":";
        } else if (input.equals(botStrings.multiplication)) {
            out = "*";
        } else if (input.equals(botStrings.backspace)) {
            out = "b";
        }
        return out;
    }
}