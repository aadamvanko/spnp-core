package cz.muni.fi.spnp.core.models.places;

import com.google.common.base.Objects;
import cz.muni.fi.spnp.core.models.arcs.Arc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Place {

    private int id;
    private String name;

    protected Set<Arc> arcs;

    protected Place(int id, String name) {
        if (name == null)
            throw new IllegalArgumentException("Name must be set.");

        this.id = id;
        this.name = name;

        this.arcs = new HashSet<>();
    }

    /**
     * Gets the {@link String} representation of the place and its parameters.
     *
     * @return  representation of the place and its parameters
     */
    public abstract String getDefinition();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        this.arcs.add(arc);
    }

    public void removeArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        this.arcs.remove(arc);
    }

    public Set<Arc> getArcs() {
        return Collections.unmodifiableSet(this.arcs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Place place = (Place) o;

        return id == place.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
