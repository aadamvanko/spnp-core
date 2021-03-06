package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.*;


public interface TransitionDistributionVisitorSPNP extends TransitionDistributionVisitor {
    
    void visit(BetaTransitionDistribution betaTransitionDistribution);

    void visit(BinomialTransitionDistribution binomialTransitionDistribution);

    void visit(CauchyTransitionDistribution cauchyTransitionDistribution);

    void visit(ConstantTransitionDistribution constantTransitionDistribution);

    void visit(ErlangTransitionDIstribution erlangTransitionDIstribution);

    void visit(ExponentialTransitionDistribution exponentialTransitionDistribution);

    void visit(GammaTransitionDistribution gammaTransitionDistribution);

    void visit(GeometricTransitionDistribution geometricTransitionDistribution);

    void visit(HyperExponentialTransitionDistribution hyperExponentialTransitionDistribution);

    void visit(HypoExponentialTransitionDistribution hypoExponentialTransitionDistribution);

    void visit(LogarithmicNormalTransitionDistribution logarithmicNormalTransitionDistribution);

    void visit(NegativeBinomialTransitionDistribution negativeBinomialTransitionDistribution);

    void visit(ParetoTransitionDistribution paretoTransitionDistribution);

    void visit(PoissonTransitionDistribution poissonTransitionDistribution);

    void visit(TruncatedNormalTransitionDistribution truncatedNormalTransitionDistribution);

    void visit(UniformTransitionDistribution uniformTransitionDistribution);

    void visit(WeibullTransitionDistribution weibullTransitionDistribution);
}
