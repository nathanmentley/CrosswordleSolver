package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.poketrirx.crosswordlesolver.pub.criteria.ContainsCriterion;
import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;

final class ContainsCriterionHandler implements CriterionHandler<String> {
    public boolean canHandle(Criterion criterion) {
        return criterion instanceof ContainsCriterion;
    }

    public Stream<String> handle(Criterion criterion, Stream<String> stream) {
        if (criterion instanceof ContainsCriterion containsCriterion) {
            return stream.filter(word -> StringUtils.countMatches(word, String.valueOf(containsCriterion.getLetter())) >= containsCriterion.getCount());
        }

        return stream;
    }
}
