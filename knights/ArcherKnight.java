package knights;

import java.util.Random;

import animal.Animal;

public class ArcherKnight extends KnightAbs {


  public ArcherKnight(int level) {
    // Hard coding the values of baseAtk, baseDly, and bugsEffected so that it mustn't be typed repeatedly
    super(level, 7, 8, 1);
  }

  // Define the conditions of this students special attack
  @Override
  public void specialAtk(Animal bug) {
    // Determining the amount of damage if probability fails.
    int attack = Math.round(this.baseAtk * (float) Math.pow(this.level, 1.2)) * 2;

    // Making the probability according to specs.
    double probability = ((float) this.level + 20) / 100;
    if (probability > 0.5) {
      probability = 0.5;
    }

    // Running the probability.
    if (new Random().nextDouble() <= probability) {
      // Chose an arbitrarily large number as instant kill.
      bug.damage(100000);
    } else {
      bug.damage(attack);
    }
  }

  // To retrieve the student type.
  @Override
  public String getStudType() {
    return "Cyber Student";
  }
}