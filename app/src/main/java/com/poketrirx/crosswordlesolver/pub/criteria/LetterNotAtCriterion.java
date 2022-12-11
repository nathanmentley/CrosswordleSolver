package com.poketrirx.crosswordlesolver.pub.criteria;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public final class LetterNotAtCriterion implements Criterion {
    @Getter
    private final char letter;

    @Getter
    private final int position;
}