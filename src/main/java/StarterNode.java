public class StarterNode extends Node {

    private Node currentNode;
    private int firstEnding = -1;
    private int secondEnding = -1;
    private int endingLoopDistance = -1;

    public StarterNode(String inputData) {
        super(inputData);
        currentNode = this;
    }

    // Travel for one iteration down the network, returning false until the 2nd ending node has been found
    public boolean travelToTwoZs(boolean lOrR, NodeMaster nodeMaster) {
        if (lOrR){
            setCurrentNode(getCurrentNode().getLPath());
        } else {
            setCurrentNode(getCurrentNode().getRPath());
        }
        if (getCurrentNode().isEnder()){
            if (firstEnding == -1){
                firstEnding = nodeMaster.calculatePathLength();
            } else {
                secondEnding = nodeMaster.calculatePathLength();
            }
        }
        return secondEnding != -1;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public int getFirstEnding(){
        return firstEnding;
    }


    public long getXthEnding(long x){
        if (endingLoopDistance == -1){
            endingLoopDistance = secondEnding - firstEnding;
        }
        return firstEnding + x * endingLoopDistance;
    }
}
