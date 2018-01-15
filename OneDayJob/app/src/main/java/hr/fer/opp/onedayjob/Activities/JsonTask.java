package hr.fer.opp.onedayjob.Activities;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Josip Kalafatic on 1/15/2018.
 */

public class JsonTask extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            //URL url = new URL(params[0]);
            URL url = new URL("https://onedayjobapp2.azurewebsites.net/poruke?korisnikID1=2&&korisnikID2=3");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                return buffer.toString();

            }
            return null;
        } catch (Exception e) {

        }
        return null;

    }

        protected void onPostExecute(String str) {
            super.onPostExecute(str);


    }
}
