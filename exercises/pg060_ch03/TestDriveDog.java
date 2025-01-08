class TestDriveDog {
  public static void main(String[] args) {
    Dog[] pets;
    pets = new Dog[7];

    pets[0] = new Dog();
    pets[1] = new Dog();
    pets[3] = pets[0];
  }
}
