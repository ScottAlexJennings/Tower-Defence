package bugs;

public class NullPointerBug extends Bug {

  public NullPointerBug(String name, int level, int initialSteps) {
    // Hard coding the values of baseHp and baseSteps so that it mustn't be typed repeatedly
    super(name, 10, 2, level, initialSteps);
  }
}
