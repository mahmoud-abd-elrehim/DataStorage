package com.iti.mahmoud.datastorage;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Storage extends Activity {
    TextView text1;
    TextView text2;
    private Button PSsave;
    private  Button PSselect;
    private  Button IntSave;
    private  Button IntSelect;
    private  Button SqlSave;
    private Button SqlSelect;
    public static final String name = "Myname" ;
    public static final String Name = "name" ;
    public static final String Phone = "phone" ;

    User user=new User();
    private String file = "mydata";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        PSsave = (Button) findViewById(R.id.button);
        PSselect= (Button) findViewById(R.id.button2);
        IntSave=(Button) findViewById(R.id.button3);
        IntSelect=(Button) findViewById(R.id.button4);
        SqlSave=(Button)findViewById(R.id.button7);
        SqlSelect=(Button)findViewById(R.id.button8);
        text1= (TextView) findViewById(R.id.editText);
        text2= (TextView) findViewById(R.id.editText2);
        sharedpreferences = getSharedPreferences(name,0);

        PSsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String name=text1.getText().toString();;
                String phone= text2.getText().toString();;
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name,name);
                editor.putString(Phone, phone);
                editor.commit();
                text1.setText("");
                text2.setText("");


            }
        });
        PSselect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                SharedPreferences settings = getSharedPreferences(name, 0);
                String name= settings.getString(Name, String.valueOf(false));
                String phone= settings.getString(Phone, String.valueOf(true));
                Log.i("!!!!!!!!!!!!", name);
                Log.i("!!!!!!!!!!!!", phone);
                text1.setText(name);
                text2.setText(phone);


            }
        });

        IntSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String name=text1.getText().toString();;
                String phone= text2.getText().toString();
                try {
                    FileOutputStream fOut = openFileOutput(file,0);
                    fOut.write(name.getBytes());
                    fOut.write(",".getBytes());
                    fOut.write(phone.getBytes());
                    fOut.close();
                }
                catch (Exception e){


                }

                text1.setText("");
                text2.setText("");

            }
        });
        IntSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                try{
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String edittext1="";


                    while( (c = fin.read()) != -1){
                        edittext1 = edittext1 + Character.toString((char)c);

                    }
                    String[] name= edittext1.split(",");
                    Log.i("!!!!!!!!!!!!!!!", name[0]);
                    text1.setText( name[0]);
                    text2.setText( name[1]);
                    Toast.makeText(getBaseContext(),"file read", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                }



            }
        });

        SqlSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                String name=text1.getText().toString();
                String phone= text2.getText().toString();

                user.setName(name);
                user.setPhone(phone);
                /******
                 * my code
                 * ***/
                Helper help=new Helper(getApplicationContext());
                help.insertContact(user);
                text1.setText("");
                text2.setText("");

            }
        });
        SqlSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Helper help=new Helper(getApplicationContext());
                Cursor cr=help.getData(user.getName());
                cr.moveToFirst();
                text1.setText(cr.getString(0) );
                text2.setText(cr.getString(1));




            }
        });

    }
}
