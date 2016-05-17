package xpadro.spring.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xpadro.spring.data.domain.ScorerResults;

@Repository
public interface ScorerResultsRepository extends MongoRepository<ScorerResults, String>, ScorerResultsRepositoryCustom {

}
