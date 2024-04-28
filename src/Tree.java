import java.io.Serializable;
import java.util.ArrayList;

public class Tree implements Serializable {
    private String treeSpecies;
    private int yearPlanted;
    private double treeHeight;
    private double treeGrowth;
//======================================================================================================================
    // Constructors
    public Tree(String treeSpecies, int yearPlanted, double treeHeight, double treeGrowth) {
        this.treeSpecies = treeSpecies;
        this.yearPlanted = yearPlanted;
        this.treeHeight = treeHeight;
        this.treeGrowth = treeGrowth;
    }
//======================================================================================================================
    // Getters and Setters
    public String getTreeSpecies() {

        return treeSpecies;
    }
//======================================================================================================================
    public void setTreeSpecies(String treeSpecies) {

        this.treeSpecies = treeSpecies;
    }
//======================================================================================================================
    public int getYearPlanted() {

        return yearPlanted;
    }
//======================================================================================================================
    public void setYearPlanted(int yearPlanted) {

        this.yearPlanted = yearPlanted;
    }
//======================================================================================================================
    public double getTreeHeight() {

        return treeHeight;
    }
//======================================================================================================================
    public void setTreeHeight(double treeHeight) {

        this.treeHeight = treeHeight;
    }
//======================================================================================================================
    public double getTreeGrowth() {

        return treeGrowth;
    }
//======================================================================================================================
    public void setTreeGrowth(double treeGrowth) {

        this.treeGrowth = treeGrowth;
    }
//======================================================================================================================
    // Override toString() method
    @Override
    public String toString() {
        return "Tree{" +
                "species=" + treeSpecies +
                ", yearPlanted=" + yearPlanted +
                ", treeHeight=" + treeHeight +
                ", treeGrowth=" + treeGrowth +
                '}';
    }
//======================================================================================================================
    // Method to generate a random tree
    public static Tree generateRandomTree() {
        String treeType;
        int randomNumber = (int) (Math.random() * 3);
        if (randomNumber == 0) {
            treeType = "Maple";
        } else if (randomNumber == 1) {
            treeType = "Fir";
        }else {
            treeType = "Birch";
        }
        int yearPlanted = (int) (Math.random() * 55) + 1970;
        double treeHeight = (Math.random() * 10) + 10;
        double treeGrowth = (Math.random() * 10) + 10;

        return new Tree(treeType, yearPlanted, treeHeight, treeGrowth);
    }
//======================================================================================================================

} // End of the Tree class


