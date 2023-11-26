package pl.edu.agh.to2.example.wardrobe;

import java.util.List;

public class Clothes {
    private Item shoes;
    private Item trousers;
    private List<Item> top;
    private List<Item> accessories;


    private Clothes(Builder builder) {
        this.shoes = builder.shoes;
        this.trousers = builder.trousers;
        this.top = builder.top;
        this.accessories = builder.accessories;
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

    //Builder class

    public static class Builder {
        private Item shoes;
        private Item trousers;
        private List<Item> top;
        private List<Item> accessories;

        public Builder setShoes(Item shoes) {
            this.shoes = shoes;
            return this;
        }

        public Builder setTrousers(Item trousers) {
            this.trousers = trousers;
            return this;
        }

        public Builder setTop(List<Item> top) {
            this.top = top;
            return this;
        }

        public Builder setAccessories(List<Item> accessories) {
            this.accessories = accessories;
            return this;
        }

        public Clothes build() {
            return new Clothes(this);
        }
    }
}
