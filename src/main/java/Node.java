public class Node {
    private String name;
    private String lName;
    private String rName;
    private Node lPath;
    private Node rPath;

    private boolean isEnder = false;

    public Node(String inputData) {
        String[] nameAndNavigation = inputData.split("=");
        this.name = nameAndNavigation[0].trim();
        parseNavigation(nameAndNavigation[1]);
        checkSpecial(name);
    }

    private String[] parseNavigation(String navigationInput) {
        String[] navigation = navigationInput.trim().split(",");
        this.lName = navigation[0].substring(1);
        this.rName = navigation[1].substring(1, navigation[1].length()-1);
        return navigation;
    }

    private void checkSpecial(String name) {
        if (name.charAt(2) == 'Z'){
            this.isEnder = true;
        }
    }

    public void linkPaths(Node lNode, Node rNode){
        this.lPath = lNode;
        this.rPath = rNode;
    }

    public String getName() {
        return name;
    }

    public String getLName() {
        return lName;
    }

    public String getRName() {
        return rName;
    }

    public Node getLPath() {
        return lPath;
    }

    public Node getRPath() {
        return rPath;
    }

    public boolean isEnder() {
        return isEnder;
    }

}
