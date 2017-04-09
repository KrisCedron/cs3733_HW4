package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;


    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException Thrown if the value is an Elbonian number that does not conform
     * to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     * in the Elbonian number system.
     */
    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // TODO check to see if the number is valid, then set it equal to the string
        removeSpaces(number);

        char[] charArray =number.toCharArray();

        //if a string contains both a number and a letter
        String numRegex = ".*[0-9].*";
        String alphaRegex = ".*[A-Z].*";
        if (number.matches(numRegex) && number.matches(alphaRegex)){
            throw new MalformedNumberException("String contains both numbers and letters");
        }

        //if a string contains a letter out side of elbonian numerals
        String alphaRegex2 = ".*[A-B].*";
        String alphaRegex3 = ".*[E-H].*";
        String alphaRegex4 = ".*[J-K].*";
        String alphaRegex5 = ".*[N-U].*";
        String alphaRegex6 = ".*[W-W].*";
        String alphaRegex7 = ".*[Y-Z].*";
        String alphaRegex8 = ".*[a-c].*";
        String alphaRegex9 = ".*[e-k].*";
        String alphaRegex10 = ".*[m-u].*";
        String alphaRegex11 = ".*[w-z].*";

        if (number.matches(alphaRegex2) || number.matches(alphaRegex3) || number.matches(alphaRegex4) || number.matches(alphaRegex5) || number.matches(alphaRegex6) || number.matches(alphaRegex7) || number.matches(alphaRegex8) || number.matches(alphaRegex9) || number.matches(alphaRegex10) || number.matches(alphaRegex11)){
            throw new MalformedNumberException("String contains characters outside the elbonian alphabet");
        }

        //if a smaller elbonian number is placed before a larger one
        for (int x = number.length() -1; x >= 0; x--) {
            char c = number.charAt(x);
            //if an elbonian lowercase letter does not come before an upper case of the same letter
            if (c == 'v' && number.charAt(x+1) != 'V'){
                throw new MalformedNumberException("Elbonian numerals not in proper order");
            }
            else if (c == 'd' && number.charAt(x+1) != 'D'){
                throw new MalformedNumberException("Elbonian numerals not in proper order");
            }
            else if (c == 'l' && number.charAt(x+1) != 'L'){
                throw new MalformedNumberException("Elbonian numerals not in proper order");
            }

            for (int i = number.length() - 1; i > x; i--){
                char b = number.charAt(i);
                if (c == 'I' && b != 'I'){
                    throw new MalformedNumberException("Elbonian numerals not in proper order");
                }
                else if (c == 'V' && b != 'I' && b != 'v' || (i == (x+2) && b != 'V')){
                    throw new MalformedNumberException("Elbonian numerals not in proper order");
                }
                else if (c == 'X' && b != 'I' && b != 'v' && b != 'V' && b != 'X'){
                    throw new MalformedNumberException("Elbonian numerals not in proper order");
                }
                else if (c == 'L' && b != 'I' && b != 'v' && b != 'V' && b != 'X' && b != 'l' || (i == (x+2) && b != 'L')){
                    throw new MalformedNumberException("Elbonian numerals not in proper order");
                }
                else if (c == 'C' && b != 'I' && b != 'v' && b != 'V' && b != 'X' && b != 'l' && b != 'L' && b != 'C'){
                    throw new MalformedNumberException("Elbonian numerals not in proper order");
                }
                else if (c == 'D' && b != 'I' && b != 'v' && b != 'V' && b != 'X' && b != 'l' && b != 'L' && b != 'C' && b != 'd' || (i == (x+2) && b != 'D')){
                    throw new MalformedNumberException("Elbonian numerals not in proper order");
                }
            }

        }

        //if a elbonian number contains 4 of any M,C,X,I
        //if an elbonian number contains more that 2 of any D,L,V
        //if an elbonian number contains more than one of any d,l,v
        for (int x = number.length() -1; x >= 0; x--) {
            char c = number.charAt(x);
            int M = 0;
            int C = 0;
            int X = 0;
            int I = 0;

            int D = 0;
            int L = 0;
            int V = 0;

            int d = 0;
            int l = 0;
            int v = 0;

            if (c == 'M'){
                M += 1;
            }
            else if (c == 'C'){
                C += 1;
            }
            else if (c == 'X'){
                X += 1;
            }
            else if (c == 'I'){
                I += 1;
            }

            if (c == 'D'){
                D += 1;
            }
            else if (c == 'L'){
                L += 1;
            }
            else if (c == 'V'){
                V += 1;
            }

            if (c == 'd'){
                d += 1;
            }
            else if (c == 'l'){
                l += 1;
            }
            else if (c == 'v'){
                v += 1;
            }

            if (M >= 4 || C >= 4 || X >= 4 || I >= 4 || D >= 4 || L >= 4 || V >= 4 || d >= 4 || l >= 4 || v >= 4){
                throw new MalformedNumberException("Too many of one elbonian number");
            }
        }

        this.number = number;
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        // TODO Fill in the method's body
        int arabic = 0;
        if (number.contains("VvV")){
            arabic = arabic + 9;
        }
        else if (number.contains("VIII")){
            arabic = arabic + 8;
        }
        else if (number.contains("VII")){
            arabic = arabic + 7;
        }
        else if (number.contains("VI")){
            arabic = arabic + 6;
        }
        else if (number.contains("V")){
            arabic = arabic + 5;
        }
        else if (number.contains("vV")){
            arabic = arabic + 4;
        }
        else if (number.contains("III")){
            arabic = arabic + 3;
        }
        else if (number.contains("II")){
            arabic = arabic + 2;
        }
        else if (number.contains("I")){
            arabic = arabic + 1;
        }

        if (number.contains("LlL")){
            arabic = arabic + 90;
        }
        else if (number.contains("LXXX")){
            arabic = arabic + 80;
        }
        else if (number.contains("LXX")){
            arabic = arabic + 70;
        }
        else if (number.contains("LX")){
            arabic = arabic + 60;
        }
        else if (number.contains("L")){
            arabic = arabic + 50;
        }
        else if (number.contains("lL")){
            arabic = arabic + 40;
        }
        else if (number.contains("XXX")){
            arabic = arabic + 30;
        }
        else if (number.contains("XX")){
            arabic = arabic + 20;
        }
        else if (number.contains("X")){
            arabic = arabic + 10;
        }

        if (number.contains("DdD")){
            arabic = arabic + 900;
        }
        else if (number.contains("DCCC")){
            arabic = arabic + 800;
        }
        else if (number.contains("DCC")){
            arabic = arabic + 700;
        }
        else if (number.contains("DC")){
            arabic = arabic + 600;
        }
        else if (number.contains("D")){
            arabic = arabic + 500;
        }
        else if (number.contains("dD")){
            arabic = arabic + 400;
        }
        else if (number.contains("CCC")){
            arabic = arabic + 300;
        }
        else if (number.contains("CC")){
            arabic = arabic + 200;
        }
        else if (number.contains("C")){
            arabic = arabic + 100;
        }

        if (number.contains("MMM")){
            arabic = arabic + 3000;
        }
        else if (number.contains("MM")){
            arabic = arabic + 2000;
        }
        else if (number.contains("M")){
            arabic = arabic + 1000;
        }

        return arabic;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() {
        // TODO Fill in the method's body
        return "I";
    }

    private String removeSpaces(String input){
        char[] inputArray = input.toCharArray();
        char[] outputArray = new char[input.length()];

        for(int i=0; i<input.length(); i++){
            if(inputArray[i] != ' '){
                outputArray[i] = inputArray[i];
            }
        }
       System.out.println(new String(outputArray));
        return new String(outputArray);
    }

}
