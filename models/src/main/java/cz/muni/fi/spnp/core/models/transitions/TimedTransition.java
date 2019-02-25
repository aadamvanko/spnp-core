package cz.muni.fi.spnp.core.models.transitions;

public class TimedTransition extends Transition {

    public TimedTransition(int id,
                           String name) {
        super(id, name, 0.0, null);
    }
}
