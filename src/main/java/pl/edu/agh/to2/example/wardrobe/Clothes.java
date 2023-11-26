package pl.edu.agh.to2.example.wardrobe;

import java.util.List;

public class Clothes {
    private Item shoes;
    private Item trousers;
    private List<Item> top;
    private List<Item> accessories;

    //setters
    public void setShoes(Item shoes) {
        this.shoes = shoes;
    }

    public void setTrousers(Item trousers) {
        this.trousers = trousers;
    }

    public void setTop(List<Item> top) {
        this.top = top;
    }

    public void setAccessories(List<Item> accessories) {
        this.accessories = accessories;
    }

    //getters
    public Item getShoes() {
        return shoes;
    }

    public Item getTrousers() {
        return trousers;
    }

    public List<Item> getTop() {
        return top;
    }

    public List<Item> getAccessories() {
        return accessories;
    }
}
