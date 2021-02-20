package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.transitions.distributions.FourValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.SingleValueTransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.ThreeValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.TwoValuesTransitionDistributionBase;

public interface TransitionDistributionFunctionsVisitor {
    <T1> void visit(SingleValueTransitionDistributionBase<T1> singleValueTransitionDistributionBase);

    <T1, T2> void visit(TwoValuesTransitionDistributionBase<T1, T2> twoValuesTransitionDistributionBase);

    <T1, T2, T3> void visit(ThreeValuesTransitionDistributionBase<T1, T2, T3> threeValuesTransitionDistributionBase);

    <T1, T2, T3, T4> void visit(FourValuesTransitionDistributionBase<T1, T2, T3, T4> fourValuesTransitionDistributionBase);
}
