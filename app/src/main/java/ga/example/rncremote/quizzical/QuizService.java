package ga.example.rncremote.quizzical;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rudraneel on 10/19/2017.
 */

public interface QuizService {

    @GET("cdn/quizzes/{id}.json")
    Call<Quiz> getQuiz(@Path("id") int id);

    @GET("cdn/quizzes.json")
    Call<List<Quiz>> getQuizzes();

}
