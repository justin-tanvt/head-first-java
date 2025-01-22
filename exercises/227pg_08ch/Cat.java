public class Cat extends Feline implements Pet {

    @Override
    protected void makeNoise() {
        System.out.println(this.getClass() + " meows!");
    }

    @Override
    protected void eat() {
        System.out.println(this.getClass() + " eats cat food!");
        this.isHungry = false;
    }

    public Cat(String name) {
        super(name);
    }

    @Override
    public void beFriendly() {
        System.out.println(this.getClass() + " sits on human laps!");
    }

    @Override
    public void play() {
        System.out.println(this.getClass() + " plays with cat toys!");
    }
}
