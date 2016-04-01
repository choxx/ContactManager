package com.choxx.contactmanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ResetPassword extends Activity {

	Button but,reset;
	FileInputStream sec,eidname;
	String temp1="",temp2="";
	EditText secAnswer,eid,newpass;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_reset_password);
		ActionBar actionbar=getActionBar();
		actionbar.setTitle("Reset Password");
		actionbar.setIcon(android.R.drawable.edit_text);

		eid=(EditText) findViewById(R.id.emailid);
		newpass=(EditText) findViewById(R.id.newpass);
		but=(Button) findViewById(R.id.resetSubmit);
		reset=(Button) findViewById(R.id.reset);
		secAnswer=(EditText) findViewById(R.id.secAnswer);
		newpass.setVisibility(View.INVISIBLE);
		reset.setVisibility(View.INVISIBLE);

	}

	public void validate(View v)
	{
		
		
		String a="",b="";
		a=eid.getText().toString();
		b=secAnswer.getText().toString();
		int aa,bb;
		try {
			eidname=new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Contact Manager/.eid");
			sec=new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Contact Manager/.security");
			
			while((aa=sec.read())>-1)
			{
				temp2+=(char)aa;
				
			}
			while((bb=eidname.read())>-1)
			{
				temp1+=(char)bb;
				
			}
			
			if((temp1.equals(a))&&(temp2.equals(b)))
			{
				
				eid.setVisibility(View.INVISIBLE);
				secAnswer.setVisibility(View.INVISIBLE);
				but.setVisibility(View.INVISIBLE);
				newpass.setVisibility(View.VISIBLE);
				reset.setVisibility(View.VISIBLE);
				Toast.makeText(this, "Email id && nick name matched, enter new password..", 2000).show();
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void reset(View v)
	{
	String n=newpass.getText().toString();
	FileOutputStream fos;
	//Toast.makeText(this, "1", 2000).show();
	try {
		eidname=new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Contact Manager/.eid");
		int a;
		//Toast.makeText(this, "2", 2000).show();
		FileOutputStream foss=new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Contact Manager/.abcd");
		foss.write(n.getBytes());
		foss.flush();
		foss.close();

		//Toast.makeText(this, "3", 2000).show();
		
		eidname.close();
		sec.close();
		AlertDialog.Builder b=new AlertDialog.Builder(this);
		b.setTitle("MESSAGE");
		b.setMessage("Password Changed..");
		//Thread.sleep(5000);
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			
			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
		
			
				startActivity(new Intent(ResetPassword.this,AfterRegActivity.class));
			}
		});
		b.show();
		
		//finish();
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//startActivity(new Intent(this,AfterRegActivity.class));
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(this,AfterRegActivity.class));
	}
	
	
}
