package xpadro.spring.data.repository;

import xpadro.spring.data.dto.ScorerNotNestedStats;
import xpadro.spring.data.dto.ScorerStats;

import java.util.List;

public interface ScorerResultsRepositoryCustom {

    List<ScorerNotNestedStats> aggregateNotNested();

    List<ScorerStats> aggregateNested();
}
