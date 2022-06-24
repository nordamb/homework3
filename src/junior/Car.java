package junior;

public abstract class Car {
    public void start() {
        System.out.println("Поехали");
    }

    public void stop() {
        System.out.println("Приехали");
    }

    public void lights() {
        System.out.println("Да будет свет");
    }

    public abstract void feature();
}
