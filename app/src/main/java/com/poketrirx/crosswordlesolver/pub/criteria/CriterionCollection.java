package com.poketrirx.crosswordlesolver.pub.criteria;

import com.google.common.collect.ImmutableSet;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;

@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CriterionCollection {
    @Singular("criterion")
    @Getter
    @NonNull
    private final ImmutableSet<Criterion> collection;
}
