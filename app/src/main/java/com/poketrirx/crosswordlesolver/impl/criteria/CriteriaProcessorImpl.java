package com.poketrirx.crosswordlesolver.impl.criteria;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.poketrirx.crosswordlesolver.pub.criteria.CriteriaProcessor;
import com.poketrirx.crosswordlesolver.pub.criteria.Criterion;
import com.poketrirx.crosswordlesolver.pub.criteria.UnprocessableCriterionException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder()
@AllArgsConstructor(access = AccessLevel.PROTECTED)
final class CriteriaProcessorImpl<TData> implements CriteriaProcessor<TData> {
    @NonNull
    @Singular
    private final Set<CriterionHandler<TData>> criterionHandlers;

    public Set<TData> process(Iterable<TData> data, Set<Criterion> criteria) throws UnprocessableCriterionException {
        Stream<TData> stream = StreamSupport.stream(data.spliterator(), true);

        for (Criterion criterion : criteria) {
            Optional<CriterionHandler<TData>> matchingHandler =
                criterionHandlers
                    .stream()
                    .filter((handler) -> handler.canHandle(criterion))
                    .findFirst();

            if (matchingHandler.isEmpty()) {
                throw new UnprocessableCriterionException();
            }

            stream = matchingHandler.get().handle(criterion, stream);
        }

        return stream.collect(Collectors.toSet());
    }
}