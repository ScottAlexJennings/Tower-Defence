package knights;

import java.util.ArrayList;

import castle.Castle;

public class Team {

  private final ArrayList<Knight> students = new ArrayList<>();
  int knowledgePoints;

  // Constructor
  public Team(int initKnwlPoints) {
    this.knowledgePoints = initKnwlPoints;
  }

  // Getters
  public int getKnowledgePoints() {
    return this.knowledgePoints;
  }

  public Knight[] getStudents() {
    return students.toArray(new Knight[0]);
  }

  public int getNewStudentCost() {
    return 100 + (10 * students.size());
  }

  // Make the students attack the bugs, all students in one step. Accumulates knowledge points
  public void studentsAct(Castle building) {
    for (Knight student : getStudents()) {
      this.knowledgePoints += student.defence(building);
    }
  }

  // Method to add another student to the arraylist of students.
  public void recruitNewStudent() throws Exception {
    // In case there are not enough knowledge points to get a new student the exception has been
    // thrown to keep the simulation going.
    if (this.knowledgePoints < getNewStudentCost()) {
      throw new Exception("Oh no, cant afford new student");
    } else {
      // Reduce the knowledge points by the cost of a student.
      this.knowledgePoints -= getNewStudentCost();
    }

    // Array of the possible new students that could be added to the team
    Knight[] possibleStudents = {new ArcherKnight(1),
        new LancerKnight(1),
        new ShieldKnight(1),
        new StewardKnight(1)};

    // Choose a student at random from the array of student to join the team as per the specs. Easier
    // than implementing nextDouble and shows different skills.
    java.util.Random random = new java.util.Random();
    int chosenStudent = random.nextInt(possibleStudents.length);

    // Add the chosen student to the arraylist.
    students.add(possibleStudents[chosenStudent]);
  }

  // Method to upgrade the level of a student to do more damage.
  public void upgrade(Knight student) throws Exception {
    // In case there are not enough knowledge points to upgrade a student the exception has been
    // thrown to keep the simulation going.
    if (this.knowledgePoints < student.upgradeCost()) {
      throw new Exception("Oh no, cant afford to upgrade student");
    } else {
      // Reduce the knowledge points by the cost of upgrading a student.
      this.knowledgePoints -= student.upgradeCost();
    }

    // Student level increase
    student.increaseLevel();
  }
}
