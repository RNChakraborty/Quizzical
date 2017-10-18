package ga.example.rncremote.quizzical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by rudraneel on 10/18/2017.
 */

public class ResultActivity extends AppCompatActivity {
    public static final String KEY_SCORE = "score";
    public static final String KEY_TOTAL = "total";
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int result = getIntent().getIntExtra(KEY_SCORE, 0);
        int total = getIntent().getIntExtra(KEY_TOTAL, 0);
        this.resultText = (TextView) findViewById(R.id.result_text);
        resultText.setText(result + "/" + total);

    }


}
