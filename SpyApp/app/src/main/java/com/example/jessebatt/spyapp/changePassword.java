package com.example.jessebatt.spyapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class changePassword extends AppCompatActivity {

    EditText name, oldPass, newPass;
    String Name, NewPassword, OldPassword;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        name = (EditText) findViewById(R.id.name);
        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
    }


    public void changePassword(View v){
        Name = name.getText().toString();
        NewPassword = newPass.getText().toString();
        OldPassword = oldPass.getText().toString();

        if (NewPassword.length() > 0 && name.length() > 0 && OldPassword.length() > 0 ) {
            BackGround b = new BackGround();
            b.execute(Name, NewPassword, OldPassword);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please fill in your details.", Toast.LENGTH_SHORT).show();
        }

    }


    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String NewPassword = params[1];
            String OldPassword = params[2];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.43.241/changePass.php");
                String urlParams = "name="+name+"&NewPassword="+NewPassword+"&OldPassword="+OldPassword;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
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
                s="Data updated successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }
}
