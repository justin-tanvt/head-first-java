public class Hippo extends Animal {

    @Override
    protected void makeNoise() {
        System.out.println(this.getClass() + " grunts!");
    }

    @Override
    protected void eat() {
        System.out.println(this.getClass() + " eats anything!");
        this.isHungry = false;
    }

    @Override
    protected void roam() {
        System.out.println(this.getClass() + " roams amphibiously!");
    }

    protected Hippo(String name) {
        super(name);
    }
}
