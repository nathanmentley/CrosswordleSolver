package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.LetterAtCriterion;

final class LetterAtCriterionHandler implements CriterionHandler<String> {
    public boolean canHandle(Criterion criterion) {
        return criterion instanceof LetterAtCriterion;
    }

    public Stream<String> handle(Criterion criterion, Stream<String> stream) {
        if (criterion instanceof LetterAtCriterion letterAtCriterion) {
            return stream.filter(word -> word.charAt(letterAtCriterion.getPosition()) == letterAtCriterion.getLetter());
        }

        return stream;
    }
}
