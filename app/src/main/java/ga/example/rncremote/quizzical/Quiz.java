package ga.example.rncremote.quizzical;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudraneel on 10/18/2017.
 */

public class Quiz {
    private List<Question> qList = new ArrayList();
    private static Quiz quiz;

    public List<Question> getList() {
        return qList;
    }

    public void addQuestion(Question q) {
        qList.add(q);

    }

    public static Quiz getInstance() {
        if (quiz == null) {
            quiz = new Quiz();
            quiz.addQuestion(new Question(false, "The moon is made of cheese"));
            quiz.addQuestion(new Question(true, "The sky is blue"));
        }

        return quiz;
    }
}
