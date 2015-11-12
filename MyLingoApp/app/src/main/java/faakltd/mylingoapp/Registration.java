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


public class Registration extends ActionBarActivity {


    EditText Regfname;
    EditText Reglname;
    EditText Regemail;
    EditText Regpassword;
    Button RegBtn;
    TextView resultView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        StrictMode.enableDefaults(); // STRICT MODE ENABLED
        Regfname = (EditText) findViewById(R.id.Regfname);
        Reglname = (EditText) findViewById(R.id.Reglname);
        Regemail = (EditText) findViewById(R.id.Regemail);
        Regpassword = (EditText) findViewById(R.id.Regpassword);
        RegBtn = (Button) findViewById(R.id.RegBtn);
        RegBtn.setTextColor(Color.parseColor("#ffffff"));
        resultView = (TextView) findViewById(R.id.Regresult);

        RegBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // check for empty fields
                if (Regfname.getText().toString().matches("")
                        || Regemail.getText().toString().matches("")
                        || Regpassword.getText().toString().matches("")) {
                    Toast.makeText(Registration.this,
                            "This application requires all fields to be filled in",
                            Toast.LENGTH_LONG).show();


                } else {
                    postData();
                }


            }

            private void postData() {
                resultView.setText("working");
                String result = "";
                InputStream isr = null;

                String myfname;
                String mylname;
                String mymail;
                String mypassword;


                myfname = Regfname.getText().toString();
                mylname = Reglname.getText().toString();
                mymail = Regemail.getText().toString();
                mypassword = Regpassword.getText().toString();



                InputStream is = null;
                //add paramaters using an arryList key value
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("fname", myfname));
                nameValuePairs.add(new BasicNameValuePair("lname", mylname));
                nameValuePairs.add(new BasicNameValuePair("email", mymail));
                nameValuePairs.add(new BasicNameValuePair("password", mypassword));

                resultView.setText("working"+mypassword);

                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(
                            "http://lingo.hostei.com/newuser.php");
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



            }
        });




    }

}