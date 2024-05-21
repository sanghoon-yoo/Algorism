package reflection.objects;

public abstract class Product {

    public Product getInstance() {
        return this;
    }
    public abstract String getName();

    public abstract int getPrice();
}
