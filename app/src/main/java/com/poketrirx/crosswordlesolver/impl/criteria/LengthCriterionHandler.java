package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.LengthCriterion;

final class LengthCriterionHandler implements CriterionHandler<String> {
    public boolean canHandle(Criterion criterion) {
        return criterion instanceof LengthCriterion;
    }

    public Stream<String> handle(Criterion criterion, Stream<String> stream) {
        if (criterion instanceof LengthCriterion lengthCriterion) {
            return stream.filter(word -> word.length() == lengthCriterion.getLength());
        }

        return stream;
    }
}
