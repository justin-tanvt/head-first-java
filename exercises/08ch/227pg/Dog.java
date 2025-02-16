public class Dog extends Canine implements Pet {

    @Override
    protected void makeNoise() {
        System.out.println(this.getClass() + " barks!");
    }

    @Override
    protected void eat() {
        System.out.println(this.getClass() + " eats dog food!");
        this.isHungry = false;
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void beFriendly() {
        System.out.println(this.getClass() + " licks human faces!");
    }

    public void play() {
        System.out.println(this.getClass() + " plays with dog toys!");
    }
}
