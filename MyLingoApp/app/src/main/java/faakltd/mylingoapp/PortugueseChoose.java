package faakltd.mylingoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PortugueseChoose extends ActionBarActivity {

    TextView welcome;
    Button alpha;
    Button common;
    Button quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portuguese_choose);

        Bundle extras = getIntent().getExtras();
        final String welcomeText = extras.getString("username");
        final String progress = extras.getString("progress");

        welcome = (TextView) findViewById(R.id.welcomePort);

        welcome.setText(""+welcomeText);

        alpha = (Button) findViewById(R.id.russianAlphaBtn);
        common = (Button) findViewById(R.id.russianComBtn);
        quiz = (Button) findViewById(R.id.portQuizBtn);





        alpha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent alpha = new Intent(PortugueseChoose.this, PortugueseAlph.class);
                alpha.putExtra("username", welcomeText);
                alpha.putExtra("progress", progress);
                startActivity(alpha);
            }
        });

        common.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent cp = new Intent(PortugueseChoose.this, PortugueseCP.class);
                cp.putExtra("username", welcomeText);
                cp.putExtra("progress", progress);
                startActivity(cp);
            }
        });


        quiz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent port1 = new Intent(PortugueseChoose.this, Portuguese.class);
                port1.putExtra("username", welcomeText);
                port1.putExtra("progress", progress);
                startActivity(port1);
            }
        });




    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_portuguese_choose, menu);
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
