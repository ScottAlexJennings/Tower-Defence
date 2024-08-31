package bugs;

public class NoneTerminationBug extends Bug {

  public NoneTerminationBug(String name, int level, int initialSteps) {
    // Hard coding the values of baseHp and baseSteps so that it mustn't be typed repeatedly
    super(name, 200, 6, level, initialSteps);
  }
}
