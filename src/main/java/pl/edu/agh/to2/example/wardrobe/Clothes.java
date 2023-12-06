package pl.edu.agh.to2.example.wardrobe;

import java.util.List;

public class Clothes {
    private String shoes;
    private String trousers;
    private List<String> top;
    private List<String> accessories;

    public Clothes(String shoes, String trousers, List<String> top, List<String> accessories) {
        this.shoes = shoes;
        this.trousers = trousers;
        this.top = top;
        this.accessories = accessories;
    }

    public String getShoes() {
        return shoes;
    }

    public String getTrousers() {
        return trousers;
    }

    public List<String> getTop() {
        return top;
    }

    public List<String> getAccessories() {
        return accessories;
    }
}
