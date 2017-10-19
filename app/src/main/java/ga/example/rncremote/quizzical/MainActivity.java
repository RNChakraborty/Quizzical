package ga.example.rncremote.quizzical;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String userName;
    private EditText enterName;
    private TextView enterNameMSG;
    private Button enterBUtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        attachListrners();


    }


    public void init() {
        enterName = (EditText) findViewById(R.id.enter_name_edit_text);
        enterNameMSG = (TextView) findViewById(R.id.enter_name_msg_text);
        enterBUtton = (Button) findViewById(R.id.start_button);

    }

    public void forwardIntent() {
        Intent resultIntent = new Intent(this, ListActivity.class);
        resultIntent.putExtra(Constants.KEY_USER_NAME, Constants.USER_NAME);
        startActivity(resultIntent);
    }

    public void attachListrners() {

        enterBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.USER_NAME = enterName.getText().toString();
                writeToSharedPreference(Constants.USER_NAME);
                forwardIntent();
            }
        });

        enterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (enterName.getText().length() > 0) {
                    Log.e("bootcamp", "text enetered");
                    enterBUtton.setClickable(true);
                    enterBUtton.setEnabled(true);
                    enterNameMSG.setText("");

                } else {
                    enterBUtton.setClickable(false);
                    enterBUtton.setEnabled(false);
                    enterNameMSG.setText("* You have to enter a name !!! *");

                }
            }
        });

    }

    public void writeToSharedPreference(String userName) {
        Log.e("bootcamp writing ",userName);
        SharedPreferences sharedPref = this.getSharedPreferences(Constants.KEY_USER_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.KEY_USER_NAME, userName);
        editor.commit();

    }
}

