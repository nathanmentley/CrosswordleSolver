package com.poketrirx.crosswordlesolver.pub.models;

import com.google.common.collect.ImmutableList;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public final class ProblemStage {
    @Getter
    @Singular
    private final ImmutableList<Space> spaces;
}
