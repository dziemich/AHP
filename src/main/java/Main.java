import java.io.File;

public class Main {
    public static File currentFile;
    public static AHP AHP;
    public static boolean firstTime=true;

    public static void main(String[] args) {

        AHP = new AHP();
        currentFile = new File("C:/JAVA/AGHStudia/BadaniaOperacyjne/src/main/java/AHP.json");
        AHP.saveToJson();
    }
}