import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//======================================================================================================================
public class ForestSimulation implements Serializable {
    private static final String FILE_EXTENSION = ".csv";
    private static final List<Forest> forestList = new ArrayList<>();
    private static int currentForestIndex = 0;
//======================================================================================================================
    // Main method
    public static void main(String[] args) {
        System.out.println("Welcome to the Forestry Simulation");
        System.out.println("----------------------------------");
        initializeForestsFromCommandLine(args);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        if(forestList.isEmpty()){
            System.out.println("No forests specified, exiting");
            return;
        }

        while (!exit && currentForestIndex < forestList.size()) {
            Forest currentForest = forestList.get(currentForestIndex);
            System.out.println("Initializing from " + currentForest.getName());
            String choice;
            Forest newForest;
            while (true) {
                displayMenu();
                choice = scanner.nextLine().toUpperCase();
                switch (choice) {
                    case "P":
                        currentForest.printForest();
                        break;
                    case "A":
                        currentForest.addRandomTree();
                        break;
                    case "C":
                        currentForest.cutTree();
                        break;
                    case "G":
                        currentForest.simulateYearlyGrowth();
                        break;
                    case "R":
                        currentForest.reapTrees();
                        break;
                    case "S":
                        currentForest.saveForestToFile(currentForest);
                        break;
                    case "L":
                        System.out.println("Enter forest name: ");
                        if ((newForest = currentForest.loadForestFromFile(scanner.nextLine() + ".db")) != null) {
                            currentForest = newForest;
                        } else {
                            System.out.println("Old Forest retained");
                        }
                        break;
                    case "N":
                        System.out.println("Moving to the next forest");
                        currentForestIndex++;
                        break;
                    case "X":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid menu option, try again");
                }

                if (choice.equals("N")) {
                    break;
                }
                if (choice.equals("X")){
                    System.out.println("Exiting the Forestry Simulation");
                    break;
                }

            }
        }
    }
//======================================================================================================================
    // Method to initialize forest from command line arguments
    private static void initializeForestsFromCommandLine(String[] args) {
        for (String forestName : args) {
            Forest forest = new Forest(forestName);
            if(forest.readForestFromCSV(forestName)){
                forestList.add(forest);
            }else {
                System.out.println("Error opening/reading " + forestName + FILE_EXTENSION);
            }
        }
    }
//======================================================================================================================
    // Method to display input menu
    private static void displayMenu() {
        System.out.println("(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it : ");
    }
//======================================================================================================================

} // End of the ForestSimulation class

