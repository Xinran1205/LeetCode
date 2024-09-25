/**
* @description TODO
* @author  Xinran
* @date 2024/6/7 16:59
* @version 1.0
*/

class solution{
    public boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }else{
            // 1. Calculate the number of digits in the number x
            int temp = x;
            int digits = 1;
            while (temp>9){
                digits *= 10;
                temp = temp/10;
            }

            // 2. Compare the head and tail of the number x
            while (x>0){
                int head = x/digits;
                int tail = x%10;
                if (head != tail){
                    return false;
                }
                x = x%digits;
                x = x/10;
                digits = digits/100;
            }
            return true;
        }
    }
}

// convert the number to a string
class solution2{
    public boolean isPalindrome(int x){
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }
}

class solution3{
    public boolean isPalindrome(int x){
        if (x<0 || (x%10 == 0 && x!=0)){
            return false;
        }
        int revertedNumber = 0;
        while (x>revertedNumber){
            revertedNumber = revertedNumber*10 + x%10;
            x = x/10;
        }
        return x == revertedNumber || x == revertedNumber/10;
    }
}

public class PalindromeNumber {
    public static void main(String[] args) {
        solution3 a = new solution3();
        System.out.println(a.isPalindrome(10011));
        System.out.println(a.isPalindrome(12345));
        System.out.println(a.isPalindrome(12321));
    }
}


