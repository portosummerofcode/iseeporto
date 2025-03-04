package antonio.iseeporto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Antonio on 12-09-2015.
 */
public class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

    ProgressDialog dialog;
    public String data;

    Activity act;

    public void setActivity(Activity temp)
    {
        act = temp;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(act);
        dialog.setMessage("Loading, please wait");
        dialog.setTitle("Connecting server");
        dialog.show();
        dialog.setCancelable(false);
    }

    @Override
    public Boolean doInBackground(String... urls) {
        try {

            //------------------>>
            HttpGet httppost = new HttpGet(urls[0]);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();
            Log.e("status", status+"");

            if (status == 200) {
                HttpEntity entity = response.getEntity();
                data = EntityUtils.toString(entity);

                return true;
            }

            //------------------>>

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void onPostExecute(Boolean result) {
        dialog.cancel();
        //notifyAll();
            /*adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();*/
    }
}