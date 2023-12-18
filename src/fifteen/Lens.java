package fifteen;

public class Lens {
        String name;
        Integer value;

        public Lens(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString() {
            return name + " " + value;
        }
}
