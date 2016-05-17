package xpadro.spring.data.dto;

public class ScorerNotNestedStats {
    private String league;
    private int totalGoals;
    private String topPlayer;
    private String topCountry;
    private int topGoals;

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

    public String getTopPlayer() {
        return topPlayer;
    }

    public void setTopPlayer(String topPlayer) {
        this.topPlayer = topPlayer;
    }

    public String getTopCountry() {
        return topCountry;
    }

    public void setTopCountry(String topCountry) {
        this.topCountry = topCountry;
    }

    public int getTopGoals() {
        return topGoals;
    }

    public void setTopGoals(int topGoals) {
        this.topGoals = topGoals;
    }
}
