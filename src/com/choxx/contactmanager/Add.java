package com.choxx.contactmanager;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Add extends Activity {

	EditText fname,lname,office,home,addr,emailid,org,note;
	SQLiteDatabase db;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		//lname=(EditText) findViewById(R.id.lname);
		ActionBar actionbar=getActionBar();
		actionbar.setTitle("Add New");
		actionbar.setIcon(android.R.drawable.btn_plus);

		home=(EditText) findViewById(R.id.home);
		office=(EditText) findViewById(R.id.office);
		addr=(EditText) findViewById(R.id.addr);
		emailid=(EditText) findViewById(R.id.emailid);
		org=(EditText) findViewById(R.id.org);
		fname=(EditText) findViewById(R.id.fname);
		note=(EditText) findViewById(R.id.note);
	
		db=openOrCreateDatabase("cmdb", MODE_PRIVATE, null);
		
			db.execSQL("create table if not exists addcontact (fname char(15),lname char(15),home int(11) primary key,office int(11) ,addr varchar(40),emailid varchar(20),"
		
				+"org char(20),note varchar(50) )");
		

	}// oncreate() closed

	public void save(View v)
	{
		
		String f=fname.getText().toString();
		String l="";
		String h=home.getText().toString().trim();
		String n=note.getText().toString().trim();
		String o=office.getText().toString().trim();
		
		String a=addr.getText().toString();
		String e=emailid.getText().toString();
		String or=org.getText().toString();
		
		try {
			
			db.execSQL("insert into addcontact values('"+f+"','"+l+"','"+h+"','"+o+"','"+a+"','"+e+"','"+or+"','"+n+"')");
			Toast.makeText(this,"Contact Added successfully",Toast.LENGTH_LONG).show();
		} catch (Exception e2) {
			Toast.makeText(this,"Same Home Number Contact already exists.",Toast.LENGTH_LONG).show();
		}
		
		
		startActivity(new Intent(this,AfterRegActivity.class));
		
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(this,AfterRegActivity.class));
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	finish();
	
	}
	
}// Add class closed
