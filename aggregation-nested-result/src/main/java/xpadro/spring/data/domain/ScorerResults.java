package xpadro.spring.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ScorerResults {
    @Id
    private final String player;
    private final String country;
    private final String league;
    private final int goals;

    public ScorerResults(String player, String country, String league, int goals) {
        this.player = player;
        this.country = country;
        this.league = league;
        this.goals = goals;
    }

    public String getPlayer() {
        return player;
    }

    public String getCountry() {
        return country;
    }

    public String getLeague() {
        return league;
    }

    public int getGoals() {
        return goals;
    }
}
