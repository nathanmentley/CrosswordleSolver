package com.poketrirx.crosswordlesolver.impl.actions.wordsolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.baggonius.gson.immutable.ImmutableListDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.poketrirx.crosswordlesolver.pub.actions.wordsolver.WordSolver;
import com.poketrirx.crosswordlesolver.pub.criteria.CriteriaProcessor;
import com.poketrirx.crosswordlesolver.pub.criteria.CriterionCollection;
import com.poketrirx.crosswordlesolver.pub.models.Word;

public final class WordSolverImpl implements WordSolver {
    private final static Gson GSON = new GsonBuilder()
        .registerTypeAdapter(ImmutableList.class, new ImmutableListDeserializer())
        .create();

    private final CriteriaProcessor<String> criteriaProcessor;

    private final WordDatabase wordDatabase;

    public WordSolverImpl(CriteriaProcessor<String> criteriaProcessor) {
        this.criteriaProcessor = Objects.requireNonNull(criteriaProcessor);

        String json;
        try {
            json = Resources.toString(Resources.getResource("wordlist.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            json = "{\"words\": []}";
        }
        wordDatabase = GSON.fromJson(json, WordDatabase.class);
    }

    @Override
    public Set<Word> fetch(CriterionCollection criterionCollection) {
        return criteriaProcessor
            .process(wordDatabase.getWords(), criterionCollection.getCollection())
            .stream()
            .map(value -> Word.builder().value(value).build())
            .collect(Collectors.toSet());
    }
}