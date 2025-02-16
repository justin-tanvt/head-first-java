public class Lion extends Feline {

    @Override
    protected void makeNoise() {
        System.out.println(this.getClass() + " roars!");
    }

    @Override
    protected void eat() {
        System.out.println(this.getClass() + " eats other animals!");
        this.isHungry = false;
    }

    public Lion(String name) {
        super(name);
    }
}
