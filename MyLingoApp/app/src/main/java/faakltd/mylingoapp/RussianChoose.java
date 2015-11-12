package faakltd.mylingoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class RussianChoose extends ActionBarActivity {

    TextView welcome;
    Button alpha;
    Button common;
    Button quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russian_choose);


        Bundle extras = getIntent().getExtras();
        final String welcomeText = extras.getString("username");
        final String progress = extras.getString("progress");

        welcome = (TextView) findViewById(R.id.welcome15);

        welcome.setText(""+welcomeText);

        alpha = (Button) findViewById(R.id.russianAlphaBtn);
        common = (Button) findViewById(R.id.russianComBtn);
        quiz = (Button) findViewById(R.id.russianQuizBtn);


        alpha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent alpha = new Intent(RussianChoose.this, RussianAlph.class);
                alpha.putExtra("username", welcomeText);
                alpha.putExtra("progress", progress);
                startActivity(alpha);
            }
        });

        common.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent alpha = new Intent(RussianChoose.this, RussianCP.class);
                alpha.putExtra("username", welcomeText);
                alpha.putExtra("progress", progress);
                startActivity(alpha);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent alpha = new Intent(RussianChoose.this, Russian.class);
                alpha.putExtra("username", welcomeText);
                alpha.putExtra("progress", progress);
                startActivity(alpha);
            }
        });
    }






    public void russianQuiz(View view) {
        Intent i = new Intent(this, Russian.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_russian_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
