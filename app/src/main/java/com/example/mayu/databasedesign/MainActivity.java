package com.example.mayu.databasedesign;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 Author Mayank Mohit (Mayu)
 github= "https://github.com/Mayu001"
 */
//Check check
public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editlastName,editMarks,editID,editiddelete;
    Button btnAddDAta,btnViewAll,btnUpdateData,btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DatabaseHelper(this);

        editMarks=(EditText)findViewById(R.id.marks);
        editName=(EditText)findViewById(R.id.firstName);
        editlastName=(EditText)findViewById(R.id.lastName);
        editID=(EditText)findViewById(R.id.userId);
        editiddelete=(EditText)findViewById(R.id.idfordelete);
        btnAddDAta=(Button)findViewById(R.id.btnadd);
        btnViewAll=(Button)findViewById(R.id.btnView);
        btnUpdateData=(Button)findViewById(R.id.btnupdate);
        btnDeleteData=(Button)findViewById(R.id.btnDelete);

        btnAddDAta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted= myDb.insertData(editName.getText().toString(),editlastName.getText().toString(),editMarks.getText().toString());
                if(isInserted)
                    Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=myDb.getAllData();
                if(cursor.getCount()==0)
                {
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(cursor.moveToNext())
                {
                    buffer.append("ID:"+cursor.getString(0)+"\n");
                    buffer.append("FIRST NAME:"+cursor.getString(1)+"\n");
                    buffer.append("LAST NAME:"+cursor.getString(2)+"\n");
                    buffer.append("MARKS:"+cursor.getString(3)+"\n\n");
                }

                showMessage("Data",buffer.toString());
            }
        });


        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated=myDb.updateData(editID.getText().toString(),editName.getText().toString(),editlastName.getText().toString(),editMarks.getText().toString());
                if(isUpdated)
                    Toast.makeText(getApplicationContext(),"Data updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Data Not updated",Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRows=myDb.deleteData(editiddelete.getText().toString());
                if(deletedRows>0)
                    Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Data Not deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
