package com.poketrirx.crosswordlesolver.pub.criteria;

import java.util.Set;

public interface CriteriaProcessor<TData> {
    Set<TData> process(Iterable<TData> data, Set<Criterion> criteria);
}