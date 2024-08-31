package students;

import bugs.Bug;

public class AiStudent extends StudentAbs {


  public AiStudent(int level) {
    // Hard coding the values of baseAtk, baseDly, and bugsEffected so that it mustn't be typed repeatedly
    super(level, 7, 7, 3);
  }

  // Define the conditions of this students special attack
  @Override
  public void specialAtk(Bug bug) {
    // Run 3 times in abstract.
    bug.damage(Math.round(this.baseAtk * (float) Math.pow(this.level, 1.2)));
  }

  // To retrieve the student type.
  @Override
  public String getStudType() {
    return "AI Student";
  }

}