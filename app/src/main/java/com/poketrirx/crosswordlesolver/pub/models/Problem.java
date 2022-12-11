package com.poketrirx.crosswordlesolver.pub.models;

import com.google.common.collect.ImmutableMap;

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
public final class Problem {
    @Getter
    @Singular
    private final ImmutableMap<Integer, ProblemStage> stages;

    @Getter
    private final Word initialWord;

    public int getNumberOfStages() {
        return stages.size();
    }
}
