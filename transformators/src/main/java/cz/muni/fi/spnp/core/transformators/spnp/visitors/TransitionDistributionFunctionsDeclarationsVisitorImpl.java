package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.distributions.*;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsDeclarationsVisitor;

public class TransitionDistributionFunctionsDeclarationsVisitorImpl extends Visitor implements TransitionDistributionFunctionsDeclarationsVisitor {
    @Override
    public <T1> void visit(SingleValueTransitionDistributionBase<T1> singleValueTransitionDistributionBase) {
        checkPreconditions(singleValueTransitionDistributionBase);

        String definition = getFunctionDeclaration(singleValueTransitionDistributionBase.getFunction());
        stringBuilder.append(definition);
    }

    @Override
    public <T1, T2> void visit(TwoValuesTransitionDistributionBase<T1, T2> twoValuesTransitionDistributionBase) {
        checkPreconditions(twoValuesTransitionDistributionBase);

        String definition = getFunctionDeclaration(twoValuesTransitionDistributionBase.getFirstFunction())
                + System.lineSeparator()
                + getFunctionDeclaration(twoValuesTransitionDistributionBase.getSecondFunction());
        stringBuilder.append(definition);
    }

    @Override
    public <T1, T2, T3> void visit(ThreeValuesTransitionDistributionBase<T1, T2, T3> threeValuesTransitionDistributionBase) {
        checkPreconditions(threeValuesTransitionDistributionBase);

        String definition = getFunctionDeclaration(threeValuesTransitionDistributionBase.getFirstFunction())
                + System.lineSeparator()
                + getFunctionDeclaration(threeValuesTransitionDistributionBase.getSecondFunction())
                + System.lineSeparator()
                + getFunctionDeclaration(threeValuesTransitionDistributionBase.getThirdFunction());
        stringBuilder.append(definition);
    }

    @Override
    public <T1, T2, T3, T4> void visit(FourValuesTransitionDistributionBase<T1, T2, T3, T4> fourValuesTransitionDistributionBase) {
        checkPreconditions(fourValuesTransitionDistributionBase);

        String definition = getFunctionDeclaration(fourValuesTransitionDistributionBase.getFirstFunction())
                + System.lineSeparator()
                + getFunctionDeclaration(fourValuesTransitionDistributionBase.getSecondFunction())
                + System.lineSeparator()
                + getFunctionDeclaration(fourValuesTransitionDistributionBase.getThirdFunction())
                + System.lineSeparator()
                + getFunctionDeclaration(fourValuesTransitionDistributionBase.getFourthFunction());
        stringBuilder.append(definition);
    }

    private void checkPreconditions(TransitionDistribution transitionDistribution) {
        if (transitionDistribution.getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Functions declarations are available ONLY on Functional Transition Distribution type.");
    }

    private <T> String getFunctionDeclaration(Function<T> function) {
        FunctionDeclarationVisitorImpl functionDeclarationVisitor = new FunctionDeclarationVisitorImpl();
        function.accept(functionDeclarationVisitor);
        return functionDeclarationVisitor.getResult();
    }
}