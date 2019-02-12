package cz.muni.fi.spnp.core.models;

public class Arc extends BaseModel {

    private int weight;
    private ArcType type;
    private ArcDirection direction;

    public Arc(int id, ArcType type, ArcDirection direction) {
        this(id, type, direction, 0);
    }

    public Arc(int id, ArcType type, ArcDirection direction, int weight) {
        super(id);

        this.weight = weight;
        this.direction = direction;
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArcType getType() {
        return type;
    }

    public ArcDirection getDirection() {
        return direction;
    }
}
