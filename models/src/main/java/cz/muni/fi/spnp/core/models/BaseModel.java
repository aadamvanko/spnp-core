package cz.muni.fi.spnp.core.models;

public abstract class BaseModel {

    private int id;

    public int getId() {
        return id;
    }

    public BaseModel(int id) {
        this.id = id;
    }
}
