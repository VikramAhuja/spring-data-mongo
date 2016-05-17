package xpadro.spring.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xpadro.spring.data.domain.ScorerResults;
import xpadro.spring.data.dto.ScorerNotNestedStats;
import xpadro.spring.data.dto.ScorerStats;
import xpadro.spring.data.repository.ScorerResultsRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AggregationApplication.class)
@WebAppConfiguration
public class AggregationApplicationTests {

	@Autowired
	private ScorerResultsRepository scorerResultsRepository;

	@Before
	public void setUp() {
		scorerResultsRepository.deleteAll();
	}

	@Test
	public void aggregateScorerStats_notNestedResult() {
		saveScores();

		List<ScorerNotNestedStats> scoreStats = scorerResultsRepository.aggregateNotNested();

		ScorerNotNestedStats spanishLeagueStats = scoreStats.stream().filter(stats -> "spanish".equals(stats.getLeague())).findAny().get();
		assertEquals(54, spanishLeagueStats.getTotalGoals());
		assertEquals("Messi", spanishLeagueStats.getTopPlayer());
	}

	@Test
	public void aggregateScorerStats_nestedResult() {
		saveScores();

		List<ScorerStats> scoreStats = scorerResultsRepository.aggregateNested();

		ScorerStats spanishLeagueStats = scoreStats.stream().filter(stats -> "spanish".equals(stats.getLeague())).findAny().get();
		assertEquals(54, spanishLeagueStats.getTotalGoals());
		assertEquals("Messi", spanishLeagueStats.getTopScorer().getName());
	}

	private void saveScores() {
		scorerResultsRepository.save(new ScorerResults("Messi", "Argentina", "spanish", 31));
		scorerResultsRepository.save(new ScorerResults("Neymar", "Brazil", "spanish", 23));
		scorerResultsRepository.save(new ScorerResults("Aguero", "Argentina", "english", 25));
		scorerResultsRepository.save(new ScorerResults("Bacca", "Colombia", "italian", 18));
		scorerResultsRepository.save(new ScorerResults("Lewandowski", "Poland", "german", 22));
		scorerResultsRepository.save(new ScorerResults("Huntelaar", "Holland", "german", 19));
		scorerResultsRepository.save(new ScorerResults("Kane", "England", "english", 26));
		scorerResultsRepository.save(new ScorerResults("Higuain", "Argentina", "italian", 20));
	}
}
