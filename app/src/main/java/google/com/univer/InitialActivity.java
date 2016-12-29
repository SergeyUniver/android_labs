package google.com.univer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        initView();
    }

    private void initView() {
        Button sortByAlp = (Button) findViewById(R.id.sorted_by_alphavit_button);
        sortByAlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                intent.setAction(MainActivity.ALPHAVIT_SORT_ACTION);
                startActivity(intent);
            }
        });

        Button sortByAcs = (Button) findViewById(R.id.sorted_by_ascending_button);
        sortByAcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                intent.setAction(MainActivity.ASCENDING_SORT_ACTION);
                startActivity(intent);
            }
        });

        Button sortByDes = (Button) findViewById(R.id.sorted_by_descending_button);
        sortByDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                intent.setAction(MainActivity.DESCENDING_SORT_ACTION);
                startActivity(intent);
            }
        });
    }

}
