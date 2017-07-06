package com.example.jessebatt.spyapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.sql.Types.NULL;


public class MainActivity extends AppCompatActivity {

    EditText name, password;
    String Name, Password;
    int count,memeCount = 0;
    Context ctx=this;
    String NAME=null, PASSWORD=null, EMAIL=null;
    Button regButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);

        regButton = (Button) findViewById(R.id.main_register);

        name.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(name.length() > 0 || password.length() > 0)
                    regButton.setEnabled(false); //disable send button if no text entered
                else
                    regButton.setEnabled(true);  //otherwise enable
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });

        password.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(password.length() > 0 || name.length() > 0)
                    regButton.setEnabled(false); //disable send button if no text entered
                else
                    regButton.setEnabled(true);  //otherwise enable
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });




    }

    public void main_register(View v){
        startActivity(new Intent(this,RegisterActivity.class));
    }


    public void main_button(View v){
    }

    public void main_login(View v){
        Name = name.getText().toString();
        Password = password.getText().toString();
        System.out.print(Name.length() );
        System.out.print(Password.length());
        //Toast.makeText(getApplicationContext(), Name.length(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), Password.length(), Toast.LENGTH_SHORT).show();


        if (password.length() > 0 && name.length() > 0) {
            BackGround b = new BackGround();
            b.execute(Name, Password);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please fill in your details.", Toast.LENGTH_SHORT).show();
        }
       // if (count >= 5){
       //    Toast.makeText(getApplicationContext(), count, Toast.LENGTH_SHORT).show();
        //}


    }
    public void changePass_(View v){
        startActivity(new Intent(this,changePassword.class));
    }

    public void playAudio(View v){
        memeCount++;
        if (memeCount == 5) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.meme);
            mp.start();

        }

    }


    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.43.241/login.php/");
                String urlParams = "name="+name+"&password="+password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                count = 0;

                while((tmp=is.read())!=-1) {
                    data += (char) tmp;
                    count++;
                    System.out.format("the word is, %s, the count is %d \n", data, count);
                }
                if (count == 15) {
                    //Toast.makeText(getApplicationContext(), "count was: "+count, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ctx, CameraAudio.class);
                    startActivity(i);
                } else if (count == 18) {
                    //Toast.makeText(getApplicationContext(), "Sorry please check you details.", Toast.LENGTH_SHORT).show();
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }


        }
        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
               // s="Login successfully.";
            }
            if(s.equals("Details Correct")) {
                //Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();

            }


        }
    }
}
