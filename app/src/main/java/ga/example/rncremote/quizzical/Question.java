package ga.example.rncremote.quizzical;

/**
 * Created by rudraneel on 10/18/2017.
 */

public class Question {
    private boolean answer;
    private String statement;
    public Question(boolean answer, String statement) {
        this.answer = answer;
        this.statement = statement;
    }
    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}

