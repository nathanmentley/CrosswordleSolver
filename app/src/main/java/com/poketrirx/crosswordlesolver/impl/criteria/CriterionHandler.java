package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.stream.Stream;

import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;

interface CriterionHandler<TData> {
    boolean canHandle(Criterion criterion);
    Stream<TData> handle(Criterion criterion, Stream<TData> stream);
}
