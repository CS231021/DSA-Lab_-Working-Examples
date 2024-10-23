public class Main{
    public static int sumOfNaturalNumbers(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + sumOfNaturalNumbers(n - 1);
        }
    }

    public static void main(String[] args) {
        int n = 5; 
        System.out.println("Sum of first " + n + " natural numbers: " + sumOfNaturalNumbers(n)); 
    }
}