package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistribution;
import cz.muni.fi.spnp.core.models.visitors.TransitionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.ExponentialTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.SPNPExponentialTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionVisitorSPNP;

public class SPNPTimedTransition extends TimedTransition {

    private PolicyAffectedType policy;
    private PolicyAffectedType affected;

    public SPNPTimedTransition(int id, String name, TransitionDistribution transitionDistribution) {
        super(id, name, transitionDistribution);

        policy = PolicyAffectedType.PreemptiveRepeatDifferent;
        affected = PolicyAffectedType.PreemptiveResume;
        if (transitionDistribution instanceof ExponentialTransitionDistribution || transitionDistribution instanceof SPNPExponentialTransitionDistribution) {
            affected = PolicyAffectedType.PreemptiveRepeatDifferent;
        }
    }

    public SPNPTimedTransition(int id, String name, int priority, Function guardFunction, TransitionDistribution transitionDistribution,
                               PolicyAffectedType policy, PolicyAffectedType affected) {
        super(id, name, priority, guardFunction, transitionDistribution);

        this.policy = policy;
        this.affected = affected;
    }

    public PolicyAffectedType getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyAffectedType policy) {
        this.policy = policy;
    }

    public PolicyAffectedType getAffected() {
        return affected;
    }

    public void setAffected(PolicyAffectedType affected) {
        this.affected = affected;
    }

    @Override
    public void accept(TransitionVisitor transitionVisitor) {
        if (transitionVisitor instanceof TransitionVisitorSPNP) {
            ((TransitionVisitorSPNP) transitionVisitor).visit(this);
        } else {
            super.accept(transitionVisitor);
        }
    }

}
