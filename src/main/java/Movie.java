import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Movie {
    public static final String API_KEY = "55eb326";

    String movieTitle;
    int year;
    String releaseDate;
    String rated;
    String runtime; // Duration
    ArrayList<String> genre;
    String director;
    ArrayList<String> writer;
    ArrayList<String> actorsList; // Stars
    String plot;
    ArrayList<String> language;
    ArrayList<String> country;
    String awards;
    int imdbVotes;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        this.actorsList = actorsList;
        this.rating = rating;
        this.imdbVotes = ImdbVotes;
        this.movieTitle = this.releaseDate = this.rated = "";
        this.runtime = this.director = this.plot = this.awards = "";
        this.year = 0;
        this.writer = this.genre = this.language = this.country = new ArrayList<>();
    }
    public Movie(){
        this.movieTitle = this.releaseDate = this.rated = "";
        this.runtime = this.director = this.plot = this.awards = this.rating = "";
        this.imdbVotes = this.year = 0;
        this.writer = this.actorsList = this.genre = this.language = this.country = new ArrayList<>();
    }

    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */
    public String getMovieData(String title) throws IOException {
        try {
            URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY);
            URLConnection Url = url.openConnection();
            Url.setRequestProperty("Authorization", "Key" + API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        }
        catch (IOException e) {
            return null;
        }
    }

    public void setAll(String movieInfoJson){
        movieTitle = getMovieTitleViaApi(movieInfoJson);
        year = getMovieYearViaApi(movieInfoJson);
        releaseDate = getMovieReleaseDateViaApi(movieInfoJson);
        rated = getMovieRatedViaApi(movieInfoJson);
        runtime = getMovieRuntimeViaApi(movieInfoJson);
        genre = getMovieGenreViaApi(movieInfoJson);
        director = getMovieDirectorViaApi(movieInfoJson);
        writer = getMovieWriterViaApi(movieInfoJson);
        actorsList = getActorListViaApi(movieInfoJson);
        plot = getMoviePlotViaApi(movieInfoJson);
        language = getMovieLanguageViaApi(movieInfoJson);
        country = getMovieCountryViaApi(movieInfoJson);
        awards = getMovieAwardsViaApi(movieInfoJson);
        rating = getRatingViaApi(movieInfoJson);
        imdbVotes = getImdbVotesViaApi(movieInfoJson);
    }

    public boolean errorCheck(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getBoolean("Response");
    }

    public String getMovieTitleViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getString("Title");
    }

    public int getMovieYearViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getInt("Year");
    }

    public String getMovieReleaseDateViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getString("Released");
    }

    public String getMovieRatedViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getString("Rated");
    }

    public String getMovieRuntimeViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getString("Runtime");
    }

    public ArrayList<String> getMovieGenreViaApi(String movieInfoJson){
        ArrayList<String> movieGenre = new ArrayList<>();
        JSONObject obj = new JSONObject(movieInfoJson);
        String genreStr = obj.getString("Genre");
        for (int i = 0; i < genreStr.length(); i++){
            if (genreStr.charAt(i) == ','){
                movieGenre.add(genreStr.substring(0, i));
                genreStr = genreStr.substring(i+2);
            }
        }
        movieGenre.add(genreStr);
        return movieGenre;
    }

    public String getMovieDirectorViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getString("Director");
    }

    public ArrayList<String> getMovieWriterViaApi(String movieInfoJson){
        ArrayList<String> movieWriter = new ArrayList<>();
        JSONObject obj = new JSONObject(movieInfoJson);
        String writerStr = obj.getString("Writer");
        for (int i = 0; i < writerStr.length(); i++){
            if (writerStr.charAt(i) == ','){
                movieWriter.add(writerStr.substring(0, i));
                writerStr = writerStr.substring(i+2);
            }
        }
        movieWriter.add(writerStr);
        return movieWriter;
    }

    public ArrayList<String> getActorListViaApi(String movieInfoJson){
        ArrayList<String> movieActor = new ArrayList<>();
        JSONObject obj = new JSONObject(movieInfoJson);
        String actorStr = obj.getString("Actors");
        for (int i = 0; i < actorStr.length(); i++){
            if (actorStr.charAt(i) == ','){
                movieActor.add(actorStr.substring(0, i));
                actorStr = actorStr.substring(i+2);
                i = 0;
            }
        }
        movieActor.add(actorStr);
        return movieActor;
    }

    public String getMoviePlotViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getString("Plot");
    }

    public ArrayList<String> getMovieLanguageViaApi(String movieInfoJson){
        ArrayList<String> movieLanguage = new ArrayList<>();
        JSONObject obj = new JSONObject(movieInfoJson);
        String writerStr = obj.getString("Language");
        for (int i = 0; i < writerStr.length(); i++){
            if (writerStr.charAt(i) == ','){
                movieLanguage.add(writerStr.substring(0, i));
                writerStr = writerStr.substring(i+2);
            }
        }
        movieLanguage.add(writerStr);
        return movieLanguage;
    }

    public ArrayList<String> getMovieCountryViaApi(String movieInfoJson){
        ArrayList<String> movieCountry = new ArrayList<>();
        JSONObject obj = new JSONObject(movieInfoJson);
        String countryStr = obj.getString("Country");
        for (int i = 0; i < countryStr.length(); i++){
            if (countryStr.charAt(i) == ','){
                movieCountry.add(countryStr.substring(0, i));
                countryStr = countryStr.substring(i+2);
            }
        }
        movieCountry.add(countryStr);
        return movieCountry;
    }

    public String getMovieAwardsViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        return obj.getString("Awards");
    }

    public int getImdbVotesViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        String imdbVotesStr = obj.getString("imdbVotes");
        String imdbVotesStr_onlyDigits = "";
        for (int i = 0; i < imdbVotesStr.length(); i++){
            if (imdbVotesStr.charAt(i) != ',')
                imdbVotesStr_onlyDigits += imdbVotesStr.charAt(i);
        }
        return Integer.parseInt(imdbVotesStr_onlyDigits);
    }

    public String getRatingViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        JSONArray arr = obj.getJSONArray("Ratings");
        String rating = "";
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).getString("Source").equals("Internet Movie Database"))
                rating = arr.getJSONObject(i).getString("Value");
        }
        return rating;
    }
}