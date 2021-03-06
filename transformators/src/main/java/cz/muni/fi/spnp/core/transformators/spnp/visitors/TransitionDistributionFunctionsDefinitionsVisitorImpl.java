package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.distributions.TwoValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.FourValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.ThreeValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.SingleValueTransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.*;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public class TransitionDistributionFunctionsDefinitionsVisitorImpl extends Visitor implements TransitionDistributionFunctionsVisitor {
    public <T1> void visit(SingleValueTransitionDistributionBase<T1> singleValueTransitionDistributionBase) {
        checkPreconditions(singleValueTransitionDistributionBase);

        stringBuilder.append(getFunctionDefinition(singleValueTransitionDistributionBase.getFunction()));
    }

    public <T1, T2> void visit(TwoValuesTransitionDistributionBase<T1, T2> twoValuesTransitionDistributionBase) {
        checkPreconditions(twoValuesTransitionDistributionBase);

        String definition = getFunctionDefinition(twoValuesTransitionDistributionBase.getFirstFunction())
                + System.lineSeparator()
                + getFunctionDefinition(twoValuesTransitionDistributionBase.getSecondFunction());
        stringBuilder.append(definition);
    }

    public <T1, T2, T3> void visit(ThreeValuesTransitionDistributionBase<T1, T2, T3> threeValuesTransitionDistributionBase) {
        checkPreconditions(threeValuesTransitionDistributionBase);

        String definition = getFunctionDefinition(threeValuesTransitionDistributionBase.getFirstFunction())
                + System.lineSeparator()
                + getFunctionDefinition(threeValuesTransitionDistributionBase.getSecondFunction())
                + System.lineSeparator()
                + getFunctionDefinition(threeValuesTransitionDistributionBase.getThirdFunction());
        stringBuilder.append(definition);
    }

    public <T1, T2, T3, T4> void visit(FourValuesTransitionDistributionBase<T1, T2, T3, T4> fourValuesTransitionDistributionBase) {
        checkPreconditions(fourValuesTransitionDistributionBase);

        String definition = getFunctionDefinition(fourValuesTransitionDistributionBase.getFirstFunction())
                + System.lineSeparator()
                + getFunctionDefinition(fourValuesTransitionDistributionBase.getSecondFunction())
                + System.lineSeparator()
                + getFunctionDefinition(fourValuesTransitionDistributionBase.getThirdFunction())
                + System.lineSeparator()
                + getFunctionDefinition(fourValuesTransitionDistributionBase.getFourthFunction());
        stringBuilder.append(definition);
    }

    private void checkPreconditions(TransitionDistribution transitionDistribution) {
        if (transitionDistribution.getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Functions definitions are available ONLY on Functional Transition Distribution type.");
    }

    private <T> String getFunctionDefinition(FunctionSPNP<T> function) {
        FunctionDefinitionVisitorImpl functionVisitorImpl = new FunctionDefinitionVisitorImpl();
        function.accept(functionVisitorImpl);
        return functionVisitorImpl.getResult();
    }

    @Override
    public void visit(TransitionDistributionBase transitionDistributionBase) {
        throw new UnsupportedOperationException("Not supported. This situation should not occur.");
    }
}
