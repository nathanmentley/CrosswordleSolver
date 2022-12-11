package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.ExcludesCriterion;

final class ExcludesCriterionHandler implements CriterionHandler<String> {
    public boolean canHandle(Criterion criterion) {
        return criterion instanceof ExcludesCriterion;
    }

    public Stream<String> handle(Criterion criterion, Stream<String> stream) {
        if (criterion instanceof ExcludesCriterion excludesCriterion) {
            return stream.filter(word -> word.indexOf(excludesCriterion.getLetter()) == -1);
        }

        return stream;
    }
}
