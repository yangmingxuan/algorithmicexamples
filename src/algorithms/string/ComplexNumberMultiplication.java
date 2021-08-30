package algorithms.string;
/***
 * A complex number can be represented as a string on the form "real+imaginaryi" where:

    real is the real part and is an integer in the range [-100, 100].
    imaginary is the imaginary part and is an integer in the range [-100, 100].
    i2 == -1.
    Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.
    
     
    
    Example 1:
    
    Input: num1 = "1+1i", num2 = "1+1i"
    Output: "0+2i"
    Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
    Example 2:
    
    Input: num1 = "1+-1i", num2 = "1+-1i"
    Output: "0+-2i"
    Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
     
    
    Constraints:
    
    num1 and num2 are valid complex numbers.
 * @author miche
 *
 */
public class ComplexNumberMultiplication {

    public String complexNumberMultiply(String num1, String num2) {
        int[] complexN1 = getRealImaginary(num1);
        int[] complexN2 = getRealImaginary(num2);
        return (complexN1[0]*complexN2[0]-complexN1[1]*complexN2[1]) + "+" + 
                (complexN1[0]*complexN2[1]+complexN1[1]*complexN2[0]) + "i";
    }

    private int[] getRealImaginary(String num) {
        String[] complexN = num.split("\\+|i");
        int real = Integer.parseInt(complexN[0]);
        int imaginary = Integer.parseInt(complexN[1]);
        return new int[] {real, imaginary};
    }
}
