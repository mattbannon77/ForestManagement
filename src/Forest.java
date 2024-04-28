import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//======================================================================================================================
public class Forest implements Serializable{
    private String name;
    private ArrayList<Tree> trees;

    public Forest(String name) {
        this.name = name;
        this.trees = new ArrayList<>();
    }
//======================================================================================================================
    // Method to print the forest
    public void printForest() {
        System.out.println("Forest name: " + name);
        double sum = 0;
        for (int i = 0; i < trees.size(); i++) {
            System.out.print(i + " ");
            System.out.printf("%-5s ",trees.get(i).getTreeSpecies());
            System.out.printf("%d ",trees.get(i).getYearPlanted());
            System.out.printf("%.2f' ",trees.get(i).getTreeHeight());
            System.out.printf("%.1f%% ",trees.get(i).getTreeGrowth());
            System.out.println();
            sum += trees.get(i).getTreeHeight();
        }
        System.out.printf("There are %d trees, with an average height of %.2f", trees.size(), sum / trees.size());
        System.out.println();
    }
//======================================================================================================================
    // Method to add a randomly generated tree
    public void addRandomTree() {
        Tree randomTree = Tree.generateRandomTree();
        trees.add(randomTree);
    }
//======================================================================================================================
    // Method to cut down a tree by index
    public void cutTree() {
        Scanner scanner = new Scanner(System.in);
        int treeIndex;
        while (true) {
            System.out.print("Tree number to cut down: ");
            String input = scanner.nextLine();
            try {
                treeIndex = Integer.parseInt(input);
                if (treeIndex < 0 || treeIndex >= trees.size()) {
                    System.out.println("Tree number " + treeIndex + " does not exist");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("That is not an integer");
            }
        }
        trees.remove(treeIndex);
    }
//======================================================================================================================
    // Method to simulate a year's growth in the forest
    public void simulateYearlyGrowth() {
        for (Tree tree : trees) {
            double growthRate = tree.getTreeGrowth() / 100;
            double growthIncrement = tree.getTreeHeight() * growthRate;
            double newHeight = tree.getTreeHeight() + growthIncrement;
            tree.setTreeHeight(newHeight);
        }
    }
//======================================================================================================================
    // Method to reap trees over a specified height
    public void reapTrees() {
        Scanner scanner = new Scanner(System.in);
        double heightToReap;
        while (true) {
            System.out.print("Height to reap from: ");
            String input = scanner.nextLine();
            try {
                heightToReap = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("That is not an integer");
            }
        }

        List<Tree> treesToRemove = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.getTreeHeight() > heightToReap) {
                treesToRemove.add(tree);
            }
        }

        for (Tree treeToRemove : treesToRemove) {
            int index = trees.indexOf(treeToRemove);
            trees.remove(index);
            Tree newTree = Tree.generateRandomTree();
            trees.add(index, newTree);
            System.out.printf("Reaping the tall tree  %-6s %d  %.2f'  %.1f%%\n",
                    treeToRemove.getTreeSpecies(), treeToRemove.getYearPlanted(),
                    treeToRemove.getTreeHeight(), treeToRemove.getTreeGrowth());
            System.out.printf("Replaced with new tree %-6s %d  %.2f'  %.1f%%\n",
                    newTree.getTreeSpecies(), newTree.getYearPlanted(),
                    newTree.getTreeHeight(), newTree.getTreeGrowth());
        }
    }
//======================================================================================================================
    // Method to save the forest to a file
    public static boolean saveForestToFile(Forest myForest) {
        ObjectOutputStream toStream = null;
        String fileName = myForest.name + ".db";

        try {
            toStream = new ObjectOutputStream(new FileOutputStream(fileName));
            toStream.writeObject(myForest);
            return (true);
        } catch (IOException e) {
            System.out.println("ERROR saving " + e.getMessage());
            return (false);
        } finally {
            if (toStream != null) {
                try {
                    toStream.close();
                } catch (IOException e) {
                    System.out.println("ERROR closing " + e.getMessage());
                    return (false);
                }
            }
        }
    }
//======================================================================================================================
    // Method to read forest from .csv
    public boolean readForestFromCSV (String name){
        String filePath = name + ".csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine())!= null){
                String[]parts = line.split(",");
                String species = parts[0];
                int year = Integer.parseInt(parts[1]);
                double height = Double.parseDouble(parts[2]);
                double growthRate = Double.parseDouble(parts[3]);

                Tree tree = new Tree(species,year,height,growthRate);
                addTree(tree);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error opening/reading " + filePath + ":" + e.getMessage());
            return false;
        }
    }
//======================================================================================================================
    //Method to load forest from file
    public static Forest loadForestFromFile (String fileName) {
        ObjectInputStream fromStream = null;
        Forest newForest;

        try {
            fromStream = new ObjectInputStream(new FileInputStream(fileName));
            newForest = (Forest) fromStream.readObject();
        } catch (IOException e) {
            System.out.println("ERROR opening/reading from " + e.getMessage());
            return (null);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return (null);
        } finally {
            if (fromStream != null) {
                try {
                    fromStream.close();
                } catch (IOException e) {
                    System.out.println("ERROR closing " + e.getMessage());
                    return (null);
                }
            }
        }
        return (newForest);
    }
//======================================================================================================================
    private void addTree(Tree tree) {

        trees.add(tree);
    }
//======================================================================================================================
    public String getName() {

        return name;
    }
//======================================================================================================================

} // End of the Forest class

