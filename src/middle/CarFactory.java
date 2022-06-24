package middle;

import junior.Toyota;
import junior.Zhiguly;

public class CarFactory {

    public static Zhiguly getZhiguly() {
        return new Zhiguly();
    }

    public static Toyota getToyota() {
        return new Toyota();
    }
}
