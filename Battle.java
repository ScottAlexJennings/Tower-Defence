import knights.Knight;
import knights.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import animal.Animal;
import castle.Castle;

public class Battle {

  Team aTeam;
  Castle building;

  // Constructor
  public Battle(Team aTeam, Castle building) {
    this.aTeam = aTeam;
    this.building = building;
  }

  // Create an array of the current students in the team and iterate through then to find a student at the lowest level.
  private Knight cheapestStudUpg() {
    Knight[] studentArr = aTeam.getStudents();

    // Variable to hold the lowest level student
    Knight minLvl = studentArr[0];
    for (Knight student : studentArr) {
      // Check if the current or next student has a lower level.
      if (student.getLevel() < minLvl.getLevel()) {
        // If next is lower save it.
        minLvl = student;
      }
    }
    return minLvl;
  }

  // The brains of the simulation.
  // Choose to improve the cheapest feature, because having more students is far more useful
  // than upgrading a student in terms of raw damage. Need like 20 something students for the
  // cost per damage to be more by upgrading.
  private void manageTeam() {
    // If the team is empty a ArrayIndexOutOfBoundsException will occur.
    try {
      // The first thing to do is get the first student.
      if (this.aTeam.getStudents().length == 0) {
        aTeam.recruitNewStudent();
        System.out.println("First student has been recruited!");
      } else {

        // The strategy is buy students until it is cheaper to upgrade them, then upgrade them all
        // in turn, then buy more students.

        // Variables to simplify code
        int stdNewCost = this.aTeam.getNewStudentCost();
        int klgPnt = this.aTeam.getKnowledgePoints();
        int stdUpCost = this.cheapestStudUpg().upgradeCost();

        // If statement to determine the cheapest option.
        if (stdNewCost < klgPnt && stdNewCost < stdUpCost) {
          this.aTeam.recruitNewStudent();

        } else if (stdUpCost < klgPnt) {
          this.aTeam.upgrade(this.cheapestStudUpg());
        }

      }
    } catch (Exception e) {
      System.out.println("Not enough Knowledge Points to make any changes.");
      System.out.println();
    }
  }

  // Info that simulation considers
  private void simulationInfo() {
    // Knowledge Points
    System.out.println("Knowledge Points before manage: " + aTeam.getKnowledgePoints());
    // Cost of new student
    System.out.println("Cost of a new student: " + this.aTeam.getNewStudentCost());
    //Cost of upgrading student
    try {
      System.out.println("Cost of upgrading a student: " + this.cheapestStudUpg().upgradeCost());
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Cost of upgrading a student: 200");
    }
  }

  // Managing the team
  private void manageInfo() {
    System.out.println("A-Team has been managed.");
    // Knowledge Points left over
    System.out.println("Knowledge Points remaining: " + aTeam.getKnowledgePoints());
  }

  // Print the student level because I do not know how to print one parameter of an object in an
  // object array.
  private void printStudLevel(ArrayList<Knight> arrayList) {
    if (arrayList.size() == 1) {
      System.out.println("There is 1 " + arrayList.get(0).getStudType());
      System.out.println("Level: [" + arrayList.get(0).getLevel() + "]");
    } else if (arrayList.size() > 1) {
      System.out.println(
          "There are " + arrayList.size() + " " + arrayList.get(0).getStudType() + "s");
      System.out.print("Levels: ");
      List<Integer> levels = new ArrayList<>();
      for (Knight s : arrayList) {
        levels.add(s.getLevel());
      }
      System.out.println(levels);
    }
  }

  // Listing the students in the team
  private void listingStudents() {
    ArrayList<Knight> cyberArray = new ArrayList<>();
    ArrayList<Knight> aiArray = new ArrayList<>();
    ArrayList<Knight> csArray = new ArrayList<>();
    ArrayList<Knight> seArray = new ArrayList<>();

    System.out.println("The current students are: ");
    for (Knight student : aTeam.getStudents()) {
      if (Objects.equals(student.getStudType(), "Cyber Student")) {
        cyberArray.add(student);
      } else if (Objects.equals(student.getStudType(), "AI Student")) {
        aiArray.add(student);
      } else if (Objects.equals(student.getStudType(), "Comp Student")) {
        csArray.add(student);
      } else {
        seArray.add(student);
      }
    }
    printStudLevel(cyberArray);
    printStudLevel(aiArray);
    printStudLevel(csArray);
    printStudLevel(seArray);
  }

  // student and bugs actions
  private void action() {
    // Informing of fighting
    System.out.println("Students have fought back.");
    aTeam.studentsAct(building);
    System.out.println();
    // Informing of movement
    System.out.println("Bugs have moved.");
    building.bugsMove();
  }

  // Listing the bugs in the building
  private void listingBugs() {
    System.out.println("The bugs in the building are:");
    if (building.getAllBugs().length > 0) {
      for (Animal bug : building.getAllBugs()) {
        System.out.println(
            bug.getName() + " is Level " + bug.getLevel() + " and on floor "
                + bug.getCurrentFloor()
                + ", " + bug.getCurrentSteps() + " steps from the next floor with "
                + bug.getCurrentHp() + " HP left.");
      }
    } else {
      // There are no bugs
      System.out.println("There are no bugs in the building");
    }
  }

  // Health of building after movement.
  private void buildingHealth() {
    System.out.println(
        "The building has " + building.getConstructionPoints()
            + " points remaining before destruction!");
    System.out.println();
  }

  // Step is the method that is called in the main to all the simulating minus getting the bugs out
  // of the .txt file.
  public void step() {
    try {
      // Set .sleep to 100 for fast and 1000 for cruising, but there over 1000 steps.
      Thread.sleep(500);

      // Formatting
      System.out.println();
      System.out.println("=========================================");
      System.out.println();

      simulationInfo();
      System.out.println();

      manageTeam();
      manageInfo();
      System.out.println();

      listingStudents();
      System.out.println();

      action();
      System.out.println();

      listingBugs();
      System.out.println();

      buildingHealth();

      // Lost the simulation.
      if (building.getConstructionPoints() <= 0) {
        System.out.println("The building has been destroyed!");
      }
      System.out.println();

    } catch (InterruptedException ignored) {
    }
  }
}
