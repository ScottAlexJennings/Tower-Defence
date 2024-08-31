package knights;

import animal.Animal;
import castle.Castle;

public abstract class KnightAbs implements Knight {
  // Abstract class so the unique attributes of the students can be designed while coordinating the
  // classes of similar type.

  int level;
  int baseAtk;
  int baseDly;
  int bugsEffected;
  int orgBaseDly;

  public KnightAbs(int level, int baseAtk, int baseDly, int bugsEffected) {
    this.level = level;
    this.baseAtk = baseAtk;
    this.baseDly = baseDly;
    this.orgBaseDly = baseDly;
    this.bugsEffected = bugsEffected;
  }

  // Abstract classes that will be filled in subclasses
  // Each student type has a different special
  public abstract void specialAtk(Animal bug);

  // Wanted the name of the student type to present to user in the battle class
  public abstract String getStudType();

  // Classes required by spec to pass test harness and connect later parts of code.
  @Override
  public int getLevel() {
    return this.level;
  }

  // Convert to int to simplify simulation interface
  @Override
  public int upgradeCost() {
    return (int) (100 * Math.pow(2, this.level));
  }

  // Remember to not reduce Knowledge Points by upgrade cost.
  @Override
  public void increaseLevel() {
    this.level++;
  }

  // Take building parameter to access building methods.
  @Override
  public int defence(Castle building) {
    int knowledgePoints = 0;
    try {
      // Reduce the base delay of the student by one, so it is closer to using the special ability.
      this.baseDly--;
      // Make bug array variable so it looks neater.
      Animal[] bugsArray = building.getAllBugs();

      // If statement to decide if to use special ability or normal. Start with special to simply
      // conditions.
      if (this.baseDly == 0) {
        // For loop to use the special ability on all the intended targets.
        for (int i = 0; i < this.bugsEffected; i++) {
          // Bugs sorted by calling array so it is affecting closest bugs to top floor.
          specialAtk(bugsArray[i]);
        }
        // Inform simulation of occurrence
        System.out.println(getStudType() + "has used their special");
        // Reset the delay
        this.baseDly = this.orgBaseDly;
      } else {
        // Do the normal damage to the first bug in the array. Damage done based on spec.
        int damageDone = Math.round(this.baseAtk * (float) Math.pow(this.level, 1.2));
        bugsArray[0].damage(damageDone);
      }

      // If the bug is dead and to the total knowledge points available to the team.
      for (Animal bug : bugsArray) {
        if (bug.getCurrentHp() == 0) {
          knowledgePoints += bug.getLevel() * 20;
          // Remove the dead bug.
          building.removeBug(bug);
        }
      }
      // catch ArrayIndexOutOfBoundsException in the event that array is empty, but the simulation
      // should continue
    } catch (ArrayIndexOutOfBoundsException ignored) {
    }
    return knowledgePoints;
  }

}
