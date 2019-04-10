package coding_problems.sedgewick.coursera.course2.week3_maxflow_mincut;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballElimination {

    private final int numberOfTeams;
    private final String[] teams;
    private final int[] matchesWon;
    private final HashMap<String, Integer> teamIndexMapping;
    private final int[] matchesLeft;
    private final int[] matchesLost;
    private final int[][] matchesLeftWithTeams;
    private final int startVertex;
    private final int finishVertex;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        numberOfTeams = in.readInt();

        teams = new String[numberOfTeams];
        matchesLeft = new int[numberOfTeams];
        matchesWon = new int[numberOfTeams];
        matchesLost = new int[numberOfTeams];
        matchesLeftWithTeams = new int[numberOfTeams][numberOfTeams];
        teamIndexMapping = new HashMap<>();
        for (int i = 0; i < numberOfTeams; i++) {
            teams[i] = in.readString();
            matchesWon[i] = in.readInt();
            matchesLost[i] = in.readInt();
            matchesLeft[i] = in.readInt();
            teamIndexMapping.put(teams[i], i);

            for (int j = 0; j < numberOfTeams; j++) {
                matchesLeftWithTeams[i][j] = in.readInt();
            }
        }

        startVertex = 0; // team vertex numbers start from 1
        finishVertex = numberOfTeams * numberOfTeams;
    }

    public static void main(String[] args) {
        String teamPath = "resources/coding_problems/sedgewick/coursera/course2/week3_maxflow_mincut/teams4.txt";
        BaseballElimination division = new BaseballElimination(teamPath);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }

    // number of teams
    public int numberOfTeams() {
        return numberOfTeams;
    }

    // all teams
    public Iterable<String> teams() {
        return Arrays.asList(teams);
    }

    // number of wins for given team
    public int wins(String team) {
        return wins(teamIndex(team));
    }

    // number of losses for given team
    public int losses(String team) {
        return matchesLost[teamIndex(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return remaining(teamIndex(team));
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return against(teamIndex(team1), teamIndex(team2));
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return certificateOfElimination(team) != null;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return findCertificateOfElimination(team);
    }

    private int remaining(int teamIndex) {
        return matchesLeft[teamIndex];
    }

    private int wins(int teamIndex) {
        return matchesWon[teamIndex];
    }

    private int against(int teamIndex1, int teamIndex2) {
        return matchesLeftWithTeams[teamIndex1][teamIndex2];
    }

    private Collection<String> findCertificateOfElimination(String team) {
        List<String> result = new ArrayList<>();
        Set<Integer> ignoreForFlowNetwork = new HashSet<>();
        int eliminationTeamIndex = teamIndex(team);
        ignoreForFlowNetwork.add(eliminationTeamIndex);

        // try trivial elimination
        int maxWinsForTeam = wins(eliminationTeamIndex) + remaining(eliminationTeamIndex);
        for (int i = 0; i < numberOfTeams; i++) {
            if (i == eliminationTeamIndex) continue;
            if (maxWinsForTeam < wins(i)) {
                ignoreForFlowNetwork.add(i);
                result.add(teams[i]);
            }
        }

        // try max flow elimination
        FlowNetwork flowNetwork = buildFlowNetworkForTeam(team, ignoreForFlowNetwork);
        FordFulkerson fordFulkerson = runMaxFlow(flowNetwork);

        for (int teamVertex = 1; teamVertex <= numberOfTeams; teamVertex++) {
            if (fordFulkerson.inCut(teamVertex)) {
                result.add(teams[teamVertex - 1]);
            }
        }

        return result.isEmpty() ? null : result;
    }

    private FlowNetwork buildFlowNetworkForTeam(String team, Set<Integer> ignoreForFlowNetwork) {
        int teamWins = wins(team);
        int teamRemaining = remaining(team);

        FlowNetwork flowNetwork = new FlowNetwork(numberOfTeams * numberOfTeams + 1);

        // add matches
        for (int i = 0; i < numberOfTeams - 1; i++) {
            for (int j = i + 1; j < numberOfTeams; j++) {
                if (ignoreForFlowNetwork.contains(i) || ignoreForFlowNetwork.contains(j))
                    continue; // exclude queried team from flow network

                int matchVertex = (i + 1) * numberOfTeams + j;
                flowNetwork.addEdge(new FlowEdge(startVertex, matchVertex, against(i, j)));
                int commandVertex1 = i + 1;
                int commandVertex2 = j + 1;
                flowNetwork.addEdge(new FlowEdge(matchVertex, commandVertex1, Double.POSITIVE_INFINITY));
                flowNetwork.addEdge(new FlowEdge(matchVertex, commandVertex2, Double.POSITIVE_INFINITY));
            }
        }

        // to finish
        for (int i = 0; i < numberOfTeams; i++) {
            if (ignoreForFlowNetwork.contains(i)) continue; // exclude queried team from flow network

            int commandVertex = i + 1;
            int capacity = teamWins + teamRemaining - wins(i);
            flowNetwork.addEdge(new FlowEdge(commandVertex, finishVertex, capacity));
        }

        return flowNetwork;
    }

    private FordFulkerson runMaxFlow(FlowNetwork flowNetwork) {
        return new FordFulkerson(flowNetwork, startVertex, finishVertex);
    }

    private int teamIndex(String team) {
        Integer index = teamIndexMapping.get(team);
        if (index == null) throwIllegalArgumentException();
        return index;
    }

    private void throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }
}
