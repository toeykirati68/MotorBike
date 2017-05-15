package com.example.toey_kirati.motorbike;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private BikeTABLE objBikeTABLE;
    private OrderTABLE objOrderTABLE;
    private UserTABLE objUserTABLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectedSQLite();//เชื่อมต่อกับ SQLite

        synJSONtoSQLite();
    }
    private void connectedSQLite(){
        objUserTABLE = new UserTABLE(this);
        objBikeTABLE = new BikeTABLE(this);
        objOrderTABLE = new OrderTABLE(this);
    }
    private void synJSONtoSQLite() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // loop2time
        int intTimes = 0;
        while (intTimes <=1) {
            //variable
            InputStream objInputStream = null;
            String strJSON = null;
            String strUserURL = "http://5711020660068.sci.dusit.ac.th/UserTABLE.php";
            String strBikeURL = "http://5711020660068.sci.dusit.ac.th/BikeTABLE.php";
            String strOrderURL = "http://5711020660068.sci.dusit.ac.th/OrderTABLE.php";
            HttpPost objHttpPost;

            // create inputstream
            try {
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 0:
                        objHttpPost = new HttpPost(strUserURL);
                        break;
                    case 1:
                        objHttpPost = new HttpPost(strBikeURL);
                        break;
                    default:
                        objHttpPost = new HttpPost(strOrderURL);
                        break;
                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
            }catch (Exception e){
                Log.d("MotorBike","InputStream ==>"+e.toString());
            }

            //create strJSON
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(objInputStream,"UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String strLine = null;
                while ((strLine = bufferedReader.readLine()) !=null){
                    stringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = stringBuilder.toString();
            }catch (Exception e){
                Log.d("MotorBike","strJSON ==>"+e.toString());
            }

            //update SQLite
            try{
                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    switch (intTimes){
                        case 0:
                            String strUser = jsonObject.getString("User");
                            String strPassword = jsonObject.getString("Password");
                            String strName = jsonObject.getString("Name") ;
                            objUserTABLE.addNewUser(strUser,strPassword,strName);
                            break;
                        case 1:
                            //update drinktable
                            // String striddrink = jsonObject.getString("ID");
                            String strBike = jsonObject.getString("Bike");

                            String strprice = jsonObject.getString("Price");
                            objBikeTABLE.addNewBike(strBike,strprice);
                            break;
                        default:
                            // update ordertable
                            // String stridorder = jsonObject.getString("ID_Order");

                            String strbil = jsonObject.getString("Bike");
                            String stritem = jsonObject.getString("Item");
                            String strofficer = jsonObject.getString("Officer");

                            objOrderTABLE.addNewOrder(strbil, stritem, strofficer);
                            break;
                    }
                }
            }catch (Exception e){
                Log.d("MotorBike","Update SQLite ==>"+e.toString());
            }
            //increase inttime
            intTimes += 1;
        }
    }// synjson


    public void clicklogout (View view){
        Intent intent = new Intent(MainActivity.this,YourName.class);
        startActivity(intent);
    }
    public void clickmenubike (View view){
        Intent intent = new Intent(MainActivity.this,MenuBike.class);
        startActivity(intent);
    }
}
