import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class AHP {

    public String headId = "";
    public List<PairwiseComparison> comparisons = new ArrayList<>();
    public List<String> alternatives = new ArrayList<>();


    public AHP() {
        while(true){
            System.out.println("Build new comparison (y/n)?");
            Scanner scn = new Scanner(System.in);
            if(scn.nextLine().equals("n")){
                System.out.println("Finished");
                break;
            }else{
                PairwiseComparison pc = new PairwiseComparison();
                pc.build();
                determineParents(pc);
                if(Main.firstTime){
                    headId = pc.getId();
                    alternatives = pc.getAlternatives();
                    Main.firstTime=false;
                }
                comparisons.add(pc);
            }
        }
    }

    public void determineParents(PairwiseComparison performSearchOn){
        List <String> parents = new ArrayList<>();
        for(PairwiseComparison pc: comparisons){
            if (pc.getAlternatives().contains(performSearchOn.getId())){
                parents.add(pc.getId());
            }
        }
        performSearchOn.setParents(parents);
    }

    public static void saveToJson() {
        //narazie poki weightRatios == null i weightVector == null
        //com.google.gson.Gson nie zapisze ich do pliku
        //
        if (Main.currentFile == null) {
            return;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(Main.currentFile)) {
            gson.toJson(Main.AHP, AHP.class, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromJson() {
        try {
            JsonReader reader = new JsonReader(new FileReader(Main.currentFile));
            Main.AHP = new Gson().fromJson(reader, AHP.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
