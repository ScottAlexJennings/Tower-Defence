package bugs;

public class ConcurrentModificationBug extends Bug {

  public ConcurrentModificationBug(String name, int level, int initialSteps) {
    // Hard coding the values of baseHp and baseSteps so that it mustn't be typed repeatedly
    super(name, 20, 4, level, initialSteps);
  }
}
