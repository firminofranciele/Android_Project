package faakltd.mylingoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginPage extends ActionBarActivity {
    private static final int LENGTH_SHORT = 0;
    /**
     * Called when the activity is first created.
     */

    TextView resultView;
    TextView resultView2;
    TextView text3;
    EditText emailText;
    EditText passwordText;
    Button subBtn;
    Button continueBtn;
    String progress;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        StrictMode.enableDefaults(); // STRICT MODE ENABLED

        resultView = (TextView) findViewById(R.id.result);
        emailText = (EditText) findViewById(R.id.emailText2);
        passwordText = (EditText) findViewById(R.id.passwordText);
        subBtn = (Button) findViewById(R.id.submit);
        subBtn.setTextColor(Color.parseColor("#ffffff"));
        continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setVisibility(View.GONE);
        continueBtn.setTextColor(Color.parseColor("#ffffff"));

        text3 = (TextView) findViewById(R.id.myText);



        subBtn.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View v) {
                // check for empty fields
                if (passwordText.getText().toString().matches("")
                        || emailText.getText().toString().matches("")) {
                    Toast.makeText(LoginPage.this,
                            "This application needs both email and password",
                            Toast.LENGTH_LONG).show();

                } else {
                    getData();
                }

				/*
				 * Intent welcome = new Intent(MainActivity.this,Welcome.class);
				 * welcome.putExtra("username", emailText.getText().toString());
				 * startActivity(welcome);
				 */

            }
        });

    }

    public void getData() {

        String result = "";
        InputStream isr = null;

        String mymail;
        String mypassword;
        mymail = emailText.getText().toString();
        mypassword = passwordText.getText().toString();

        InputStream is = null;
        //add paramaters using an arryList key value
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("email", mymail));
        nameValuePairs.add(new BasicNameValuePair("password", mypassword));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://lingo.hostei.com/welcome.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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
            resultView.setText("" + result);

        } catch (Exception e) {
            Log.e("log_tag", "Error  converting result " + e.toString());
        }


        //parse json data
        try {
            String s = "";
            JSONArray jArray = new JSONArray(result);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                s = s +
                        "Name : " + json.getString("fname") + " " + json.getString("lname") + "\n" +
                        "email : " + json.getString("email") + "\n\n" +
                        "Progressed to level : " + json.getString("progress") + "\n  \n";


                progress = json.getString("progress");

            }
            if (s.matches("")) {
                resultView.setText("No user found \n" + s);
                passwordText.setText("");
                emailText.setText("");


            } else {
                resultView.setText("Welcome \n" + s);
                passwordText.setText("");
                passwordText.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);


                subBtn.setText("Your logged in");
                subBtn.setVisibility(View.GONE);
                continueBtn.setVisibility(View.VISIBLE);

                //hide keyboard...
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(passwordText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // click to continue
                continueBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        Intent welcome = new Intent(LoginPage.this, ChoseLanguage.class);
                        welcome.putExtra("username", emailText.getText().toString());
                        welcome.putExtra("progress", progress);

                        startActivity(welcome);

                    }
                });

            }

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error Parsing Data " + e.toString());
        }


    }
}

