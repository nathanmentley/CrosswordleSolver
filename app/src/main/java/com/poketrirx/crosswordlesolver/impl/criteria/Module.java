package com.poketrirx.crosswordlesolver.impl.criteria;

import com.poketrirx.crosswordlesolver.pub.criteria.CriteriaProcessor;

public final class Module {
    private static final CriteriaProcessor<String> CRITERIA_PROCESSOR =
        CriteriaProcessorImpl.<String>builder()
            .criterionHandler(new ContainsCriterionHandler())
            .criterionHandler(new EndsWithCriterionHandler())
            .criterionHandler(new ExcludesCriterionHandler())
            .criterionHandler(new LengthCriterionHandler())
            .criterionHandler(new LetterAtCriterionHandler())
            .criterionHandler(new LetterNotAtCriterionHandler())
            .criterionHandler(new StartsWithCriterionHandler())
            .build();

    public static CriteriaProcessor<String> fetch() {
        return CRITERIA_PROCESSOR;
    }
}
