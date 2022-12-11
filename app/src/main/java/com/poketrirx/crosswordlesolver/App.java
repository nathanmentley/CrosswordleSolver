package com.poketrirx.crosswordlesolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import com.baggonius.gson.immutable.ImmutableListDeserializer;
import com.baggonius.gson.immutable.ImmutableMapDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.poketrirx.crosswordlesolver.impl.actions.criteriagenerator.CriteriaGeneratorImpl;
import com.poketrirx.crosswordlesolver.impl.actions.solver.SolverImpl;
import com.poketrirx.crosswordlesolver.impl.actions.wordsolver.WordSolverImpl;
import com.poketrirx.crosswordlesolver.pub.actions.solver.Solver;
import com.poketrirx.crosswordlesolver.pub.models.Problem;
import com.poketrirx.crosswordlesolver.pub.models.Solution;

public class App {
    private final static Gson GSON = new GsonBuilder()
        .registerTypeAdapter(ImmutableList.class, new ImmutableListDeserializer())
        .registerTypeAdapter(ImmutableMap.class, new ImmutableMapDeserializer())
        .create();

    private static Solver solver = new SolverImpl(
        new CriteriaGeneratorImpl(),
        new WordSolverImpl(com.poketrirx.crosswordlesolver.impl.criteria.Module.fetch())
    );

    public static void main(String[] args) {
        Problem problem = getProblem();

        Set<Solution> solutions = solver.solve(problem);

        Solution solution = solutions.stream().findFirst().orElse(Solution.builder().build());

        System.out.println(solution);
    }

    private static Problem getProblem() {
        try {
            String json = Resources.toString(Resources.getResource("problem.json"), StandardCharsets.UTF_8);

            return GSON.fromJson(json, Problem.class);
        } catch (IOException e) {
            return Problem.builder().build();
        }
    }
}