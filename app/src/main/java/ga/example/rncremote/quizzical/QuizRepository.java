package ga.example.rncremote.quizzical;

import android.content.Context;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;

import okio.Okio;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by rudraneel on 10/19/2017.
 */

public class QuizRepository {
    private Context context;
    private static final String LOG_TAG = "QuizRepository";
    private static final String QUIZ_JSON = "quiz.json";
    static final String[] listOfJSONFiles = {"quiz.json", "quiz.json"};


    public QuizRepository(Context context) {
        this.context = context;
    }

    public Quiz getAllQuiz() {
        for (String jsonFile : listOfJSONFiles) {
            getQuiz(jsonFile);
        }
        return null;
    }


    public Quiz getQuiz() {
        InputStream assetInputStream;
        // Log.e("abcde",context.getAssets().list().length+"");
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

    public Quiz getQuiz(String jsonFileName) {
        InputStream assetInputStream;
        // Log.e("abcde",context.getAssets().list().length+"");
        try {
            assetInputStream = context.getAssets().open(jsonFileName);
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


    public void getRemoteQuiz(final CallBack callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://oolong.tahnok.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        QuizService service = retrofit.create(QuizService.class);
        service.getQuiz();

        service.getQuiz().enqueue(new retrofit2.Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                callback.onFailure();

            }
        });
        //return null;
    }

    public interface CallBack {
        void onFailure();

        void onSuccess(Quiz quiz);
    }
}

