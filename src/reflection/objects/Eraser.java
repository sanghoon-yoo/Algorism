package reflection.objects;

public class Eraser extends Product {
    public Eraser() {
    }

    @Override
    public String getName() {
        return "eraser";
    }

    @Override
    public int getPrice() {
        return 500;
    }
}
