package pl.edu.agh.to2.example.wardrobe;

import java.util.List;

public class Clothes {
    private Item shoes;
    private Item trousers;
    private List<Item> top;
    private List<Item> accessories;

    public Item getShoes() {
        return shoes;
    }

    public void setShoes(Item shoes) {
        this.shoes = shoes;
    }

    public Item getTrousers() {
        return trousers;
    }

    public void setTrousers(Item trousers) {
        this.trousers = trousers;
    }

    public List<Item> getTop() {
        return top;
    }

    public void setTop(List<Item> top) {
        this.top = top;
    }

    public List<Item> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Item> accessories) {
        this.accessories = accessories;
    }
}
