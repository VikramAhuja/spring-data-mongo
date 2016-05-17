package xpadro.spring.data.dto;

public class ScorerStats {
    private String league;
    private int totalGoals;
    private Scorer topScorer;

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    public Scorer getTopScorer() {
        return topScorer;
    }

    public void setTopScorer(Scorer topScorer) {
        this.topScorer = topScorer;
    }
}
