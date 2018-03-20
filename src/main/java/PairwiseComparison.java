import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PairwiseComparison {

    public String id;
    public List<String> parents;
    public List<String> alternatives;
    private double[][] weightRatios;
    private double[] hierarchyVector;

    public PairwiseComparison() {
        parents = new ArrayList<>();
        alternatives =  new ArrayList<>();
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    public static PairwiseComparison get(String id) {
        for (PairwiseComparison comparison : Main.AHP.comparisons) {
            if (comparison.id.equals(id)) {
                return comparison;
            }
        }
        return null;
    }

    public void build() {
        String[] splitUserInput, splitAgainUserInput;
        ArrayList<Double> matrixList = new ArrayList<>();
        Scanner scn;
        boolean success;
        System.out.println("Input id: ");
        scn = new Scanner(System.in);
        setId(scn.nextLine());

        System.out.println("Input alternative seperated by a comma, eg: Power,Wheels,Color\n");
        scn = new Scanner(System.in);
        splitUserInput = scn.nextLine().split(",");
        success = Collections.addAll(alternatives, splitUserInput);
        System.out.println("Input hierarchy seperating individual numbers with comma and rows with semicolon: eg. 0.1,0.9 ");
        scn = new Scanner(System.in);
        splitUserInput = scn.nextLine().split(",");
        int arrayIterator1 = 0;
        hierarchyVector = new double[splitUserInput.length];
        for (String s : splitUserInput) {
            hierarchyVector[arrayIterator1] = Double.parseDouble(s);
            arrayIterator1++;
        }

        System.out.println("Input weight matrix seperating individual numbers with comma and rows with semicolon: eg. 1,2;3,4 ");
        scn = new Scanner(System.in);
        splitUserInput = scn.nextLine().split(";");
        for (String s1 : splitUserInput) {
            splitAgainUserInput = s1.split(",");
            for (String s2 : splitAgainUserInput) {
                matrixList.add(Double.parseDouble(s2));
            }
        }
        int arrayIterator2 = 0;
        int arrayIterator3 = 0;
        double matrixSize = Math.sqrt(matrixList.size());
        weightRatios = new double[(int)matrixSize][(int)matrixSize];
        for (double d : matrixList) {
            if (arrayIterator3 > matrixSize-1) {
                arrayIterator2++;
                arrayIterator3 = 0;
            }
            weightRatios[arrayIterator2][arrayIterator3] = d;
            arrayIterator3++;
        }
        System.out.println("end of pc");
    }

}
