package program;

public interface QuestionInterface {
    String text = "";
    String answer = "";

    default boolean isCorrect(String theUserAnswer) {
        return theUserAnswer.equals(answer);
    }
}
