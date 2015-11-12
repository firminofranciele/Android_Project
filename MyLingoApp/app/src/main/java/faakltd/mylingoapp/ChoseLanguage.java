package faakltd.mylingoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChoseLanguage extends ActionBarActivity {


    TextView welcome;
    Button portBtn;
    Button russianBtn;
    Button germanBtn;
    Button spanishBtn;
    Button profile;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_language);

        portBtn = (Button) findViewById(R.id.portBtn);
        russianBtn = (Button) findViewById(R.id.russianBtn);
        germanBtn = (Button) findViewById(R.id.germanBtn);
        spanishBtn = (Button) findViewById(R.id.spanishBtn);
        profile = (Button) findViewById(R.id.profile);
        logout = (Button) findViewById(R.id.button10);

        Bundle extras = getIntent().getExtras();
        final String welcomeText = extras.getString("username");
        final String progress = extras.getString("progress");

        welcome = (TextView) findViewById(R.id.textView9);

        welcome.setText(""+welcomeText);




        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent profile = new Intent(ChoseLanguage.this, LoginPage.class);
                profile.putExtra("username", welcomeText);
                startActivity(profile);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent profile1 = new Intent(ChoseLanguage.this, profile.class);
                profile1.putExtra("username", welcomeText);
                profile1.putExtra("progress", progress);
                startActivity(profile1);
            }
        });

        portBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent span = new Intent(ChoseLanguage.this, PortugueseChoose.class);
                span.putExtra("username", welcomeText);
                span.putExtra("progress", progress);
                startActivity(span);
            }
        });

        spanishBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent span = new Intent(ChoseLanguage.this, SpanishChoose.class);
                span.putExtra("username", welcomeText);
                span.putExtra("progress", progress);
                startActivity(span);
            }
        });

        russianBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent russian = new Intent(ChoseLanguage.this, RussianChoose.class);
                russian.putExtra("username", welcomeText);
                russian.putExtra("progress", progress);
                startActivity(russian);
            }
        });



        germanBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent german = new Intent(ChoseLanguage.this, GermanChoose.class);
                german.putExtra("username", welcomeText);
                german.putExtra("progress", progress);
                startActivity(german);
            }
        });



    }

    public void spanishLang(View view){

    }

    public void portugueseLang(View view){

    }

    public void russianLang(View view){


    }

    public void germanLang(View view){


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chose_language, menu);
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
