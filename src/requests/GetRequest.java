package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest {

    public int responseCode;
    public String readLine;
    private String text;
    public String longitude;
    public String latitude;
    public HttpURLConnection connection;
    private boolean postKey;

    public GetRequest() throws IOException {
        URL urlGetRequest = new URL("https://data.police.uk/api/crime-categories");
        // URL urlGetRequest = new URL("http://api.postcodes.io/postcodes/CV49BT");
        readLine = null;
        this.connection = (HttpURLConnection) urlGetRequest.openConnection();
        connection.setRequestMethod("GET");
        this.responseCode = connection.getResponseCode();
        getOutput();
    }

    public GetRequest(String date, String postcode) throws IOException {
        URL urlGetRequest = new URL("http://api.postcodes.io/postcodes/"+postcode);
        this.readLine = null;
        this.connection = (HttpURLConnection) urlGetRequest.openConnection();
        connection.setRequestMethod("GET");
        this.responseCode = connection.getResponseCode();
        this.postKey = true;
        getOutput();
        String[] latLong = getLatLong();
        getCrimeAtLocation(date, latLong[0], latLong[1]);
        getOutput();
    }


    public void getCrimeAtLocation(String date, String lat, String lng) throws IOException {
        URL urlGetRequest = new URL("https://data.police.uk/api/crimes-at-location?date="+date+"&lat="+lat+"&lng="+lng);
        this.readLine = null;
        this.connection = (HttpURLConnection) urlGetRequest.openConnection();
        connection.setRequestMethod("GET");
        this.responseCode = connection.getResponseCode();
    }


    public String[] getLatLong(){
        if (postKey) {
            longitude = this.text.substring(151, 160);
            latitude = this.text.substring(172, 180);
        }
        else{
            System.out.println("Request post code first");
        }
        postKey = false;
        return new String[] { latitude, longitude };
    }

    public void getOutput() throws IOException{
        if (this.responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((this.readLine = reader.readLine()) != null) {
                response.append(this.readLine);
            }
            reader.close();
            this.text = response.toString();;

        } else {
            System.out.println("GET NOT WORKED");
        }
    }

    public void display() {
        // print result
        System.out.println("Result:" + this.text);
    }


}

