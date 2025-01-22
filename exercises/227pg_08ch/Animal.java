public abstract class Animal {

    protected final String name;
    protected boolean isHungry = true;
    protected boolean isTired = true;

    protected abstract void makeNoise();

    protected abstract void eat();

    protected void sleep() {
        this.isTired = false;
        this.isHungry = true;
    }

    protected abstract void roam();

    protected Animal(String name) {
        this.name = name;
    }
}
