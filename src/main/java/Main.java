import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
        System.out.println(" 1. movie");
        System.out.println(" 2. actor/actress");
        System.out.println(" 3. Exit\n");

        System.out.print(">> Enter your choice: ");
        return sc.nextInt();
    }

    public static void movieMenu(){
        Movie movie = new Movie();
        Scanner sc = new Scanner(System.in);

        System.out.println("*************************");
        System.out.print(">> Enter the Movie Title: ");
        String movieTitle = sc.nextLine();
        movie.setAll(movie.getMovieData(movieTitle));

        System.out.println("*************************");
        System.out.println("Movie: " + movie.movieTitle + " (" + movie.year + ")");
        System.out.println(movie.rated);
        System.out.print("Genre: ");
        for (int i = 0; i < movie.genre.size(); i++) {
            if (i > 0)
                System.out.println(", ");
            System.out.print(movie.genre.get(i));
        }
        System.out.println("\nDirector: " + movie.director);
        System.out.print("Writer: ");
        for (int i = 0; i < movie.writer.size(); i++) {
            if (i > 0)
                System.out.println(", ");
            System.out.print(movie.writer.get(i));
        }
        System.out.print("Stars: ");
        for (int i = 0; i < movie.actorsList.size(); i++) {
            if (i > 0)
                System.out.println(", ");
            System.out.print(movie.writer.get(i));
        }
        System.out.println(" 3. Exit\n");
    }

    public static void actorMenu(){
        Actors actors = new Actors("",true);
    }
}