import java.util.*;

public class NodeMaster {

    boolean initialLine = true;

    // L => true; R => false
    boolean[] path;
    int pathLength;

    // network and nodesList is used to store data which is later used to link up the nodes
    HashMap<String, Node> network = new HashMap<>();
    ArrayList<Node> nodesList = new ArrayList<>();

    ArrayList<StarterNode> starterNodes = new ArrayList<>();

    String networkLocation;
    int numSteps;
    int numPaths;

    public void newLineRead(String inputData) {
        if (initialLine){
            pathLength = inputData.length();
            path = new boolean[pathLength];
            for (int i = 0; i < pathLength; i++) {
                path[i] = inputData.charAt(i) == 'L';
            }
            initialLine = false;
        } else {
            populateNetwork(inputData);
        }
    }

    private void populateNetwork(String inputData) {
        if (inputData.charAt(2) == 'A'){
            StarterNode newNode = new StarterNode(inputData);
            network.put(newNode.getName(), newNode);
            nodesList.add(newNode);
            starterNodes.add(newNode);
        } else {
            Node newNode = new Node(inputData);
            network.put(newNode.getName(), newNode);
            nodesList.add(newNode);
        }
    }

    public void linkNodes() {
        for (Node node: nodesList) {
            String lNode = node.getLName();
            String rNode = node.getRName();
            node.linkPaths(network.get(lNode), network.get(rNode));
        }
        network = null;
        nodesList = null;
    }

    public long sendGhostsIndividually(){
        // For each ghost, find the position of its first and second Z ending. We can assume that it will continue looping
        // the path between its first and second forever, so once we have calculated this for each ghost we can find a
        // common digit which coincides every single loop's ending position. Note that this assumes that each ghost's
        // path only contains one ending node.
        for (StarterNode ghost: starterNodes) {
            numSteps = 0;
            numPaths = 0;
            while (!ghost.travelToTwoZs(path[numSteps], this)){
                numSteps++;
                // if the number of steps has increased past the end of the path, set it back to zero and increment the number of whole paths taken
                if (numSteps == pathLength){
                    numSteps = 0;
                    numPaths ++;
                }
            }
        }

        return calculateAllGhostEnding();
    }

    private long calculateAllGhostEnding() {
        boolean areAllAtEndings = false;
        long largestEnding;
        // the number of steps taken to reach an ending
        long[] endingNumSteps = initialiseEnding();
        // the xth time this ghost has reached an ending
        long[] endingX = new long[starterNodes.size()];
        while (!areAllAtEndings){

            areAllAtEndings = true;

            largestEnding = Arrays.stream(endingNumSteps).max().getAsLong();

            // for each ghost
            for (int i = 0; i < endingNumSteps.length; i++) {
                // If its Xth ending is smaller than the largest ending
                if (endingNumSteps[i] < largestEnding){
                    // Get the next ending of that ghost and set to false to show that not all endings are equal
                    endingNumSteps[i] = starterNodes.get(i).getXthEnding(++endingX[i]);
                    areAllAtEndings = false;
                }
            }
        }

        for (int i = 0; i < starterNodes.size(); i++) {
            System.out.println(endingNumSteps[i]);
            System.out.println(endingX[i]);
        }
        return endingNumSteps[0];
    }

    private long[] initialiseEnding() {
        long[] endingNumSteps = new long[starterNodes.size()];
        for (int i = 0; i < endingNumSteps.length; i++){
            endingNumSteps[i] = starterNodes.get(i).getXthEnding(0);
        }
        return endingNumSteps;
    }

    public int calculatePathLength() {
        // +1 needs to be added as the final step increment is cut off by the logic
        return pathLength * numPaths + numSteps + 1;
    }
}
