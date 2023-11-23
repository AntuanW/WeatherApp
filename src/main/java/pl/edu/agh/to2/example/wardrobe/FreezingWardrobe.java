package pl.edu.agh.to2.example.wardrobe;

import java.util.List;

public class FreezingWardrobe implements Wardrobe{
        private boolean takeUmbrella;
        private boolean takeGasMask;
        private List<Item> clothing;

        public FreezingWardrobe() {
        }

        @Override
        public boolean checkUmbrella() {
            return false;
        }

        @Override
        public boolean checkGasMask() {
            return false;
        }

        @Override
        public List<Item> getClothing() {
            return null;
        }

        @Override
        public void addCloth(Item cloth) {
            clothing.add(cloth);
        }

        @Override
        public void setUmbrella(boolean takeUmbrella) {
            this.takeUmbrella = takeUmbrella;
        }

        @Override
        public void setGasMask(boolean takeGasMask) {
            this.takeGasMask = takeGasMask;
        }
}
