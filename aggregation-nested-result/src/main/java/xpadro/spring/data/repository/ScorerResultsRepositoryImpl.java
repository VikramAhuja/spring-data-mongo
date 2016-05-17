package xpadro.spring.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import xpadro.spring.data.domain.ScorerResults;
import xpadro.spring.data.dto.ScorerNotNestedStats;
import xpadro.spring.data.dto.ScorerStats;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class ScorerResultsRepositoryImpl implements ScorerResultsRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ScorerResultsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ScorerNotNestedStats> aggregateNotNested() {
        SortOperation sortOperation = buildSortOpertation();
        GroupOperation groupOperation = buildGroupOperation();

        return mongoTemplate.aggregate(Aggregation.newAggregation(
                sortOperation,
                groupOperation
        ), ScorerResults.class, ScorerNotNestedStats.class).getMappedResults();
    }

    @Override
    public List<ScorerStats> aggregateNested() {
        SortOperation sortOperation = buildSortOpertation();
        GroupOperation groupOperation = buildGroupOperation();

        ProjectionOperation projectionOperation = project("totalGoals")
                .and("league").as("league")
                .and("topScorer").nested(
                        bind("name", "topPlayer").and("goals", "topGoals").and("country", "topCountry")
                );

        return mongoTemplate.aggregate(Aggregation.newAggregation(
                sortOperation,
                groupOperation,
                projectionOperation
        ), ScorerResults.class, ScorerStats.class).getMappedResults();
    }

    private SortOperation buildSortOpertation() {
        return sort(Sort.Direction.DESC, "goals");
    }

    private GroupOperation buildGroupOperation() {
        return group("league")
                .first("league").as("league")
                .sum("goals").as("totalGoals")
                .first("player").as("topPlayer")
                .first("goals").as("topGoals")
                .first("country").as("topCountry");
    }
}
