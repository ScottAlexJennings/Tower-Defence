package knights;

import animal.Animal;

public class LancerKnight extends KnightAbs {


  public LancerKnight(int level) {
    // Hard coding the values of baseAtk, baseDly, and bugsEffected so that it mustn't be typed repeatedly
    super(level, 6, 6, 1);
  }

  // Define the conditions of this students special attack
  @Override
  public void specialAtk(Animal bug) {
    // Run 1 time in abstract.
    int damageCombo = 4;
    int attack = Math.round(this.baseAtk * (float) Math.pow(this.level, 1.2)) * damageCombo;
    bug.damage(attack);
  }

  // To retrieve the student type.
  @Override
  public String getStudType() {
    return "Comp Student";
  }
}
