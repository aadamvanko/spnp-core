package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.*;


public interface TransitionDistributionVisitorSPNP extends TransitionDistributionVisitor {

    void visit(BetaTransitionDistribution betaTransitionDistribution);

    void visit(SPNPBetaTransitionDistribution spnpBetaTransitionDistribution);

    void visit(BinomialTransitionDistribution binomialTransitionDistribution);

    void visit(SPNPBinomialTransitionDistribution spnpBinomialTransitionDistribution);

    void visit(CauchyTransitionDistribution cauchyTransitionDistribution);

    void visit(SPNPCauchyTransitionDistribution spnpCauchyTransitionDistribution);

    void visit(ConstantTransitionDistribution constantTransitionDistribution);

    void visit(SPNPConstantTransitionDistribution spnpConstantTransitionDistribution);

    void visit(ErlangTransitionDIstribution erlangTransitionDIstribution);

    void visit(SPNPErlangTransitionDIstribution spnpErlangTransitionDIstribution);

    void visit(ExponentialTransitionDistribution exponentialTransitionDistribution);

    void visit(SPNPExponentialTransitionDistribution spnpExponentialTransitionDistribution);

    void visit(GammaTransitionDistribution gammaTransitionDistribution);

    void visit(SPNPGammaTransitionDistribution spnpGammaTransitionDistribution);

    void visit(GeometricTransitionDistribution geometricTransitionDistribution);

    void visit(SPNPGeometricTransitionDistribution spnpGeometricTransitionDistribution);

    void visit(HyperExponentialTransitionDistribution hyperExponentialTransitionDistribution);

    void visit(SPNPHyperExponentialTransitionDistribution spnpHyperExponentialTransitionDistribution);

    void visit(HypoExponentialTransitionDistribution hypoExponentialTransitionDistribution);

    void visit(SPNPHypoExponentialTransitionDistribution spnpHypoExponentialTransitionDistribution);

    void visit(LogarithmicNormalTransitionDistribution logarithmicNormalTransitionDistribution);

    void visit(SPNPLogarithmicNormalTransitionDistribution spnpLogarithmicNormalTransitionDistribution);

    void visit(NegativeBinomialTransitionDistribution negativeBinomialTransitionDistribution);

    void visit(SPNPNegativeBinomialTransitionDistribution spnpNegativeBinomialTransitionDistribution);

    void visit(ParetoTransitionDistribution paretoTransitionDistribution);

    void visit(SPNPParetoTransitionDistribution spnpParetoTransitionDistribution);

    void visit(PoissonTransitionDistribution poissonTransitionDistribution);

    void visit(SPNPPoissonTransitionDistribution spnpPoissonTransitionDistribution);

    void visit(TruncatedNormalTransitionDistribution truncatedNormalTransitionDistribution);

    void visit(SPNPTruncatedNormalTransitionDistribution spnpTruncatedNormalTransitionDistribution);

    void visit(UniformTransitionDistribution uniformTransitionDistribution);

    void visit(SPNPUniformTransitionDistribution spnpUniformTransitionDistribution);

    void visit(WeibullTransitionDistribution weibullTransitionDistribution);

    void visit(SPNPWeibullTransitionDistribution spnpWeibullTransitionDistribution);
}
