package animal;

public class Bear extends Animal {

  public Bear(String name, int level, int initialSteps) {
    // Hard coding the values of baseHp and baseSteps so that it mustn't be typed repeatedly
    super(name, 200, 6, level, initialSteps);
  }
}
