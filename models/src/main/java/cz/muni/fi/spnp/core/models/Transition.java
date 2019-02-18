package cz.muni.fi.spnp.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transition {

    private int id;
    private boolean enabled;

    private List<Arc> inputArcs;
    private List<Arc> outputArcs;

    public Transition(int id) {
        this.id = id;

        this.inputArcs = new ArrayList<>();
        this.outputArcs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void addInputArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");
        if (arc.getDirection() != ArcDirection.In)
            throw new IllegalStateException("Arc is not input type.");

        inputArcs.add(arc);
    }

    public void addOutputArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");
        if (arc.getDirection() != ArcDirection.Out)
            throw new IllegalStateException("Arc is not output type.");

        outputArcs.add(arc);
    }

    public List<Arc> getInputArcs() {
        return Collections.unmodifiableList(inputArcs);
    }

    public List<Arc> getOutputArcs() {
        return Collections.unmodifiableList(outputArcs);
    }
}
