package com.poketrirx.crosswordlesolver.impl.actions.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.poketrirx.crosswordlesolver.pub.actions.criteriagenerator.CriteriaGenerator;
import com.poketrirx.crosswordlesolver.pub.actions.solver.Solver;
import com.poketrirx.crosswordlesolver.pub.actions.wordsolver.WordSolver;
import com.poketrirx.crosswordlesolver.pub.criteria.CriterionCollection;
import com.poketrirx.crosswordlesolver.pub.models.Problem;
import com.poketrirx.crosswordlesolver.pub.models.Solution;
import com.poketrirx.crosswordlesolver.pub.models.Word;

public final class SolverImpl implements Solver {
    private final CriteriaGenerator criterionCollectionGenerator;
    private final WordSolver wordHandler;

    public SolverImpl(CriteriaGenerator criterionCollectionGenerator, WordSolver wordHandler) {
        this.criterionCollectionGenerator = Objects.requireNonNull(criterionCollectionGenerator);
        this.wordHandler = Objects.requireNonNull(wordHandler);
    }

    public Set<Solution> solve(Problem problem) {
        List<Word> solution = new ArrayList<Word>();
        solution.add(problem.getInitialWord());

        for(int i = 0; i < problem.getNumberOfStages(); i++) {
            CriterionCollection criterionCollection =
                criterionCollectionGenerator.getCriteria(problem.getInitialWord(), problem, solution, i);

            Set<Word> words = wordHandler.fetch(criterionCollection);

            if (words.isEmpty()) {
                break;
            }
            
            solution.add(words.stream().findFirst().get());
        }

        return Set.of(Solution.builder().words(solution).build());
    }
}