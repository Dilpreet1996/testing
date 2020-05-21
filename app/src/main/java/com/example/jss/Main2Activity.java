package com.example.jss;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
{
    private SQLiteDatabase mdaSqLiteDatabase;
    private IpAdapter miIpAdapter;
    EditText editText;
    Button button;
    String ip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        IPDBHelper ipdbHelper =new IPDBHelper(this);
        mdaSqLiteDatabase=ipdbHelper.getWritableDatabase();
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        miIpAdapter=new IpAdapter(this,getAllItems());
        recyclerView.setAdapter(miIpAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i)
            {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);



        editText=findViewById(R.id.ip);
        button=findViewById(R.id.btn);
        Toast.makeText(this, "Mention full Link", Toast.LENGTH_LONG).show();
    }

    public void click(View view)
    {
        ip=editText.getText().toString();
        if(ip.toString().trim().length()==0)
        {
            return;
        }



        ContentValues contentValues=new ContentValues();
        contentValues.put(ipcontent.ipcontentEntry.COLUMN_NAME, ip);
        mdaSqLiteDatabase.insert(ipcontent.ipcontentEntry.TABLE_NAME, null,contentValues);
        miIpAdapter.swapCursor(getAllItems());
        editText.getText().clear();






    }

    private void removeItem(long id)
    {
        mdaSqLiteDatabase.delete(ipcontent.ipcontentEntry.TABLE_NAME, ipcontent.ipcontentEntry._ID + "=" + id, null);
        miIpAdapter.swapCursor(getAllItems());
    }
    private Cursor getAllItems()
    {
        return  mdaSqLiteDatabase.query(ipcontent.ipcontentEntry.TABLE_NAME, null , null,null,null,null, ipcontent.ipcontentEntry.COLUMN_TIMESTAMP + " DESC" );

    }

}
