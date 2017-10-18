package ga.example.rncremote.quizzical;

/**
 * Created by rudraneel on 10/18/2017.
 */

public class Question {
    private boolean correctAnswer;
    private String question;
    public Question(boolean correctAnswer, String question) {
        this.correctAnswer = correctAnswer;
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

