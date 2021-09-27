package cz.muni.fi.spnp.core.transformators.spnp.options;

public enum OptionKey {
    IOP_PR_RSET(OptionCategory.INTERMEDIATE),
    IOP_PR_RGRAPH(OptionCategory.INTERMEDIATE),
    IOP_PR_MARK_ORDER(OptionCategory.INTERMEDIATE),
    IOP_PR_MERG_MARK(OptionCategory.INTERMEDIATE),
    IOP_PR_FULL_MARK(OptionCategory.INTERMEDIATE),
    IOP_USENAME(OptionCategory.INTERMEDIATE),
    IOP_PR_MC(OptionCategory.INTERMEDIATE),
    IOP_PR_DERMC(OptionCategory.INTERMEDIATE),
    IOP_PR_MC_ORDER(OptionCategory.INTERMEDIATE),
    IOP_PR_PROB(OptionCategory.INTERMEDIATE),
    IOP_PR_PROBDTMC(OptionCategory.INTERMEDIATE),
    IOP_PR_DOT(OptionCategory.INTERMEDIATE),

    IOP_MC(OptionCategory.ANALYSIS),
    IOP_SSMETHOD(OptionCategory.ANALYSIS),
    IOP_SSDETECT(OptionCategory.ANALYSIS),
    FOP_SSPRES(OptionCategory.ANALYSIS),
    IOP_TSMETHOD(OptionCategory.ANALYSIS),
    IOP_CUMULATIVE(OptionCategory.ANALYSIS),
    IOP_SENSITIVITY(OptionCategory.ANALYSIS),
    IOP_ITERATIONS(OptionCategory.ANALYSIS),
    FOP_PRECISION(OptionCategory.ANALYSIS),

    IOP_SIMULATION(OptionCategory.SIMULATION),
    IOP_SIM_RUNS(OptionCategory.SIMULATION),
    IOP_SIM_RUNMETHOD(OptionCategory.SIMULATION),
    IOP_SIM_SEED(OptionCategory.SIMULATION),
    IOP_SIM_CUMULATIVE(OptionCategory.SIMULATION),
    IOP_SIM_STD_REPORT(OptionCategory.SIMULATION),
    IOP_SPLIT_LEVEL_DOWN(OptionCategory.SIMULATION),
    IOP_SPLIT_PRESIM(OptionCategory.SIMULATION),
    IOP_SPLIT_NUMBER(OptionCategory.SIMULATION),
    IOP_SPLIT_RESTART_FINISH(OptionCategory.SIMULATION),
    IOP_SPLIT_PRESIM_RUNS(OptionCategory.SIMULATION),
    FOP_SIM_LENGTH(OptionCategory.SIMULATION),
    FOP_SIM_CONFIDENCE(OptionCategory.SIMULATION),
    FOP_SIM_ERROR(OptionCategory.SIMULATION),

    IOP_ELIMINATION(OptionCategory.MISCELLANEOUS),
    IOP_OK_ABSMARK(OptionCategory.MISCELLANEOUS),
    IOP_OK_VANLOOP(OptionCategory.MISCELLANEOUS),
    IOP_OK_TRANS_M0(OptionCategory.MISCELLANEOUS),
    IOP_OK_VAN_M0(OptionCategory.MISCELLANEOUS),
    FOP_ABS_RET_M0(OptionCategory.MISCELLANEOUS),
    IOP_DEBUG(OptionCategory.MISCELLANEOUS),
    FOP_FLUID_EPSILON(OptionCategory.MISCELLANEOUS),
    FOP_TIME_EPSILON(OptionCategory.MISCELLANEOUS);

    private final OptionCategory category;

    OptionKey(OptionCategory optionCategory) {
        this.category = optionCategory;
    }

    public OptionCategory getCategory() {
        return category;
    }

}
