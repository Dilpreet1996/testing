package com.example.jss;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {
    private  Button tube1,buld1,plug1,television1,ipdd;
    private  TextView output;
    private OkHttpClient client;
    private   String Stube1,Sbuld1,Splug1,Stelevision1,address1,address;
    private final class EchoWebSocketListener extends WebSocketListener
    {
        //private static final int NORMAL_CLOSURE_STATUS=1000;
        @Override
        public void onOpen(final WebSocket webSocket, Response response) {
            webSocket.send("Dilpreet Singh");

            webSocket.send("How are you");
           webSocket.send(ByteString.decodeHex("deadbeef"));

           tube1.setOnClickListener(new View.OnClickListener()
           {
               @Override
               public void onClick(View v)
               {
                   if(Stube1.equals("off"))
                   {
                       webSocket.send("1");
                   }
                   if(Stube1.equals("on"))
                   {
                       webSocket.send("0");
                   }

               }
           });
           buld1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(Sbuld1.equals("off"))
                   {
                       webSocket.send("3");
                   }
                   if(Sbuld1.equals("on"))
                   {
                       webSocket.send("2");
                   }

               }
           });
           plug1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(Splug1.equals("off"))
                   {
                       webSocket.send("5");
                   }
                   if(Splug1.equals("on"))
                   {
                       webSocket.send("4");
                   }

               }
           });
           television1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(Stelevision1.equals("off"))
                   {
                       webSocket.send("7");
                   }
                   if(Stelevision1.equals("on"))
                   {
                       webSocket.send("6");
                   }
               }
           });


            //webSocket.close(NORMAL_CLOSURE_STATUS,"Goodbye !!!");


        }

        @Override
        public void onMessage(WebSocket webSocket, String text)
        {

            output("Receving : "+ text);
            try {

                JSONObject oo = new JSONObject(text.toString());






                JSONArray jsonArray = oo.getJSONArray("devices");
                for (int i = 0; i < jsonArray.length(); i++)
                {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Stube1 = jsonObject.getString("state1");
                    Sbuld1 = jsonObject.getString("state2");
                    Splug1 = jsonObject.getString("state3");
                    Stelevision1 = jsonObject.getString("state4");
                   tube1.setText(Stube1);
                   buld1.setText(Sbuld1);
                    plug1.setText(Splug1);
                    television1.setText(Stelevision1);

                   if (tube1.getText().toString().equals("off"))
                   {

                       tube1.setBackgroundColor(Color.RED);
                       tube1.setTextColor(Color.WHITE);
                   }
                   else
                   {
                       tube1.setBackgroundColor(Color.GREEN);
                       tube1.setTextColor(Color.BLACK);
                   }
                    if (buld1.getText().toString().equals("off"))
                    {

                        buld1.setBackgroundColor(Color.RED);
                        buld1.setTextColor(Color.WHITE);
                    }
                    else
                    {
                        buld1.setBackgroundColor(Color.GREEN);
                        buld1.setTextColor(Color.BLACK);
                    }
                    if (plug1.getText().toString().equals("off"))
                    {

                        plug1.setBackgroundColor(Color.RED);
                        plug1.setTextColor(Color.WHITE);
                    }
                    else
                    {
                        plug1.setBackgroundColor(Color.GREEN);
                        plug1.setTextColor(Color.BLACK);
                    }
                    if (television1.getText().toString().equals("off"))
                    {

                        television1.setBackgroundColor(Color.RED);
                        television1.setTextColor(Color.WHITE);
                    }
                    else
                    {
                        television1.setBackgroundColor(Color.GREEN);
                        television1.setTextColor(Color.BLACK);
                    }












                }











            } catch (Exception e)
            {
                //Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Reeving Bytes :"+ bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
           // webSocket.close(NORMAL_CLOSURE_STATUS,null);
            output("Closing :" + code +"/" + reason);

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("Error :" + t.getMessage());
            pass();


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tube1=findViewById(R.id.tube1);
        buld1=findViewById(R.id.bulb1);
        plug1=findViewById(R.id.plug1);
        television1=findViewById(R.id.tv1);
        output=findViewById(R.id.output);
        ipdd=findViewById(R.id.btn);


        client=new OkHttpClient();

        try {
            Bundle bundle=new Bundle();
            bundle=getIntent().getExtras();
            address=bundle.getString("ipaddress");



            address1="http://"+address;

        }
        catch (NullPointerException e)
        {
            address1="http://"+address;

            Toast.makeText(this, "Make sure Wheather Ip Addrres hello  is valid or not...", Toast.LENGTH_SHORT).show();
        }




        output.setMovementMethod(new ScrollingMovementMethod());

        Toast.makeText(this, ""+address1, Toast.LENGTH_SHORT).show();
        Request request=new Request.Builder().url(address1+":81").build();
        EchoWebSocketListener listener=new EchoWebSocketListener();
        WebSocket ws=client.newWebSocket(request,listener);
        client.dispatcher().executorService().shutdown();
        Toast.makeText(this, ""+ws.toString(), Toast.LENGTH_SHORT).show();











    }

    public void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setText(output.getText().toString() + "\n\n" + txt);
            }
        });
    }

    public void ipdd1(View view)
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    public void pass()
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

}
