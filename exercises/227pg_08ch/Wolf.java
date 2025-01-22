public class Wolf extends Canine {

    @Override
    protected void makeNoise() {
        System.out.println(this.getClass() + " howls!");
    }

    @Override
    protected void eat() {
        System.out.println(this.getClass() + " eats other animals!");
        this.isHungry = false;
    }

    public Wolf(String name) {
        super(name);
    }
}
