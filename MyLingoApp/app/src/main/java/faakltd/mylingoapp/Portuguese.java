package faakltd.mylingoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static faakltd.mylingoapp.R.id.textView5;
import static faakltd.mylingoapp.R.id.welcome10;


public class Portuguese extends ActionBarActivity {

    TextView welcome;
    TextView score;
    TextView phrases;
    TextView option_a;
    TextView option_b;
    TextView option_c;
    ImageView wrong;
    ImageView right;


    TextView questions;
    Button next1;
    Button logout;
    Button option1;
    Button option2;
    Button homepage;
    Button profile;
    int count = 0;
    int scoring = 0;
    String welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portuguese);

        Bundle extras = getIntent().getExtras();
        final String welcomeText = extras.getString("username");
        final String progression = extras.getString("progress");

        next1 = (Button) findViewById(R.id.commonNext);
        logout = (Button) findViewById(R.id.logoutG);
        option1 = (Button)findViewById(R.id.chooseOne
        );
        option2 = (Button)findViewById(R.id.chooseTwo);

        welcome = (TextView) findViewById(welcome10);
        score = (TextView) findViewById(R.id.score);

        phrases = (TextView) findViewById(textView5);

        option_a = (TextView) findViewById(R.id.option_a);

        option_b = (TextView) findViewById(R.id.option_b);

        option_c = (TextView) findViewById(R.id.option_c);

        wrong = (ImageView) findViewById(R.id.wrong);
        right = (ImageView) findViewById(R.id.right);

        homepage = (Button) findViewById(R.id.homeG);
        profile = (Button) findViewById(R.id.profileG);

        wrong.setVisibility(View.GONE);
        right.setVisibility(View.GONE);

        option_c.setVisibility(View.GONE);

        welcome.setText(""+welcomeText);
        score.setText(""+progression);


        getData();

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent i = new Intent(Portuguese.this, MainActivity.class);

                startActivity(i);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent profile1 = new Intent(Portuguese.this, profile.class);
                profile1.putExtra("username", welcomeText);
                profile1.putExtra("progress", score.getText().toString());
                startActivity(profile1);
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
                    "http://lingo.hostei.com/getquiz_port.php");

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

        int addScore = Integer.parseInt(score.getText().toString());
        scoring = scoring+addScore;


        next1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                option1.setVisibility(View.VISIBLE);
                option2.setVisibility(View.VISIBLE);
                try {
                    String s = "";
                    String o1 = "";
                    String o2 = "";
                    String o3 = "";
                    JSONArray jArray = new JSONArray(finalResult);


                    JSONObject json = jArray.getJSONObject(count);
                    s = s +json.getString("question");
                    o1 = o1 +json.getString("option1");
                    o2 = o2 +json.getString("option2");
                    o3 = o3 +json.getString("right_answer");


                    phrases.setText("" + s);
                    option_a.setText("" + o1);
                    option_b.setText("" + o2);
                    option_c.setText("" + o3);

                    count++;
                    if (count >= jArray.length()) {
                        count = jArray.length();
                        phrases.setText("You have completed this section \n well done \n ");
                        next1.setVisibility(View.GONE);
                        option_a.setText(" ");
                        option_b.setText(" ");
                        right.setVisibility(View.GONE);
                        wrong.setVisibility(View.GONE);
                        option1.setVisibility(View.GONE);
                        option2.setVisibility(View.GONE);

                    }
                    right.setVisibility(View.GONE);
                    wrong.setVisibility(View.GONE);



                    option1.setOnClickListener(new View.OnClickListener() {


                        @Override
                        public void onClick(View v) {
                            wrong.setVisibility(View.GONE);
                            right.setVisibility(View.GONE);
                            System.out.println("Bla!!!!! " + option_b.getText());

                            if(option_a.getText().equals(option_c.getText())  ){
                                right.setVisibility(View.VISIBLE);

                                option1.setVisibility(View.GONE);
                                option2.setVisibility(View.GONE);


                                scoring = scoring+1;

                                score.setText(""+scoring);


                                String yourCount;

                                yourCount = score.getText().toString();
                                welcomeText = welcome.getText().toString();



                                InputStream is = null;

                                //add paramaters using an arryList key value
                                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                                nameValuePairs.add(new BasicNameValuePair("countVariable", yourCount));
                                nameValuePairs.add(new BasicNameValuePair("email", welcomeText));




                                try {
                                    HttpClient httpclient = new DefaultHttpClient();
                                    HttpPost httppost = new HttpPost(
                                            "http://lingo.hostei.com/newuser2.php");
                                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                                    HttpResponse response = httpclient.execute(httppost);
                                    HttpEntity entity = response.getEntity();

                                    Log.d("HTTP", "HTTP: OK");
                                    System.out.println("yes it was sent");

                                } catch (Exception e) {
                                    Log.e("HTTP", "Error in http connection " + e.toString());
                                }

                                System.out.println("yes");
                            }else{
                                wrong.setVisibility(View.VISIBLE);
                                System.out.println("nope");
                                option1.setVisibility(View.GONE);
                                option2.setVisibility(View.GONE);
                            }

                        }
                    });
                    option2.setOnClickListener(new View.OnClickListener() {


                        @Override
                        public void onClick(View v) {
                            right.setVisibility(View.GONE);
                            wrong.setVisibility(View.GONE);
                            if(option_b.getText().equals(option_c.getText())  ){
                                right.setVisibility(View.VISIBLE);
                                option1.setVisibility(View.GONE);
                                option2.setVisibility(View.GONE);


                                scoring = scoring+1;

                                score.setText(""+scoring);


                                String yourCount;

                                yourCount = score.getText().toString();
                                welcomeText = welcome.getText().toString();



                                InputStream is = null;

                                //add paramaters using an arryList key value
                                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                                nameValuePairs.add(new BasicNameValuePair("countVariable", yourCount));
                                nameValuePairs.add(new BasicNameValuePair("email", welcomeText));




                                try {
                                    HttpClient httpclient = new DefaultHttpClient();
                                    HttpPost httppost = new HttpPost(
                                            "http://lingo.hostei.com/newuser2.php");
                                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                                    HttpResponse response = httpclient.execute(httppost);
                                    HttpEntity entity = response.getEntity();

                                    Log.d("HTTP", "HTTP: OK");
                                    System.out.println("yes it was sent");

                                } catch (Exception e) {
                                    Log.e("HTTP", "Error in http connection " + e.toString());
                                }



                                System.out.println("yes");
                            }else{
                                wrong.setVisibility(View.VISIBLE);
                                System.out.println("nope");
                                option1.setVisibility(View.GONE);
                                option2.setVisibility(View.GONE);
                            }


                        }
                    });

                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("log_tag", "Error Parsing Data " + e.toString());
                }

            }

        });
        homepage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent homepage = new Intent(Portuguese.this, ChoseLanguage.class);
                homepage.putExtra("username", welcomeText);
                homepage.putExtra("progress", score.getText().toString());
                startActivity(homepage);
            }
        });

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_portuguese, menu);
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
