package ga.example.rncremote.quizzical;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rudraneel on 10/19/2017.
 */

public interface QuizService {

    @GET("cdn/quiz.json")
    Call<Quiz> getQuiz();
}
