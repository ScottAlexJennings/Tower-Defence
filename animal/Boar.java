package animal;

public class Boar extends Animal {

  public Boar(String name, int level, int initialSteps) {
    // Hard coding the values of baseHp and baseSteps so that it mustn't be typed repeatedly
    super(name, 10, 2, level, initialSteps);
  }
}
