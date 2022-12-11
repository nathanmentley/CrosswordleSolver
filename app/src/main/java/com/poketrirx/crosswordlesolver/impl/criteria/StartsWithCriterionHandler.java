package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.StartsWithCriterion;

final class StartsWithCriterionHandler implements CriterionHandler<String> {
    public boolean canHandle(Criterion criterion) {
        return criterion instanceof StartsWithCriterion;
    }

    public Stream<String> handle(Criterion criterion, Stream<String> stream) {
        if (criterion instanceof StartsWithCriterion startsWithCriterion) {
            return stream.filter(word -> word.startsWith(startsWithCriterion.getSubstring()));
        }

        return stream;
    }
}
