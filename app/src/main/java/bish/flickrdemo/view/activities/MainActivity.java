package bish.flickrdemo.view.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bish.flickrdemo.R;
import bish.flickrdemo.controller.AppController;
import bish.flickrdemo.model.ImageInfo;
import bish.flickrdemo.view.adapters.MyGridAdapter;

public class MainActivity extends AppCompatActivity {

    private GridView myGridView;
    private MyGridAdapter myGridAdapter;
    private ArrayList<ImageInfo> imageInfoArrayList = new ArrayList<>();
    private String[] imageUrl;
    private ProgressDialog progressDialog;
    private final String TAG = MainActivity.class.getSimpleName();
    private final String URL = "https://api.flickr.com/services/rest/?" +
                                "method=flickr.people.getPublicPhotos&" +
                                "api_key=9f053a84fb6b5a182d8bb57486941cbe&" +
                                "user_id=143325745%40N05&" +
                                "format=json&nojsoncallback=1&" +
                                "auth_token=72157668362308672-023ec8e1d0997998&" +
                                "api_sig=566db951a3a954b4e1143781eed49673";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        myGridView = (GridView)findViewById(R.id.my_gridview);
        myGridAdapter = new MyGridAdapter(getApplicationContext(),imageInfoArrayList);
        myGridView.setAdapter(myGridAdapter);
        downloadImages();

    }

 /* SAMPLE JSON
    {
        photos: {
            page: 1,
                    pages: 1,
                    perpage: 100,
                    total: "100",
                    photo: [
            {
                id: "27115526926",
                    owner: "143325745@N05",
                    secret: "e09cc62fcc",
                    server: "7398",
                    farm: 8,
                    title: "Wispa's_Wallpapers_pack_050-058",
                    ispublic: 1,
                    isfriend: 0,
                    isfamily: 0
            },
            */
    private void downloadImages() {

        progressDialog.setMessage("Downloading images...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.length() + " : " + response.toString());
                try {
                    JSONObject myJsonObject = response.getJSONObject("photos");
                    JSONArray jsonArray = myJsonObject.getJSONArray("photo");
                    int jsonArraySize = jsonArray.length();
                    imageUrl = new String[jsonArraySize];

                    //Looping each json object
                    for(int i=0; i<jsonArraySize;i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        imageUrl[i] = buildFlickrImgUrl(jsonObject);

                        imageInfoArrayList.add(new ImageInfo(
                                jsonObject.getString("title"),
                                buildFlickrImgUrl(jsonObject)
                        ));
                        Log.d(TAG,"Json : "+jsonObject.toString());

                    }
                    //updating the gridview adapter
                    myGridAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Oops..Something Went Wrong!!!",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                progressDialog.cancel();
                // for (int i = 0;i<response.length();)
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                progressDialog.cancel();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private String buildFlickrImgUrl(JSONObject jsonObject) throws JSONException {
        String FARMID = jsonObject.getString("farm");
        String SERVERID = jsonObject.getString("server");
        String SECRET = jsonObject.getString("secret");
        String ID = jsonObject.getString("id");

        StringBuilder sb = new StringBuilder();

        sb.append("http://farm");
        sb.append(FARMID);
        sb.append(".static.flickr.com/");
        sb.append(SERVERID);
        sb.append("/");
        sb.append(ID);
        sb.append("_");
        sb.append(SECRET);
        sb.append("_n");
        sb.append(".jpg");

        return sb.toString();
}
    }
