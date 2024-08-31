package animal;

public class Wolf extends Animal {

  public Wolf(String name, int level, int initialSteps) {
    // Hard coding the values of baseHp and baseSteps so that it mustn't be typed repeatedly
    super(name, 20, 4, level, initialSteps);
  }
}
