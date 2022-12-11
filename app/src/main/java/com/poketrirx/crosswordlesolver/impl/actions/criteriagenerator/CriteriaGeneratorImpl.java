package com.poketrirx.crosswordlesolver.impl.actions.criteriagenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.poketrirx.crosswordlesolver.pub.actions.criteriagenerator.CriteriaGenerator;
import com.poketrirx.crosswordlesolver.pub.criteria.ContainsCriterion;
import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.CriterionCollection;
import com.poketrirx.crosswordlesolver.pub.criteria.ExcludesCriterion;
import com.poketrirx.crosswordlesolver.pub.criteria.LetterAtCriterion;
import com.poketrirx.crosswordlesolver.pub.criteria.LetterNotAtCriterion;
import com.poketrirx.crosswordlesolver.pub.models.Problem;
import com.poketrirx.crosswordlesolver.pub.models.ProblemStage;
import com.poketrirx.crosswordlesolver.pub.models.Space;
import com.poketrirx.crosswordlesolver.pub.models.Word;

public final class CriteriaGeneratorImpl implements CriteriaGenerator {
    public CriterionCollection getCriteria(Word initialWord, Problem problem, List<Word> solution, int step) {
        Set<Criterion> criteria = new HashSet<Criterion>();

        //Exclude letters not used form the initial word.
        populateConstraintsForUnusedInitialWordLetters(criteria, initialWord, solution, step);

        //Exlcude letters used up from the previous stages.
        populateConstraintsForUsedWords(criteria, initialWord, problem, solution, step);

        //Setup the constraints for this word.
        populateConstraintsForStage(criteria, initialWord, problem, step);

        //Create criterion collection.
        return CriterionCollection.builder().collection(criteria).build();
    }

    private static void populateConstraintsForUnusedInitialWordLetters(
        Set<Criterion> criteria,
        Word initialWord,
        List<Word> solution,
        int step
    ) {
        List<Character> initialCharacters = initialWord.getValue().chars()
            .mapToObj(e -> (char)e).collect(Collectors.toList());

        Set<Character> toExclude = new HashSet<Character>();

        List<Character> usedCharacters = solution.get(step).getValue().chars()
            .mapToObj(e -> (char)e).collect(Collectors.toList());

        for (Character initialCharacter : initialCharacters) {
            if (!usedCharacters.contains(initialCharacter)) {
                toExclude.add(initialCharacter);
            }
        }

        for (Character character : toExclude) {
            criteria.add(
                ExcludesCriterion.builder()
                    .letter(character)
                    .build()
            );
        }
    }

    private static void populateConstraintsForUsedWords(
        Set<Criterion> criteria,
        Word initialWord,
        Problem problem,
        List<Word> solution,
        int step
    ) {
        for (int i = 1; i <= step; i++) {
            ProblemStage problemStage = problem.getStages().get(i);
            for (int position = 0; position < problemStage.getSpaces().size(); position++) {
                Space space = problemStage.getSpaces().get(position);

                switch (space) {
                    case CorrectPosition:
                    case IncorrectPosition:
                        break;
                    case NotInWord:
                        char letter = solution.get(i).getValue().charAt(position);

                        populateNotInWordCriteria(criteria, letter);

                        break;
                }
            }
        }
    }

    private static void populateConstraintsForStage(Set<Criterion> criteria, Word initialWord, Problem problem, int step) {
        ProblemStage problemStage = problem.getStages().get(step + 1);
        for (int position = 0; position < problemStage.getSpaces().size(); position++) {
            Space space = problemStage.getSpaces().get(position);

            char letter = initialWord.getValue().charAt(position);

            switch (space) {
                case CorrectPosition:
                    populateCorrectPositionCriteria(criteria, letter, position);

                    break;
                case IncorrectPosition:
                    populateIncorrectPositionCriteria(criteria, letter, position);

                    break;
                case NotInWord:
                    populateNotInWordCriteria(criteria, letter);

                    break;
            }
        }
    }

    private static void populateCorrectPositionCriteria(Set<Criterion> criteria, char letter, int position) {
        criteria.add(
            LetterAtCriterion
                .builder()
                .letter(letter)
                .position(position)
                .build()
        );
    }

    private static void populateIncorrectPositionCriteria(Set<Criterion> criteria, char letter, int position) {
        criteria.add(
            LetterNotAtCriterion
                .builder()
                .letter(letter)
                .position(position)
                .build()
        );

        int count = criteria.stream()
            .filter(criterion -> {
                return
                    criterion instanceof ContainsCriterion containsCriterion &&
                    containsCriterion.getLetter() == letter;
            })
            .collect(Collectors.toSet())
            .size();

        criteria.add(
            ContainsCriterion
                .builder()
                .letter(letter)
                .count(count)
                .build()
        );
    }

    private static void populateNotInWordCriteria(Set<Criterion> criteria, char letter) {
        criteria.add(
            ExcludesCriterion
                .builder()
                .letter(letter)
                .build()
        );
    }
}