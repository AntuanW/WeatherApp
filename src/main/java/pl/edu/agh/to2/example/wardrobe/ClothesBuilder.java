package pl.edu.agh.to2.example.wardrobe;
import java.util.List;

public class ClothesBuilder {
    private String shoes;
    private String trousers;
    private List<String> top;
    private List<String> accessories;

    public ClothesBuilder setShoes(String shoes) {
        this.shoes = shoes;
        return this;
    }

    public ClothesBuilder setTrousers(String trousers) {
        this.trousers = trousers;
        return this;
    }

    public ClothesBuilder setTop(List<String> top) {
        this.top = top;
        return this;
    }

    public ClothesBuilder setAccessories(List<String> accessories) {
        this.accessories = accessories;
        return this;
    }

    public Clothes build() {
        return new Clothes(shoes, trousers, top, accessories);
    }
}
