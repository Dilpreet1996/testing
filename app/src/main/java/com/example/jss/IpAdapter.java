package com.example.jss;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class IpAdapter extends RecyclerView.Adapter<IpAdapter.IpViewHolder>
{
    private Context mcontext;
    private Cursor mcursor;


    public IpAdapter(Context context,Cursor cursor)
    {
        mcontext=context;
        mcursor =cursor;
    }


    public IpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        View view=layoutInflater.inflate(R.layout.ipaddress,viewGroup,false);
        return new IpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IpViewHolder ipViewHolder, int i)
    {
        if(!mcursor.moveToPosition(i))
        {
            return;
        }
        String ipname=mcursor.getString(mcursor.getColumnIndex(ipcontent.ipcontentEntry.COLUMN_NAME));

        long id=mcursor.getLong(mcursor.getColumnIndex(ipcontent.ipcontentEntry._ID));



        ipViewHolder.textView.setText(ipname);
        ipViewHolder.itemView.setTag(id);


    }
    public int getItemCount()
    {

        return mcursor.getCount();


    }
    public void swapCursor(Cursor newCursor)
    {
        if(mcursor !=null)
        {
            mcursor.close();
        }
        mcursor=newCursor;
        if(newCursor !=null)
        {
            notifyDataSetChanged();
        }
    }



    public class IpViewHolder extends RecyclerView.ViewHolder
        {
            public TextView textView;

            public IpViewHolder(@NonNull View itemView)
            {
                super(itemView);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(v.getContext(),MainActivity.class);
                        intent.putExtra("ipaddress",textView.getText().toString());

                        v.getContext().startActivity(intent);



                        Toast.makeText(v.getContext(), ""+textView.getText(), Toast.LENGTH_SHORT).show();


                    }
                });
                textView=itemView.findViewById(R.id.re_id);

            }
        }

}
