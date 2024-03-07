import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        while (true) {
            switch (runMenu()){
                case 3:
                    return;
                case 1:
                    movieMenu();
                    break;
                case 2:
                    actorMenu();
                    break;
            }
        }
    }

    public static int runMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("*************************");
        System.out.println("CineScribe");
        System.out.println(" 1. Movie");
        System.out.println(" 2. Actor/Actress");
        System.out.println(" 3. Exit\n");

        System.out.print(">> Enter your choice: ");
        return sc.nextInt();
    }

    public static void movieMenu() throws IOException {
        Movie movie = new Movie();
        Scanner sc = new Scanner(System.in);

        System.out.println("*************************");
        System.out.print(">> Enter the Movie Title: ");
        String movieTitle = sc.nextLine();
        String movieData = movie.getMovieData(movieTitle);

        if (movieData == null){
            System.out.println("Error: Could not connect to api!");
            return;
        }
        else if (!movie.errorCheck(movieData)) {
            System.out.println("Error: Movie not found!");
            return;
        }

        movie.setAll(movieData);

        System.out.println("*************************");
        System.out.println("Movie: " + movie.movieTitle + " (" + movie.year + ") - " + movie.rated);
        System.out.print("Genre: ");
        for (int i = 0; i < movie.genre.size(); i++) {
            if (i > 0)
                System.out.print(", ");
            System.out.print(movie.genre.get(i));
        }
        System.out.println("\nDirector: " + movie.director);
        System.out.print("Writer: ");
        for (int i = 0; i < movie.writer.size(); i++) {
            if (i > 0)
                System.out.print(", ");
            System.out.print(movie.writer.get(i));
        }
        System.out.print("\nStars: ");
        for (int i = 0; i < movie.actorsList.size(); i++) {
            if (i > 0)
                System.out.print(", ");
            System.out.print(movie.actorsList.get(i));
        }
        System.out.println("\n" + movie.plot);
        System.out.println("Release Date: " + movie.releaseDate);
        System.out.println("Duration: " + movie.runtime);
        System.out.println("Internet Movie Database Rating: " + movie.rating);
        System.out.println("IMDB Votes: " + movie.imdbVotes);
        System.out.print("Language: ");
        for (int i = 0; i < movie.language.size(); i++) {
            if (i > 0)
                System.out.print(", ");
            System.out.print(movie.language.get(i));
        }
        System.out.print("\nCountry: ");
        for (int i = 0; i < movie.country.size(); i++) {
            if (i > 0)
                System.out.print(", ");
            System.out.print(movie.country.get(i));
        }
        System.out.println("\nAwards: " + movie.awards);
        System.out.println("\nWould you like to know more about the cast? (Enter the desired number. Also, enter b to return to the main menu.)");
        for (int i = 0; i < movie.actorsList.size(); i++) {
            System.out.print(" ");
            System.out.print(i+1);
            System.out.print(". " + movie.actorsList.get(i) + "\n");
        }
        System.out.print(">> ");
        String str = sc.next();
        if (str.equals("b")){
            return;
        }

        Actors actor = new Actors();
        String actorData = actor.getActorData(movie.actorsList.get(Integer.parseInt(str)-1));

        if (actorData == null){
            System.out.println("Error: Could not connect to api!");
            return;
        }
        else if (!actor.errorCheck(actorData)) {
            System.out.println("Error: Actor not found!");
            return;
        }

        actor.setAll(actorData);
        if (!actor.isAlive) {
            String string = actor.getDateOfDeathViaApi(actorData);
        }

        System.out.println("*************************");
        System.out.print("Name: " + actor.name);
        if (actor.isAlive)
            System.out.print(" (Alive)\n");
        else
            System.out.print(" (Passed Away)\n");
        System.out.println("Gender: " + actor.gender);
        System.out.println("Nationality: " + actor.nationality);
        System.out.println("Net Worth: " + new BigDecimal(Double.parseDouble(actor.netWorth)).toPlainString());
        System.out.print("Occupation: ");
        for (int i = 0; i < actor.occupation.size(); i++){
            if (i > 0)
                System.out.print(", ");
            System.out.print(actor.occupation.get(i));
        }
        System.out.print("\n");
        if (!actor.isAlive)
            System.out.println("Date Of Death: " + actor.dateOfDeath + "\n");
        System.out.println("(Enter any character to return to the main menu.)");
        System.out.print(">>");
        sc.next();
    }

    public static void actorMenu(){
        Actors actor = new Actors();
        Scanner sc = new Scanner(System.in);

        System.out.println("*************************");
        System.out.print(">> Enter the Actor Name: ");
        String actorName = sc.nextLine();
        String actorData = actor.getActorData(actorName);

        if (actorData == null){
            System.out.println("Error: Could not connect to api!");
            return;
        }
        else if (!actor.errorCheck(actorData)) {
            System.out.println("Error: Actor not found!");
            return;
        }

        actor.setAll(actorData);
        if (!actor.isAlive) {
            String str = actor.getDateOfDeathViaApi(actorData);
        }

        System.out.println("*************************");
        System.out.print("Name: " + actor.name);
        if (actor.isAlive)
            System.out.print(" (Alive)\n");
        else
            System.out.print(" (Passed Away)\n");
        System.out.println("Gender: " + actor.gender);
        System.out.println("Nationality: " + actor.nationality);
        System.out.println("Net Worth: " + new BigDecimal(Double.parseDouble(actor.netWorth)).toPlainString());
        System.out.print("Occupation: ");
        for (int i = 0; i < actor.occupation.size(); i++){
            if (i > 0)
                System.out.print(", ");
            System.out.print(actor.occupation.get(i));
        }
        System.out.print("\n");
        if (!actor.isAlive)
            System.out.println("Date Of Death: " + actor.dateOfDeath + "\n");
        System.out.println("(Enter any character to return to the main menu.)");
        System.out.print(">>");
        sc.next();
    }
}