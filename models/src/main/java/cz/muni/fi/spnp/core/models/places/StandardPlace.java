package cz.muni.fi.spnp.core.models.places;

public class StandardPlace extends Place {

    private int numberOfTokens;

    public StandardPlace(int id, String name) {
        this(id, name, 0);
    }

    public StandardPlace(int id, String name, int numberOfTokens) {
        super(id, name);

        this.numberOfTokens = numberOfTokens;
    }

    /**
     * Gets the {@link String} representation of the place and its parameters.
     *
     * @return representation of the place and its parameters
     */
    @Override
    public String getDefinition() {
        StringBuilder definition = new StringBuilder();

        definition.append(String.format("place(\"%s\");", this.getName()));
        definition.append(System.lineSeparator());

        if (this.getNumberOfTokens() > 0) {
            definition.append(String.format("init(\"%s\", %d);", this.getName(), this.getNumberOfTokens()));
            definition.append(System.lineSeparator());
        }

        return definition.toString();
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public void setNumberOfTokens(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
    }
}
