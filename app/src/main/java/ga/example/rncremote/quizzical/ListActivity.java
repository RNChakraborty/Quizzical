package ga.example.rncremote.quizzical;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rudraneel on 10/19/2017.
 */

public class ListActivity extends AppCompatActivity implements QuizRepository.QuizzesCallback {
    QuizAdapter adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quiz quiz = adapter.getItem(position);
                Intent intent = new Intent(ListActivity.this, QuizActivity.class);
                intent.putExtra(Constants.KEY_QUIZ_ID, quiz.getId());
                startActivity(intent);
            }
        });

        new QuizRepository(this).getRemoteQuizzes(this);
//        List<String> strings = Arrays.asList("First", "Second","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third"
//                ,"Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third");
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        //listView.setAdapter(adapter);
    }


    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess(List<Quiz> quizzes) {
        // quizAda = new ArrayAdapter(this, android.R.layout.simple_list_item_1, quizzes);

        //Caching(!) for rotation
        if (Constants.adapter == null) {
            adapter = new QuizAdapter(this, quizzes);
            Constants.adapter = adapter;
            Log.e("caching", "***Not Getting adapter from static var(cache!!)");
        } else {
            Log.e("caching", "**Getting adapter from static var(cache!!) ");
            adapter = Constants.adapter;
        }

        listView.setAdapter(adapter);

    }

    class QuizAdapter extends ArrayAdapter<Quiz> {
        public QuizAdapter(@NonNull Context context, @NonNull List<Quiz> quizzes) {
            super(context, 0, quizzes);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView text = (TextView) convertView;

            Quiz quiz = getItem(position);

            String numOfItems = quiz.getList().size() + "";
            text.setText(quiz.getTitle() + " (" + numOfItems + ")");
            Log.e("bootcamp", quiz.getTitle());

            return convertView;

        }
    }
}