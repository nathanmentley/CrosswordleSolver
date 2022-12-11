package com.poketrirx.crosswordlesolver.pub.actions.criteriagenerator;

import java.util.List;

import com.poketrirx.crosswordlesolver.pub.criteria.CriterionCollection;
import com.poketrirx.crosswordlesolver.pub.models.Problem;
import com.poketrirx.crosswordlesolver.pub.models.Word;

public interface CriteriaGenerator {
    CriterionCollection getCriteria(Word initialWord, Problem problem, List<Word> solution, int step);
}