package ga.example.rncremote.quizzical;

import android.content.Context;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;

import okio.Okio;

/**
 * Created by rudraneel on 10/19/2017.
 */

public class QuizRepository {
    private Context context;
    private static final String LOG_TAG = "QuizRepository";
    private static final String QUIZ_JSON = "quiz.json";

    public QuizRepository(Context context) {
        this.context = context;
    }

    public Quiz getQuiz() {
        InputStream assetInputStream;
        try {
            assetInputStream = context.getAssets().open(QUIZ_JSON);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not open quiz parse json", e);
            return null;

        }
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Quiz> jsonAdapter = moshi.adapter(Quiz.class);
        try {
            Quiz quiz = jsonAdapter.fromJson(Okio.buffer(Okio.source(assetInputStream)));
            return quiz;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not parse json", e);
            return null;
        }


    }
}