package coding_problems.sedgewick.coursera.course2.week5_regular_expressions;

public class Nfa { // todo: non deterministic finite automata

    private Nfa(String regularExpression) {

    }

    public static Nfa of(String regularExpression) {
        return new Nfa(regularExpression);
    }
}
