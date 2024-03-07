import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Actors {
    public static final String API_KEY = "6IWaNUdpksEOzgixmbXwqg==53K2K85E8nB3TfaK";

    String name;
    String netWorth;
    String gender;
    String nationality;
    ArrayList<String> occupation;
    Boolean isAlive;
    String dateOfDeath;

    public Actors(String netWorth, boolean isAlive){
        this.netWorth = netWorth;
        this.isAlive = isAlive;
        this.dateOfDeath = this.name = this.gender = this.nationality = "";
        this.occupation = new ArrayList<>();
    }

    public Actors(){
        this.dateOfDeath = this.netWorth = this.name = this.gender = this.nationality = "";
        this.isAlive = false;
        this.occupation = new ArrayList<>();
    }

    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name="+
                    name.replace(" ", "+"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        }
        catch (IOException e) {
            return null;
        }
    }

    public void setAll(String actorsInfoJson){
        try {
            name = getActorNameViaApi(actorsInfoJson);
            netWorth = Double.toString(getNetWorthViaApi(actorsInfoJson));
            gender = getActorGenderViaApi(actorsInfoJson);
            nationality = getActorNationalityViaApi(actorsInfoJson);
            occupation = getActorOccupationViaApi(actorsInfoJson);
            isAlive = isAlive(actorsInfoJson);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean errorCheck(String actorsInfoJson){
        return !actorsInfoJson.equals("[]");
    }

    public String getActorNameViaApi(String actorsInfoJson){
        JSONArray jArr = new JSONArray(actorsInfoJson);
        JSONObject obj = jArr.getJSONObject(0);
        return obj.getString("name");
    }

    public double getNetWorthViaApi(String actorsInfoJson){
        JSONArray jArr = new JSONArray(actorsInfoJson);
        JSONObject obj = jArr.getJSONObject(0);
        return obj.getDouble("net_worth");
    }

    public String getActorGenderViaApi(String actorsInfoJson){
        JSONArray jArr = new JSONArray(actorsInfoJson);
        JSONObject obj = jArr.getJSONObject(0);
        return obj.getString("gender");
    }

    public String getActorNationalityViaApi(String actorsInfoJson){
        JSONArray jArr = new JSONArray(actorsInfoJson);
        JSONObject obj = jArr.getJSONObject(0);
        return obj.getString("nationality");
    }

    public ArrayList<String> getActorOccupationViaApi(String actorsInfoJson){
        JSONArray jArr = new JSONArray(actorsInfoJson);
        JSONObject obj = jArr.getJSONObject(0);
        JSONArray jArrOccupations = obj.getJSONArray("occupation");
        ArrayList<String> occupationsList = new ArrayList<>();
        for (Object object : jArrOccupations){
            occupationsList.add(object.toString().replaceAll("_", " "));
        }
        return occupationsList;
    }

    public boolean isAlive(String actorsInfoJson){
        JSONArray jArr = new JSONArray(actorsInfoJson);
        JSONObject obj = jArr.getJSONObject(0);
        return obj.getBoolean("is_alive");
    }

    public String getDateOfDeathViaApi(String actorsInfoJson){
        JSONArray jArr = new JSONArray(actorsInfoJson);
        JSONObject obj = jArr.getJSONObject(0);
        this.dateOfDeath = obj.getString("death");
        return this.dateOfDeath;
    }

}