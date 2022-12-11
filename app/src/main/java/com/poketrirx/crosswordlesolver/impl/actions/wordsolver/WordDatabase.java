package com.poketrirx.crosswordlesolver.impl.actions.wordsolver;

import com.google.common.collect.ImmutableList;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
final class WordDatabase {
    @Getter
    @Singular
    private final ImmutableList<String> words;
}
