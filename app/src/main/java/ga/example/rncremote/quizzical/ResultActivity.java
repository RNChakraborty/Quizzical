package ga.example.rncremote.quizzical;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rudraneel on 10/18/2017.
 */

public class ResultActivity extends AppCompatActivity {
    public static final String KEY_SCORE = "score";
    public static final String KEY_TOTAL = "total";

    private TextView resultText;
    private TextView usrText;

    private Button playAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
        attachListeners();
        int result = getIntent().getIntExtra(KEY_SCORE, 0);
        int total = getIntent().getIntExtra(KEY_TOTAL, 0);
        decideResultType(result,total);
        resultText.setText(result + "/" + total);

    }

    public void initialize(){
        playAgain=(Button) findViewById(R.id.play_again_button);
        this.resultText = (TextView) findViewById(R.id.result_text);
        usrText = (TextView) findViewById(R.id.user_text);
        usrText.setText("Hi "+ getIntent().getStringExtra(Constants.KEY_USER_NAME)+" :) !");
    }

    public void attachListeners(){
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardIntent();


            }
        });

    }
    public void forwardIntent(){
        Intent quizIntent = new Intent(this, QuizActivity.class);
        quizIntent.putExtra(Constants.KEY_PLAY_AGAIN,true);
        quizIntent.putExtra(Constants.KEY_USER_NAME, Constants.USER_NAME);
        startActivity(quizIntent);
    }
    public void decideResultType(int result, int total){
        double percentage=((double)result/total)*100;
        Log.e("result",percentage+"");
        if(percentage>80){
            goodResult();
        }
        else{
            badResult();
        }
    }

    public void goodResult(){
        resultText.setTextColor((getResources().getColor(R.color.sGreen)));
    }

    public void badResult(){
        resultText.setTextColor((getResources().getColor(R.color.colorAccent)));
    }
}
