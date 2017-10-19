package ga.example.rncremote.quizzical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private RadioButton trueButton;
    private RadioButton falseButton;

    private Button nextButton;

    private TextView questionText;
    private TextView resultText;
    private TextView usrText;
    ImageView rightOrWrongImage;

    private String questionToAsk = "";

    private boolean questionAnswered = false;
    private boolean lastAnswer;
    private boolean isAnswered;
    private int score = 0;

    private static final String KEY_ANSWERED = "is_answered";
    private static final String KEY_LAST_ANSWER = "last_answer";
    private static final String KEY_QUESTION_INDEX = "question_index";
    private static final String KEY_SCORE = "score";
    private static int questionIndex;
    private static final String CORRECT_ANSWER_TEXT = "Correct! +1 points";
    private static final String WRONG_ANSWER_TEXT = "Wrong Answer! +0 points ";
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        quiz= new QuizRepository(this).getQuiz();
        setContentView(R.layout.activity_quiz);
        initialize();
        attachListners();
        boolean playAgain = getIntent().getBooleanExtra(Constants.KEY_PLAY_AGAIN, false);
        if (playAgain) {
            questionIndex = 0;
            enableRadio();
        }
        showQuestion();
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(KEY_ANSWERED)) {
                checkAnswer(savedInstanceState.getBoolean(KEY_LAST_ANSWER));
                questionIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX, -1);
                this.score = savedInstanceState.getInt(KEY_SCORE, -1);
            }
            Log.e("again Called", "Oncreate Called again");
        } else {
            questionIndex = 0;
        }
    }


    public void initialize() {


        trueButton = (RadioButton) findViewById(R.id.true_button);
        falseButton = (RadioButton) findViewById(R.id.false_button);

        nextButton = (Button) findViewById(R.id.next_button);

        questionText = (TextView) findViewById(R.id.question_text);
        resultText = (TextView) findViewById(R.id.result_text);
        usrText = (TextView) findViewById(R.id.user_text);
        rightOrWrongImage = (ImageView) findViewById(R.id.right_or_wrong_img);
        usrText.setText("Hi " + getIntent().getStringExtra(Constants.KEY_USER_NAME) + " :) !");
    }

    public void attachListners() {
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                falseButton.setChecked(false);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

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

    public void showQuestion() {
        enableRadio();
        resultText.setText("");
        rightOrWrongImage.setImageResource(R.mipmap.ic_launcher);
        questionText.setText(quiz.getList().get(questionIndex).getStatement());

    }

    public void checkAnswer(boolean picked) {
        disableRadio();
        lastAnswer = picked;
        isAnswered = true;
        if (picked == quiz.getList().get(questionIndex).getAnswer()) {
            // Correct;
            rightOrWrongImage.setImageResource(R.drawable.tick);
            resultText.setText(CORRECT_ANSWER_TEXT);
            score++;

        } else {
            //Incorrect
            rightOrWrongImage.setImageResource(R.drawable.wrong);
            resultText.setText(WRONG_ANSWER_TEXT);
        }

    }

    public void nextQuestion() {

        if (questionIndex >= quiz.getList().size() - 1) {
            forwardIntent();
        } else {
            questionIndex++;
            showQuestion();

        }


    }

    private Question getCurrentQuestion() {
        return quiz.getList().get(questionIndex);
    }

    public void forwardIntent() {
        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra(ResultActivity.KEY_SCORE, score);
        resultIntent.putExtra(ResultActivity.KEY_TOTAL, quiz.getList().size());
        resultIntent.putExtra(Constants.KEY_USER_NAME, Constants.USER_NAME);
        startActivity(resultIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_LAST_ANSWER, lastAnswer);
        outState.putBoolean(KEY_ANSWERED, isAnswered);
        outState.putInt(KEY_QUESTION_INDEX, questionIndex);
        outState.putInt(KEY_SCORE, score);
        Log.e("onsave", "onsaveInstance called");

    }

    public void enableRadio() {
        trueButton.setChecked(false);
        falseButton.setChecked(false);
        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
    }

    public void disableRadio() {

        trueButton.setEnabled(false);
        falseButton.setEnabled(false);
    }
}


