package faakltd.mylingoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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


import static faakltd.mylingoapp.R.id.textView5;
//import static faakltd.mylingoapp.R.id.welcome7;
import static faakltd.mylingoapp.R.id.welcome8;


public class PortugueseCP extends ActionBarActivity {

    TextView welcome;
    TextView phrases;
    Button next1;
    Button logout;
    Button homepage;
    Button profile;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portuguese_cp);



        next1 = (Button) findViewById(R.id.commonNext);
        logout = (Button) findViewById(R.id.logoutG);

        Bundle extras = getIntent().getExtras();
        final String welcomeText = extras.getString("username");


        welcome = (TextView) findViewById(welcome8);
        phrases = (TextView) findViewById(textView5);

        homepage = (Button) findViewById(R.id.homeG);
        profile = (Button) findViewById(R.id.profileG);


        welcome.setText("" + welcomeText);



        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent i = new Intent(PortugueseCP.this, MainActivity.class);

                startActivity(i);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent profile1 = new Intent(PortugueseCP.this, profile.class);
                profile1.putExtra("username", welcomeText);
                startActivity(profile1);
            }
        });

        homepage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent homepage = new Intent(PortugueseCP.this, ChoseLanguage.class);
                homepage.putExtra("username", welcomeText);
                startActivity(homepage);
            }
        });

getData();
    }

    public void getData() {



        String result = "";
        InputStream isr = null;



        InputStream is = null;
        //add paramaters using an arryList key value


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://lingo.hostei.com/getcommon.php");

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
                    s = s + "Phrase :"+json.getString("id")+


                        " \n\n Portuguese: \n"+json.getString("portuguese")+"\n\n English: \n"+json.getString("english");
                    phrases.setText("" + s);

                        count++;
                        if(count >= jArray.length()){
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
        getMenuInflater().inflate(R.menu.menu_portuguese_c, menu);
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
