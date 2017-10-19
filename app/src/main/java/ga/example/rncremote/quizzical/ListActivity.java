package ga.example.rncremote.quizzical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rudraneel on 10/19/2017.
 */

public class ListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list_view);

        List<String> strings = Arrays.asList("First", "Second","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third"
                ,"Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third","Third");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);
    }


}