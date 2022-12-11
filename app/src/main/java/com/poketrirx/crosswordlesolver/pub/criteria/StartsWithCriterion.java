package com.poketrirx.crosswordlesolver.pub.criteria;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public final class StartsWithCriterion implements Criterion {
    @Getter
    @NonNull
    private final String substring;
}