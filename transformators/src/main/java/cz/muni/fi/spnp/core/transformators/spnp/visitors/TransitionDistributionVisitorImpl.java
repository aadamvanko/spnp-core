package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.transitions.distributions.*;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;

public class TransitionDistributionVisitorImpl extends Visitor implements TransitionDistributionVisitor {

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
                stringBuilder.append(String.format("void betval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(betaTransitionDistribution.getFirstValue()),
                        formatDouble(betaTransitionDistribution.getSecondValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void betfun(\"%s\", %s, %s);",
                        transition.getName(),
                        betaTransitionDistribution.getFirstFunction().getName(),
                        betaTransitionDistribution.getSecondFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void betdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(betaTransitionDistribution.getFirstValue()),
                        formatDouble(betaTransitionDistribution.getSecondValue()),
                        betaTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void binoval(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        formatDouble(binomialTransitionDistribution.getNumberValue()),
                        formatDouble(binomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(binomialTransitionDistribution.getTValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void binofun(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        binomialTransitionDistribution.getNumberValueFunction(),
                        binomialTransitionDistribution.getProbabilityValueFunction(),
                        binomialTransitionDistribution.getTValueFunction()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void binodep(\"%s\", %s, %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(binomialTransitionDistribution.getNumberValue()),
                        formatDouble(binomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(binomialTransitionDistribution.getTValue()),
                        binomialTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void cauval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(cauchyTransitionDistribution.getAlphaValue()),
                        formatDouble(cauchyTransitionDistribution.getBetaValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void caufun(\"%s\", %s, %s);",
                        transition.getName(),
                        cauchyTransitionDistribution.getAlphaValueFunction(),
                        cauchyTransitionDistribution.getBetaValueFunction()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void cauval(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(cauchyTransitionDistribution.getAlphaValue()),
                        formatDouble(cauchyTransitionDistribution.getBetaValue()),
                        cauchyTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void detval(\"%s\", %s);",
                        transition.getName(),
                        formatDouble(constantTransitionDistribution.getValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void detfun(\"%s\", %s);",
                        transition.getName(),
                        constantTransitionDistribution.getFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void detdep(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(constantTransitionDistribution.getValue()),
                        constantTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void erlval(\"%s\", %s, %d);",
                        transition.getName(),
                        formatDouble(erlangTransitionDIstribution.getRate()),
                        erlangTransitionDIstribution.getNumberOfPhases()));
                break;

            case Functional:
                stringBuilder.append(String.format("void erlfun(\"%s\", %s, %s);",
                        transition.getName(),
                        erlangTransitionDIstribution.getRateFunction().getName(),
                        erlangTransitionDIstribution.getNumberOfPhasesFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void erldep(\"%s\", %s, %d, %s);",
                        transition.getName(),
                        formatDouble(erlangTransitionDIstribution.getRate()),
                        erlangTransitionDIstribution.getNumberOfPhases(),
                        erlangTransitionDIstribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void rateval(\"%s\", %s);",
                        transition.getName(),
                        formatDouble(exponentialTransitionDistribution.getRate())));
                break;

            case Functional:
                stringBuilder.append(String.format("void ratefun(\"%s\", %s);",
                        transition.getName(),
                        exponentialTransitionDistribution.getRateFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void ratedep(\"%s\", %s, \"%s\");",
                        transition.getName(),
                        formatDouble(exponentialTransitionDistribution.getRate()),
                        exponentialTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void gamval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(gammaTransitionDistribution.getFirstValue()),
                        formatDouble(gammaTransitionDistribution.getSecondValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void gamfun(\"%s\", %s, %s);",
                        transition.getName(),
                        gammaTransitionDistribution.getFirstFunction().getName(),
                        gammaTransitionDistribution.getSecondFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void gamdep(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        formatDouble(gammaTransitionDistribution.getFirstValue()),
                        formatDouble(gammaTransitionDistribution.getSecondValue()),
                        gammaTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void geomval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(geometricTransitionDistribution.getFirstValue()),
                        formatDouble(geometricTransitionDistribution.getSecondValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void geomfun(\"%s\", %s, %s);",
                        transition.getName(),
                        geometricTransitionDistribution.getFirstFunction().getName(),
                        geometricTransitionDistribution.getSecondFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void geomdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(geometricTransitionDistribution.getFirstValue()),
                        formatDouble(geometricTransitionDistribution.getSecondValue()),
                        geometricTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void hyperval(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        formatDouble(hyperExponentialTransitionDistribution.getFirstLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getSecondLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getProbabilityValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void hyperfun(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        hyperExponentialTransitionDistribution.getFirstLambdaRateFunction().getName(),
                        hyperExponentialTransitionDistribution.getSecondLambdaRateFunction().getName(),
                        hyperExponentialTransitionDistribution.getProbabilityValueFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void hyperdep(\"%s\", %s, %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(hyperExponentialTransitionDistribution.getFirstLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getSecondLambdaRate()),
                        formatDouble(hyperExponentialTransitionDistribution.getProbabilityValue()),
                        hyperExponentialTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void hypoval(\"%s\", %d, %s, %s, %s);",
                        transition.getName(),
                        hypoExponentialTransitionDistribution.getNumberOfStages(),
                        formatDouble(hypoExponentialTransitionDistribution.getFirstRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getSecondRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getThirdRateValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void hypofun(\"%s\", %s, %s, %s, %s);",
                        transition.getName(),
                        hypoExponentialTransitionDistribution.getNumberOfStagesFunction().getName(),
                        hypoExponentialTransitionDistribution.getFirstRateValueFunction().getName(),
                        hypoExponentialTransitionDistribution.getSecondRateValueFunction().getName(),
                        hypoExponentialTransitionDistribution.getThirdRateValueFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void hypodep(\"%s\", %d, %s, %s, %s, \"%s\");",
                        transition.getName(),
                        hypoExponentialTransitionDistribution.getNumberOfStages(),
                        formatDouble(hypoExponentialTransitionDistribution.getFirstRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getSecondRateValue()),
                        formatDouble(hypoExponentialTransitionDistribution.getThirdRateValue()),
                        hypoExponentialTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void lognval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(logarithmicNormalTransitionDistribution.getFirstValue()),
                        formatDouble(logarithmicNormalTransitionDistribution.getSecondValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void lognfun(\"%s\", %s, %s);",
                        transition.getName(),
                        logarithmicNormalTransitionDistribution.getFirstFunction().getName(),
                        logarithmicNormalTransitionDistribution.getSecondFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void logndep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(logarithmicNormalTransitionDistribution.getFirstValue()),
                        formatDouble(logarithmicNormalTransitionDistribution.getSecondValue()),
                        logarithmicNormalTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void negbval(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        formatDouble(negativeBinomialTransitionDistribution.getNumberValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getTValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void negbfun(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        negativeBinomialTransitionDistribution.getNumberValueFunction().getName(),
                        negativeBinomialTransitionDistribution.getProbabilityValueFunction().getName(),
                        negativeBinomialTransitionDistribution.getTValueFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void negbdep(\"%s\", %s, %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(negativeBinomialTransitionDistribution.getNumberValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getProbabilityValue()),
                        formatDouble(negativeBinomialTransitionDistribution.getTValue()),
                        negativeBinomialTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void parval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(paretoTransitionDistribution.getScale()),
                        formatDouble(paretoTransitionDistribution.getAlpha())));
                break;

            case Functional:
                stringBuilder.append(String.format("void parfun(\"%s\", %s, %s);",
                        transition.getName(),
                        paretoTransitionDistribution.getScaleFunction().getName(),
                        paretoTransitionDistribution.getAlphaFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void pardep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(paretoTransitionDistribution.getScale()),
                        formatDouble(paretoTransitionDistribution.getAlpha()),
                        paretoTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void poisval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(poissonTransitionDistribution.getLambdaValue()),
                        formatDouble(poissonTransitionDistribution.getTValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void poisfun(\"%s\", %s, %s);",
                        transition.getName(),
                        poissonTransitionDistribution.getLambdaValueFunction().getName(),
                        poissonTransitionDistribution.getTValueFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void poisdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(poissonTransitionDistribution.getLambdaValue()),
                        formatDouble(poissonTransitionDistribution.getTValue()),
                        poissonTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void normval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(truncatedNormalTransitionDistribution.getExpectation()),
                        formatDouble(truncatedNormalTransitionDistribution.getVariance())));
                break;

            case Functional:
                stringBuilder.append(String.format("void normfun(\"%s\", %s, %s);",
                        transition.getName(),
                        truncatedNormalTransitionDistribution.getExpectationFunction().getName(),
                        truncatedNormalTransitionDistribution.getVarianceFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void normdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(truncatedNormalTransitionDistribution.getExpectation()),
                        formatDouble(truncatedNormalTransitionDistribution.getVariance()),
                        truncatedNormalTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void unifval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(uniformTransitionDistribution.getLowerBound()),
                        formatDouble(uniformTransitionDistribution.getUpperBound())));
                break;

            case Functional:
                stringBuilder.append(String.format("void uniffun(\"%s\", %s, %s);",
                        transition.getName(),
                        uniformTransitionDistribution.getLowerBoundFunction().getName(),
                        uniformTransitionDistribution.getUpperBoundFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void unifdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(uniformTransitionDistribution.getLowerBound()),
                        formatDouble(uniformTransitionDistribution.getUpperBound()),
                        uniformTransitionDistribution.getDependentPlace().getName()));
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
                stringBuilder.append(String.format("void weibval(\"%s\", %s, %s);",
                        transition.getName(),
                        formatDouble(weibullTransitionDistribution.getAlphaValue()),
                        formatDouble(weibullTransitionDistribution.getLambdaValue())));
                break;

            case Functional:
                stringBuilder.append(String.format("void weibfun(\"%s\", %s, %s);",
                        transition.getName(),
                        weibullTransitionDistribution.getAlphaValueFunction().getName(),
                        weibullTransitionDistribution.getLambdaValueFunction().getName()));
                break;

            case PlaceDependent:
                stringBuilder.append(String.format("void weibdep(\"%s\", %s, %s, \"%s\");",
                        transition.getName(),
                        formatDouble(weibullTransitionDistribution.getAlphaValue()),
                        formatDouble(weibullTransitionDistribution.getLambdaValue()),
                        weibullTransitionDistribution.getDependentPlace().getName()));
                break;

            default:
                throw new IllegalStateException("Weibull transition distribution has unknown type.");
        }
    }
}
