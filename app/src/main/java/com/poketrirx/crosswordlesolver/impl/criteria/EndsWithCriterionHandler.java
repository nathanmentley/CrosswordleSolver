package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.EndsWithCriterion;

final class EndsWithCriterionHandler implements CriterionHandler<String> {
    public boolean canHandle(Criterion criterion) {
        return criterion instanceof EndsWithCriterion;
    }

    public Stream<String> handle(Criterion criterion, Stream<String> stream) {
        if (criterion instanceof EndsWithCriterion endsWithCriterion) {
            return stream.filter(word -> word.endsWith(endsWithCriterion.getSubstring()));
        }

        return stream;
    }
}
