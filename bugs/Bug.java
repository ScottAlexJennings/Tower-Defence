package bugs;

public class Bug implements Comparable<Bug> {

  String name;
  int baseHp;
  int baseSteps;
  int level;
  int currentHp;
  int currentSteps;
  int currentFloor = -1;

  // Don't actually know the use of this, but it was in the specification.
  public Bug(String name, int baseHp, int baseSteps, int level) {
    this.name = name;
    this.baseHp = baseHp;
    this.baseSteps = baseSteps;
    this.level = level;
    this.currentHp = Math.round(this.baseHp * (float) Math.pow(this.level, 1.5));
  }

  // Constructor that used to make new bug objects. This one includes initialSteps.
  public Bug(String name, int baseHp, int baseSteps, int level, int initialSteps) {
    this.name = name;
    this.baseHp = baseHp;
    this.baseSteps = baseSteps;
    this.level = level;
    this.currentSteps = initialSteps;
    this.currentHp = Math.round(this.baseHp * (float) Math.pow(this.level, 1.5));
  }

  // Getters for this class, used in Battle class to display bug information.
  public String getName() {
    return this.name;
  }

  public int getBaseSteps() {
    return this.baseSteps;
  }

  public int getLevel() {
    return this.level;
  }

  public int getCurrentHp() {
    return this.currentHp;
  }

  public int getCurrentSteps() {
    return this.currentSteps;
  }

  public int getCurrentFloor() {
    return this.currentFloor;
  }

  // Move an individual bug and in some cases to the next floor. Current steps cannot go below 0
  public void move() {
    if (getCurrentSteps() == 0) {
      this.currentFloor += 1;
      this.currentSteps = this.baseSteps - 1;
    } else {
      this.currentSteps -= 1;
    }
  }

  // Special ability of the SeStudent class to slow down the bug.
  public void slowDown(int steps) {
    this.currentSteps += steps;
  }

  // Ability of the students to do damage to the bug.
  public void damage(int amount) {
    this.currentHp -= amount;
    // Does not go below 0
    if (this.currentHp < 0) {
      this.currentHp = 0;
    }
  }

  // Comparable to sort the order of the bugs in the GetAllBugs array in descending order with
  // priority of floor level over steps to next floor. Easier to implement comparable rather than
  // make own iterator once you understand how.
  @Override
  public int compareTo(Bug compareBug) {
    if (compareBug.getCurrentFloor() == this.getCurrentFloor()) {
      return Integer.compare(this.getCurrentSteps(), compareBug.getCurrentSteps());
    } else if (compareBug.getCurrentFloor() > this.getCurrentFloor()) {
      return 1;
    } else {
      return -1;
    }
  }
}


