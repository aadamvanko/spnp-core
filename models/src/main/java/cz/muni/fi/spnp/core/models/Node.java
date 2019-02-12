package cz.muni.fi.spnp.core.models;

public class Node extends BaseModel {

    private String name;
    private int numberOfTokens;

    public Node(int id, String name, int numberOfTokens) {
        super(id);

        this.name = name;
        this.numberOfTokens = numberOfTokens;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public void setNumberOfTokens(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
    }
}
