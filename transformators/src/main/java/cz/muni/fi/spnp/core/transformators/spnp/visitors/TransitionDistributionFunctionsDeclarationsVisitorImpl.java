package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.distributions.TwoValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.FourValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.ThreeValuesTransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.SingleValueTransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.*;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public class TransitionDistributionFunctionsDeclarationsVisitorImpl extends Visitor implements TransitionDistributionFunctionsVisitor {
    public <T1> void visit(SingleValueTransitionDistributionBase<T1> singleValueTransitionDistributionBase) {
        checkPreconditions(singleValueTransitionDistributionBase);

        String definition = getFunctionDeclaration(singleValueTransitionDistributionBase.getFunction());
        stringBuilder.append(definition);
    }

    public <T1, T2> void visit(TwoValuesTransitionDistributionBase<T1, T2> twoValuesTransitionDistributionBase) {
        checkPreconditions(twoValuesTransitionDistributionBase);

        String definition = getFunctionDeclaration(twoValuesTransitionDistributionBase.getFirstFunction())
                + getFunctionDeclaration(twoValuesTransitionDistributionBase.getSecondFunction());
        stringBuilder.append(definition);
    }

    public <T1, T2, T3> void visit(ThreeValuesTransitionDistributionBase<T1, T2, T3> threeValuesTransitionDistributionBase) {
        checkPreconditions(threeValuesTransitionDistributionBase);

        String definition = getFunctionDeclaration(threeValuesTransitionDistributionBase.getFirstFunction())
                + getFunctionDeclaration(threeValuesTransitionDistributionBase.getSecondFunction())
                + getFunctionDeclaration(threeValuesTransitionDistributionBase.getThirdFunction());
        stringBuilder.append(definition);
    }

    public <T1, T2, T3, T4> void visit(FourValuesTransitionDistributionBase<T1, T2, T3, T4> fourValuesTransitionDistributionBase) {
        checkPreconditions(fourValuesTransitionDistributionBase);

        String definition = getFunctionDeclaration(fourValuesTransitionDistributionBase.getFirstFunction())
                + getFunctionDeclaration(fourValuesTransitionDistributionBase.getSecondFunction())
                + getFunctionDeclaration(fourValuesTransitionDistributionBase.getThirdFunction())
                + getFunctionDeclaration(fourValuesTransitionDistributionBase.getFourthFunction());
        stringBuilder.append(definition);
    }

    private void checkPreconditions(TransitionDistribution transitionDistribution) {
        if (transitionDistribution.getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Functions declarations are available ONLY on Functional Transition Distribution type.");
    }

    private <T> String getFunctionDeclaration(FunctionSPNP<T> function) {
        FunctionDeclarationVisitorImpl functionDeclarationVisitor = new FunctionDeclarationVisitorImpl();
        function.accept(functionDeclarationVisitor);
        return functionDeclarationVisitor.getResult();
    }

    @Override
    public void visit(TransitionDistributionBase transitionDistributionBase) {
        throw new UnsupportedOperationException("Not supported. This situation should not occur.");
    }
}
