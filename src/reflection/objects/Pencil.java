package reflection.objects;

public class Pencil extends Product{
    public Pencil() {
    }

    @Override
    public String getName() {
        return "pencil";
    }

    @Override
    public int getPrice() {
        return 200;
    }
}
