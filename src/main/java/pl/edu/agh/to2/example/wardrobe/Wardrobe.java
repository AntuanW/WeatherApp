package pl.edu.agh.to2.example.wardrobe;

import java.util.List;

public interface Wardrobe {
    boolean checkUmbrella();
    boolean checkGasMask();
    List<Item> getClothing();
    void addCloth(Item cloth);
    void setUmbrella(boolean takeUmbrella);
    void setGasMask(boolean takeGasMask);
}
