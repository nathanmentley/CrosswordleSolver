package com.poketrirx.crosswordlesolver.pub.actions.wordsolver;

import java.util.Set;

import com.poketrirx.crosswordlesolver.pub.criteria.CriterionCollection;
import com.poketrirx.crosswordlesolver.pub.models.Word;

public interface WordSolver {
    Set<Word> fetch(CriterionCollection criterionCollection);
}