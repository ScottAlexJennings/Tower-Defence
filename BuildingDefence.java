import bugs.Bug;
import bugs.ConcurrentModificationBug;
import bugs.NoneTerminationBug;
import bugs.NullPointerBug;
import building.Building;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import students.Team;

public class BuildingDefence {

  // Method that makes an arraylist of the bugs on a certain line from the .txt file. Takes the file
  // name and the current wave that is determined to entre.
  private static ArrayList<Bug> makeWavesOfBugs(String fileName, int waveNum) {
    // Initiate arraylist
    ArrayList<Bug> nextWave = null;

    // Try/catch block to catch the exception for no file or line
    try (BufferedReader ignored = new BufferedReader(new FileReader(fileName))) {
      // Simplify code by making variable
      Path path = Paths.get(fileName);
      String line = Files.readAllLines(path).get(waveNum);
      // Makes an empty arraylist to populate
      nextWave = new ArrayList<>();
      // Read each line
      String[] separatedBugs = line.split(";");

      // Separate components of string
      for (String bugNotFormatted : separatedBugs) {
        int bk1 = bugNotFormatted.indexOf("(");
        int bk2 = bugNotFormatted.indexOf(")");
        int comma1 = bugNotFormatted.indexOf(",");
        int comma2 = bugNotFormatted.indexOf(",", bugNotFormatted.indexOf(",") + 1);

        // Organise string information
        String bugName = bugNotFormatted.substring(0, bk1);
        String bugType = bugNotFormatted.substring(bk1 + 1, comma1);
        int bugLevel = Integer.parseInt(bugNotFormatted.substring(comma1 + 1, comma2));
        int bugSteps = Integer.parseInt(bugNotFormatted.substring(comma2 + 1, bk2));

        // Determine type og bug, make bug and add bugs
        if (Objects.equals(bugType, "CMB")) {
          nextWave.add(new ConcurrentModificationBug(bugName, bugLevel, bugSteps));
        } else if (Objects.equals(bugType, "NPB")) {
          nextWave.add(new NullPointerBug(bugName, bugLevel, bugSteps));
        } else {
          nextWave.add(new NoneTerminationBug(bugName, bugLevel, bugSteps));
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("The file was not found");
    } catch (IOException | IndexOutOfBoundsException e) {
      // When no more lines are found in the file terminate the program
      System.out.println();
      System.out.println(
          "============================================================================");
      System.out.println(
          "Congratulations you succeeded at defending the building from the invasion!!!");
      System.out.println(
          "============================================================================");

      System.exit(0);
    }
    return nextWave;
  }

  public static void main(String[] args) {

    // Get arguments from command line to use in simulation
    int topFloor = Integer.parseInt(args[0]);
    int constructionPoints = Integer.parseInt(args[1]);
    String fileName = args[2];
    int initialKnowledgePoints = Integer.parseInt(args[3]);

    // Create objects
    Building zepler = new Building(topFloor, constructionPoints);
    Team aTeam = new Team(initialKnowledgePoints);
    Battle battle = new Battle(aTeam, zepler);

    // Counter to know what step it is and see if the next wave should come. WaveNum to record how
    // many waves have happened and thereby which line to retrieve on from the file on the next wave.
    int counter = 0;
    int waveNum = 0;

    // Loop the simulation until the building is destroyed, other way of ending the simulation is
    // by defeating all the bugs. That termination is in makeWaveOfBugs() for when there are no more line.
    while (zepler.getConstructionPoints() > 0) {
      if (counter % (8 * topFloor) == 0 || counter == 0) {
        for (Bug bug : makeWavesOfBugs(fileName, waveNum)) {
          zepler.addBug(bug);
        }
        waveNum++;
      }
      // Take one step further into the simulation.
      battle.step();
      counter++;
      // Try and remove. Looks like simulation has crashed sometimes because step() has the same
      // output. Measures user that things are happening
      System.out.println("Move: " + counter);
    }
  }
}
