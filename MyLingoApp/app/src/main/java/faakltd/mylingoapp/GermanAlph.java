package faakltd.mylingoapp;

import java.util.Arrays;
import java.util.Locale;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import static faakltd.mylingoapp.R.id.welcome15;
import static faakltd.mylingoapp.R.id.welcome16;
import static faakltd.mylingoapp.R.id.welcome33;
//import static faakltd.mylingoapp.R.id.welcome7;


public class GermanAlph extends ActionBarActivity implements OnClickListener {
    TextToSpeech ttsObject;
    int result;
    TextView welcome;
    TextView phrases;
    Button next1;
    Button logout1;
    Button profile1;
    Button homepage;
    int count = 0;
    String textTranscript;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_german_alph);

        ttsObject = new TextToSpeech(GermanAlph.this, new TextToSpeech.OnInitListener() {
            Locale loc = new Locale("en");

            @Override
            public void onInit(int status) {




                //showing available locales
                Log.i("-------------", Arrays.toString(loc.getAvailableLocales()));




                if(status==TextToSpeech.SUCCESS){
                    result = ttsObject.setLanguage(Locale.UK);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Bundle extras = getIntent().getExtras();
        final String welcomeText = extras.getString("username");


        welcome = (TextView) findViewById(welcome16);
        phrases = (TextView) findViewById(welcome33);
        next1 = (Button) findViewById(R.id.commonNext2);
        logout1 = (Button) findViewById(R.id.buttonG);
        profile1 =(Button) findViewById(R.id.BtnG);
        homepage = (Button) findViewById(R.id.homeG);
        welcome.setText(""+welcomeText);

        getData();

        logout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent logout = new Intent(GermanAlph.this, MainActivity.class);

                startActivity(logout);

            }
        });
        profile1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent profile1 = new Intent(GermanAlph.this, profile.class);
                profile1.putExtra("username", welcomeText);
                startActivity(profile1);
            }
        });

        homepage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent homepage = new Intent(GermanAlph.this, ChoseLanguage.class);
                homepage.putExtra("username", welcomeText);
                startActivity(homepage);
            }
        });



    }

    public void getData() {


        String result = "";
        InputStream isr = null;


        InputStream is = null;
        //add paramaters using an arryList key value


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://lingo.hostei.com/getalpha_germ.php");

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
            Log.d("HTTP", "HTTP: OK");

        } catch (Exception e) {
            Log.e("HTTP", "Error in http connection " + e.toString());
        }

        // convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    isr, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            //reads line for line
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
            //phrases.setText("" + result);

        } catch (Exception e) {
            Log.e("log_tag", "Error  converting result " + e.toString());
        }


        //parse json data



        final String finalResult = result;
        next1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String s = "";
                    JSONArray jArray = new JSONArray(finalResult);


                    JSONObject json = jArray.getJSONObject(count);
                    s = s +json.getString("letter");
                    textTranscript = json.getString("transcript");





                    phrases.setText("" + s);

                    count++;
                    if (count >= jArray.length()) {
                        count = 0;

                    }



                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("log_tag", "Error Parsing Data " + e.toString());
                }

            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_german_alph, menu);
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

    @Override
    public void onClick(View v) {
        //String speak = textFromField;
        ttsObject.speak(textTranscript, TextToSpeech.QUEUE_FLUSH, null);

    }


    public void next(View v) {
        String speak = "Hello alan";
        ttsObject.speak(speak, TextToSpeech.QUEUE_FLUSH, null);

    }
}
