public abstract class Canine extends Animal {

    protected void roam() {
        System.out.println(this.getClass() + " roams in packs!");
    }

    public Canine(String name) {
        super(name);
    }
}
