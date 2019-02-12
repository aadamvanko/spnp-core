package cz.muni.fi.spnp.core.models;

public class Transition extends BaseModel {

    private TransitionType type;
    private boolean enabled;

    public Transition(int id) {
        super(id);
    }

    public TransitionType getType() {
        return type;
    }

    public void setType(TransitionType type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
