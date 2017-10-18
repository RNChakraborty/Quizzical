package ga.example.rncremote.quizzical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioButton trueButton;
    private RadioButton falseButton;

    private Button nextButton;

    private TextView questionText;
    private TextView answerText;

    private String questionToAsk = "";

    private boolean questionAnswered = false;
    private boolean lastAnswer;
    private boolean isAnswered;
    private int score=0;

    private static final String KEY_ANSWERED = "is_answered";
    private static final String KEY_LAST_ANSWER = "last_answer";
    private static final String KEY_QUESTION_INDEX = "question_index";
    private static final String KEY_SCORE = "score";
    private static int questionIndex;
    private static final String CORRECT_ANSWER_TEXT = "Correct! Congrats";
    private static final String WRONG_ANSWER_TEXT = "Wrong Answer:(";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(KEY_ANSWERED)) {
                checkANswer(savedInstanceState.getBoolean(KEY_LAST_ANSWER));
                questionIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX, -1);
                this.score=savedInstanceState.getInt(KEY_SCORE, -1);
            }
            Log.e("bootcamp", savedInstanceState.toString());
        } else {
            questionIndex = 0;
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        showQuestion();
        attachListerners();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_LAST_ANSWER, lastAnswer);
        outState.putBoolean(KEY_ANSWERED, isAnswered);
        outState.putInt(KEY_QUESTION_INDEX, questionIndex);
        outState.putInt(KEY_SCORE,score );


    }


    private void checkANswer(boolean answer) {
        lastAnswer = answer;
        isAnswered = true;
        boolean correctAnswer = getCurrentQuestion().getCorrectAnswer();
        if (answer == correctAnswer) {
            answerText.setText(CORRECT_ANSWER_TEXT);
           // nextButton.setVisibility(View.VISIBLE);
          if(score<Quiz.getInstance().getList().size()) {
              score = score + 1;
          }
            // nextQuestion();
        } else {
            answerText.setText(WRONG_ANSWER_TEXT);
            if(score>=0){
                score--;
            }
            //nextButton.setVisibility(View.INVISIBLE);
        }
    }

    private void showQuestion() {
        questionText.setText(getCurrentQuestion().getQuestion());
        answerText.setText("");
       // nextButton.setVisibility(View.INVISIBLE);
        trueButton.setChecked(false);
        falseButton.setChecked(false);

    }

    private Question getCurrentQuestion() {
        return Quiz.getInstance().getList().get(questionIndex);
    }

    private void nextQuestion() {
        questionIndex = (questionIndex + 1);
        if (questionIndex == Quiz.getInstance().getList().size()) {
            questionIndex=0;
           // score=0;
            Intent resultIntent = new Intent(this, ResultActivity.class);
            resultIntent.putExtra(ResultActivity.KEY_SCORE,score);
            resultIntent.putExtra(ResultActivity.KEY_TOTAL,Quiz.getInstance().getList().size());
            startActivity(resultIntent);

        } else {
            isAnswered = false;
            showQuestion();
        }
    }

    public void initialize() {
        trueButton = (RadioButton) findViewById(R.id.true_button);
        falseButton = (RadioButton) findViewById(R.id.false_button);

        nextButton = (Button) findViewById(R.id.next_button);

        questionText = (TextView) findViewById(R.id.question_text);
        answerText = (TextView) findViewById(R.id.answer_text);
    }

    public void attachListerners() {
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("bootcamp", "true button was clicked");
                checkANswer(true);
                falseButton.setChecked(false);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "false button was clicked");
                checkANswer(false);
                trueButton.setChecked(false);

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextQuestion();
            }
        });
    }

}
