import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        String filename = "src/main/resources/input.txt";
        System.out.println(runDay8Code(filename));

    }

    public static long runDay8Code(String filename){

        NodeMaster nodeMaster = new NodeMaster();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String wholeData = myReader.nextLine();
                if (!Objects.equals(wholeData, "")){
                    nodeMaster.newLineRead(wholeData);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        nodeMaster.linkNodes();

        return nodeMaster.sendGhostsIndividually();
    }
}
