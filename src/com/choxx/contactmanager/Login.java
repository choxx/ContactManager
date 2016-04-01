package com.choxx.contactmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Login extends Activity {

	EditText getPass;
	File sd;
	TextView forgot;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ActionBar actionBar = getActionBar();
		actionBar.setIcon(android.R.drawable.sym_action_chat);

		getPass=(EditText) findViewById(R.id.getPass);
		forgot=(TextView) findViewById(R.id.forgot);
		forgot.setTextColor(Color.BLUE);
		forgot.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(Login.this,ResetPassword.class);
				startActivity(i);
				finish();
				
			}
		});
	sd=Environment.getExternalStorageDirectory();
	}

public void check(View v)
{
	String tmp,pass;
	tmp=getPass.getText().toString();
	try {
		FileInputStream fis=new FileInputStream(sd+"/Contact Manager/.abcd");
		int a=0;
		pass="";
		while((a=fis.read())>-1)
		{
			pass+=(char)a;
		}
		if(pass.equals(tmp))
		{
			Intent i=new Intent(this,AfterRegActivity.class);
			startActivity(i);
			finish();
		}
		else
		{
			AlertDialog.Builder b=new AlertDialog.Builder(this);
			b.setTitle("ERROR");
			b.setMessage("Invalid Password!!! Try again..");
			b.show();
					// TODO: handle exception
						
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void exit(View v)
{
	
	finish();
}
	
}
