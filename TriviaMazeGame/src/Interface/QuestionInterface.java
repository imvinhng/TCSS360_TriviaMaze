package Interface;

public interface QuestionInterface {
    String text = "";
    String answer = "";

    default boolean isCorrect(String theUserAnswer) {
        return theUserAnswer.equals(answer);
    }
}
