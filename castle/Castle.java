package castle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import animal.Animal;
import animal.Wolf;
import animal.Bear;

public class Castle {

  public ArrayList<Animal> bugs = new ArrayList<>();
  int constructionPoints;
  int topFloor;

  public Castle(int topFloor, int constructionPoints) {
    this.constructionPoints = constructionPoints;
    this.topFloor = topFloor;
  }

  // Getters for this class, used in Battle class to display bug information.
  public int getConstructionPoints() {
    return this.constructionPoints;
  }

  public int getTopFloor() {
    return this.topFloor;
  }

  // Method to assemble all the bugs that currently reside within the building in descending order.
  public Animal[] getAllBugs() {
    // Made new arraylist so not to get confused and lose bugs, and populated with original arraylist!
    ArrayList<Animal> bugsInBuilding = new ArrayList<>(bugs);
    // Condition to remove the bugs that should not be in the building as per specifications.
    bugsInBuilding.removeIf(bug -> bug.getCurrentHp() <= 0 || bug.getCurrentFloor() < 0);
    // Collection that is able to sort arraylist of objects because of comparable
    Collections.sort(bugsInBuilding);
    // Returning an array instead of arraylist as per specifications.
    return bugsInBuilding.toArray(new Animal[0]);
  }

  // Add the bugs to the original arraylist, easier to manipulate arraylist.
  public int addBug(Animal bug) {
    // Start by checking if it exists and if not add to save code.
    if (bugs.contains(bug)) {
      return -1;
    } else {
      bugs.add(bug);
    }
    return bugs.size();
  }

  // Method to remove bug, where called has check conditions.
  public void removeBug(Animal bug) {
    bugs.remove(bug);
    System.out.println("Bug has died!");
  }

  // The array of bugs is iterated through and in turn the bugs are moved. The code also checks to
  // see if and what type of bug has reached the top floor.
  public void bugsMove() {
    // Iterate over original arraylist rather than array because then the bugs in position -1 will
    // progress into the building.
    Iterator<Animal> i = bugs.iterator();

    // Each bug moves then checks if it is on the top floor.
    while (i.hasNext()) {
      Animal bug = i.next();

      // Call bugs to move.
      bug.move();

      // Check floor, do constructional damage, then remove, then notify user.
      if (bug.getCurrentFloor() == getTopFloor()) {
        if (bug instanceof Wolf) {
          this.constructionPoints -= 2;
          i.remove();
          System.out.println("Bug has attacked the building");
        } else if (bug instanceof Bear) {
          this.constructionPoints -= 4;
          i.remove();
          System.out.println("Bug has attacked the building");
        } else {
          this.constructionPoints -= 1;
          i.remove();
          System.out.println("Bug has attacked the building");

        }
      }

      // Ensure that the building does not go below zero points, as a building cannot be in the
      // negative.
      if (this.constructionPoints <= 0) {
        constructionPoints = 0;
        break;
      }
    }
  }
}
