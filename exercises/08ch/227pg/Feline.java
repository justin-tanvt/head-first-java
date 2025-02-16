public abstract class Feline extends Animal {

    protected void roam() {
        System.out.println(this.getClass() + " roams alone!");
    }

    protected Feline(String name) {
        super(name);
    }
}
