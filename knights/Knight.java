package knights;

import castle.Castle;

public interface Knight {
  // Think that an abstract class would be more appropriate for this situation so the class feeds
  // into an abstract class.

  // Initialised all the methods that the specs required.
  int getLevel();

  int upgradeCost();

  void increaseLevel();

  int defence(Castle building);

  // Method to retrieve the type of student.
  String getStudType();
}