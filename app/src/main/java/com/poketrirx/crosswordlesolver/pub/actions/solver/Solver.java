package com.poketrirx.crosswordlesolver.pub.actions.solver;

import java.util.Set;

import com.poketrirx.crosswordlesolver.pub.models.Problem;
import com.poketrirx.crosswordlesolver.pub.models.Solution;

public interface Solver {
    Set<Solution> solve(Problem problem);
}
