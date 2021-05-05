package spw4.exploration;

public class Calculator {
    public Calculator() {

    }

    public float add(float x, float y) {
        return x + y;
    }

    public float divide(float x, float y) {
        if (y == 0) throw new ArithmeticException("Division by Zero");

        return x / y;
    }

    public float square(float x) {
        return x * x;
    }

    public float abs(float x) {
        return Math.abs(x);
    }

    public static double pi () {
        return Math.PI;
    }
}
