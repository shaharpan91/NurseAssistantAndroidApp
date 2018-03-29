package com.g4bootcamp.nurseassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText ETlogin, ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        ETlogin = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);

    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop(){
        super.onStop();
    }


    protected void signIn(){

        StringRequest request = new StringRequest(Request.Method.POST, Resourses.URLlogin, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                if (!s.equals("false")){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Resourses.setUsername(ETlogin.getText().toString());
                    Resourses.setPassword(ETpassword.getText().toString());
                    requestList();
                    Log.d("login", s);
                    try {
                        Resourses.loadNurseName(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "Incorrect details", Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(LoginActivity.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("username", ETlogin.getText().toString());
                parameters.put("password", ETpassword.getText().toString());
                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);

    }

    public void requestList() {

        StringRequest request = new StringRequest(Request.Method.POST, Resourses.URLgetlist, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                try {
                    Resourses.loadMainMap(s);
                    Resourses.getPatientList();
                    Toast.makeText(LoginActivity.this,"List loaded successfully", Toast.LENGTH_SHORT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                goToMainActivity();

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(LoginActivity.this, "Some error occurred -> " + volleyError, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("uname", ETlogin.getText().toString());
                parameters.put("pwd", ETpassword.getText().toString());
                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);

    }





    protected void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_sign_in:

                    signIn();
                    //goToMainActivity();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
