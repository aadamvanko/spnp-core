package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.*;

public class TransitionDistributionVisitorImpl extends Visitor implements TransitionDistributionVisitorSPNP {

    private final Transition transition;

    public TransitionDistributionVisitorImpl(Transition transition) {
        this.transition = transition;
    }

    @Override
    public void visit(BetaTransitionDistribution betaTransitionDistribution) {
        if (betaTransitionDistribution == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (betaTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("betval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(betaTransitionDistribution.getFirstValue()),
                        formatDouble(betaTransitionDistribution.getSecondValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("betfun(\"%s\", %s, %s);",
                        transition.getName(),
                        betaTransitionDistribution.getFirstFunction().getName(),
                        betaTransitionDistribution.getSecondFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("betdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(betaTransitionDistribution.getFirstValue()),
                        formatDouble(betaTransitionDistribution.getSecondValue()),
                        betaTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Beta transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(BinomialTransitionDistribution binomialTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (binomialTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("binoval(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        formatDouble(binomialTransitionDistribution.getNumberValue()),
                        formatDouble(binomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(binomialTransitionDistribution.getTValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("binofun(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        binomialTransitionDistribution.getNumberValueFunction().getName(),
                        binomialTransitionDistribution.getProbabilityValueFunction().getName(),
                        binomialTransitionDistribution.getTValueFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("binodep(\"%s\", %s, %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(binomialTransitionDistribution.getNumberValue()),
                        formatDouble(binomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(binomialTransitionDistribution.getTValue()),
                        binomialTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Binomial transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(CauchyTransitionDistribution cauchyTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (cauchyTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("cauval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(cauchyTransitionDistribution.getAlphaValue()),
                        formatDouble(cauchyTransitionDistribution.getBetaValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("caufun(\"%s\", %s, %s);",
                        transition.getName(),
                        cauchyTransitionDistribution.getAlphaValueFunction().getName(),
                        cauchyTransitionDistribution.getBetaValueFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("caudep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(cauchyTransitionDistribution.getAlphaValue()),
                        formatDouble(cauchyTransitionDistribution.getBetaValue()),
                        cauchyTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Cauchy transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(ConstantTransitionDistribution constantTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (constantTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("detval(\"%s\", %s);",
                        transition.getName(),
                        formatDouble(constantTransitionDistribution.getValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("detfun(\"%s\", %s);",
                        transition.getName(),
                        constantTransitionDistribution.getFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("detdep(\"%s\", %s, \"%s\");",
                        transition.getName(),
                        formatDouble(constantTransitionDistribution.getValue()),
                        constantTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Constant transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(ErlangTransitionDIstribution erlangTransitionDIstribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (erlangTransitionDIstribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("erlval(\"%s\", %s, %d);",
                        transition.getName(),
                        formatDouble(erlangTransitionDIstribution.getRate()),
                        erlangTransitionDIstribution.getNumberOfPhases())
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("erlfun(\"%s\", %s, %s);",
                        transition.getName(),
                        erlangTransitionDIstribution.getRateFunction().getName(),
                        erlangTransitionDIstribution.getNumberOfPhasesFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("erldep(\"%s\", %s, %d, \"%s\");",
                        transition.getName(),
                        formatDouble(erlangTransitionDIstribution.getRate()),
                        erlangTransitionDIstribution.getNumberOfPhases(),
                        erlangTransitionDIstribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Erlang transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(ExponentialTransitionDistribution exponentialTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (exponentialTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("rateval(\"%s\", %s);",
                        transition.getName(),
                        formatDouble(exponentialTransitionDistribution.getRate()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("ratefun(\"%s\", %s);",
                        transition.getName(),
                        exponentialTransitionDistribution.getRateFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("ratedep(\"%s\", %s, \"%s\");",
                        transition.getName(),
                        formatDouble(exponentialTransitionDistribution.getRate()),
                        exponentialTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Exponential transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(GammaTransitionDistribution gammaTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (gammaTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("gamval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(gammaTransitionDistribution.getFirstValue()),
                        formatDouble(gammaTransitionDistribution.getSecondValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("gamfun(\"%s\", %s, %s);",
                        transition.getName(),
                        gammaTransitionDistribution.getFirstFunction().getName(),
                        gammaTransitionDistribution.getSecondFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("gamdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(gammaTransitionDistribution.getFirstValue()),
                        formatDouble(gammaTransitionDistribution.getSecondValue()),
                        gammaTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Gamma transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(GeometricTransitionDistribution geometricTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (geometricTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("geomval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(geometricTransitionDistribution.getFirstValue()),
                        formatDouble(geometricTransitionDistribution.getSecondValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("geomfun(\"%s\", %s, %s);",
                        transition.getName(),
                        geometricTransitionDistribution.getFirstFunction().getName(),
                        geometricTransitionDistribution.getSecondFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("geomdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(geometricTransitionDistribution.getFirstValue()),
                        formatDouble(geometricTransitionDistribution.getSecondValue()),
                        geometricTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Geometric transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(HyperExponentialTransitionDistribution hyperExponentialTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (hyperExponentialTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("hyperval(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        formatDouble(hyperExponentialTransitionDistribution.getFirstLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getSecondLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getProbabilityValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("hyperfun(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        hyperExponentialTransitionDistribution.getFirstLambdaRateFunction().getName(),
                        hyperExponentialTransitionDistribution.getSecondLambdaRateFunction().getName(),
                        hyperExponentialTransitionDistribution.getProbabilityValueFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("hyperdep(\"%s\", %s, %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(hyperExponentialTransitionDistribution.getFirstLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getSecondLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getProbabilityValue()),
                        hyperExponentialTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Hyper-exponential transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(HypoExponentialTransitionDistribution hypoExponentialTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (hypoExponentialTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("hypoval(\"%s\", %s, %s, %s, %s);",
                        transition.getName(),
                        formatIntAsDouble(hypoExponentialTransitionDistribution.getNumberOfStages()),
                        formatDouble(hypoExponentialTransitionDistribution.getFirstRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getSecondRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getThirdRateValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("hypofun(\"%s\", %s, %s, %s, %s);",
                        transition.getName(),
                        hypoExponentialTransitionDistribution.getNumberOfStagesFunction().getName(),
                        hypoExponentialTransitionDistribution.getFirstRateValueFunction().getName(),
                        hypoExponentialTransitionDistribution.getSecondRateValueFunction().getName(),
                        hypoExponentialTransitionDistribution.getThirdRateValueFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("hypodep(\"%s\", %s, %s, %s, %s, \"%s\");",
                        transition.getName(),
                        formatIntAsDouble(hypoExponentialTransitionDistribution.getNumberOfStages()),
                        formatDouble(hypoExponentialTransitionDistribution.getFirstRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getSecondRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getThirdRateValue()),
                        hypoExponentialTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Hypo-exponential transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(LogarithmicNormalTransitionDistribution logarithmicNormalTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (logarithmicNormalTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("lognval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(logarithmicNormalTransitionDistribution.getFirstValue()),
                        formatDouble(logarithmicNormalTransitionDistribution.getSecondValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("lognfun(\"%s\", %s, %s);",
                        transition.getName(),
                        logarithmicNormalTransitionDistribution.getFirstFunction().getName(),
                        logarithmicNormalTransitionDistribution.getSecondFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("logndep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(logarithmicNormalTransitionDistribution.getFirstValue()),
                        formatDouble(logarithmicNormalTransitionDistribution.getSecondValue()),
                        logarithmicNormalTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Logarithmic normal transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(NegativeBinomialTransitionDistribution negativeBinomialTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (negativeBinomialTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("negbval(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        formatDouble(negativeBinomialTransitionDistribution.getNumberValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getTValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("negbfun(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        negativeBinomialTransitionDistribution.getNumberValueFunction().getName(),
                        negativeBinomialTransitionDistribution.getProbabilityValueFunction().getName(),
                        negativeBinomialTransitionDistribution.getTValueFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("negbdep(\"%s\", %s, %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(negativeBinomialTransitionDistribution.getNumberValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getTValue()),
                        negativeBinomialTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Negative binomial transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(ParetoTransitionDistribution paretoTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (paretoTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("parval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(paretoTransitionDistribution.getScale()),
                        formatDouble(paretoTransitionDistribution.getAlpha()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("parfun(\"%s\", %s, %s);",
                        transition.getName(),
                        paretoTransitionDistribution.getScaleFunction().getName(),
                        paretoTransitionDistribution.getAlphaFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("pardep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(paretoTransitionDistribution.getScale()),
                        formatDouble(paretoTransitionDistribution.getAlpha()),
                        paretoTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Pareto transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(PoissonTransitionDistribution poissonTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (poissonTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("poisval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(poissonTransitionDistribution.getLambdaValue()),
                        formatDouble(poissonTransitionDistribution.getTValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("poisfun(\"%s\", %s, %s);",
                        transition.getName(),
                        poissonTransitionDistribution.getLambdaValueFunction().getName(),
                        poissonTransitionDistribution.getTValueFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("poisdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(poissonTransitionDistribution.getLambdaValue()),
                        formatDouble(poissonTransitionDistribution.getTValue()),
                        poissonTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Poisson transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(TruncatedNormalTransitionDistribution truncatedNormalTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (truncatedNormalTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("normval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(truncatedNormalTransitionDistribution.getExpectation()),
                        formatDouble(truncatedNormalTransitionDistribution.getVariance()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("normfun(\"%s\", %s, %s);",
                        transition.getName(),
                        truncatedNormalTransitionDistribution.getExpectationFunction().getName(),
                        truncatedNormalTransitionDistribution.getVarianceFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("normdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(truncatedNormalTransitionDistribution.getExpectation()),
                        formatDouble(truncatedNormalTransitionDistribution.getVariance()),
                        truncatedNormalTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Truncate normal transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(UniformTransitionDistribution uniformTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (uniformTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("unifval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(uniformTransitionDistribution.getLowerBound()),
                        formatDouble(uniformTransitionDistribution.getUpperBound()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("uniffun(\"%s\", %s, %s);",
                        transition.getName(),
                        uniformTransitionDistribution.getLowerBoundFunction().getName(),
                        uniformTransitionDistribution.getUpperBoundFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("unifdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(uniformTransitionDistribution.getLowerBound()),
                        formatDouble(uniformTransitionDistribution.getUpperBound()),
                        uniformTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Uniform transition distribution has unknown type.");
        }
    }

    @Override
    public void visit(WeibullTransitionDistribution weibullTransitionDistribution) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (weibullTransitionDistribution.getDistributionType()) {
            case Constant:
                stringBuilder.append(String.format("weibval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(weibullTransitionDistribution.getAlphaValue()),
                        formatDouble(weibullTransitionDistribution.getLambdaValue()))
                        + System.lineSeparator());
                break;

            case Functional:
                stringBuilder.append(String.format("weibfun(\"%s\", %s, %s);",
                        transition.getName(),
                        weibullTransitionDistribution.getAlphaValueFunction().getName(),
                        weibullTransitionDistribution.getLambdaValueFunction().getName())
                        + System.lineSeparator());
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("weibdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(weibullTransitionDistribution.getAlphaValue()),
                        formatDouble(weibullTransitionDistribution.getLambdaValue()),
                        weibullTransitionDistribution.getDependentPlace().getName())
                        + System.lineSeparator());
                break;

            default:
                throw new IllegalStateException("Weibull transition distribution has unknown type.");
        }
    }
    
    @Override
    public void visit(TransitionDistributionBase transitionDistribution){
        if(transitionDistribution instanceof ExponentialTransitionDistribution)
            throw new UnsupportedOperationException("Not supported.");
        throw new UnsupportedOperationException("Not supported. This situation should not occur.");
    }
}
