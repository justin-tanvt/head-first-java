public class Tiger extends Feline {

    @Override
    protected void makeNoise() {
        System.out.println(this.getClass() + " chuffs!");
    }

    @Override
    protected void eat() {
        System.out.println(this.getClass() + " eats other animals!");
        this.isHungry = false;
    }

    public Tiger(String name) {
        super(name);
    }
}
