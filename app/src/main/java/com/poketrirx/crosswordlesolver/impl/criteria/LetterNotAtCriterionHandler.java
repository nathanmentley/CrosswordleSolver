package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.LetterNotAtCriterion;

final class LetterNotAtCriterionHandler implements CriterionHandler<String> {
    public boolean canHandle(Criterion criterion) {
        return criterion instanceof LetterNotAtCriterion;
    }

    public Stream<String> handle(Criterion criterion, Stream<String> stream) {
        if (criterion instanceof LetterNotAtCriterion letterNotAtCriterion) {
            return stream.filter(word -> word.charAt(letterNotAtCriterion.getPosition()) != letterNotAtCriterion.getLetter());
        }

        return stream;
    }
}
