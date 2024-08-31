package knights;

import animal.Animal;

public class StewardKnight extends KnightAbs {

  public StewardKnight(int level) {
    // Hard coding the values of baseAtk, baseDly, and bugsEffected so that it mustn't be typed repeatedly
    super(level, 5, 6, 5);

  }

  // Define the conditions of this students special attack
  @Override
  public void specialAtk(Animal bug) {
    // Run 5 times in abstract.
    int slowness = 2;
    // Method in bug to slow bug
    bug.slowDown(slowness);
  }

  // To retrieve the student type.
  @Override
  public String getStudType() {
    return "SoftEng Student";
  }
}