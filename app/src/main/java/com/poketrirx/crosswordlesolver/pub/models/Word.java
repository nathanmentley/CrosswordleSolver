package com.poketrirx.crosswordlesolver.pub.models;

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
public final class Word {
    @NonNull
    @Getter
    private final String value;
}