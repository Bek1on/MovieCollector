
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter a actor: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        ArrayList<String> actors = new ArrayList<String>();
        for(int i = 0; i < movies.size();i++)
        {
            String alphaCast = movies.get(i).getCast().toLowerCase();
            String[] listo = alphaCast.split("\\|");
            for(int d = 0; d < listo.length;d++)
            {
                boolean canAdd = true;
                for(int w = 0; w < actors.size();w++)
                {
                    if(actors.get(w).contains(listo[d]))
                    {
                        canAdd = false;
                    }
                }
                if(canAdd && listo[d].contains(searchTerm))
                {
                    actors.add(listo[d]);
                }
            }
        }
        Sort.selectionSortWordList(actors);
        int counter = 1;
        for(int i = 0; i < actors.size();i++)
        {
            System.out.println( counter + ". " + actors.get(i));
            counter++;
        }
        System.out.println("Which actor's movie would you like to view?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String selectedActor = actors.get(choice - 1);
        ArrayList<Movie> selected = new ArrayList<Movie>();
        for(int i = 0; i < movies.size();i++)
        {
            if(movies.get(i).getCast().toLowerCase().contains(selectedActor))
            {
                selected.add(movies.get(i));
            }
        }
        int county = 1;
        for(int i = 0; i < selected.size();i++)
        {
            System.out.println(county + ". " + selected.get(i).getTitle());
            county++;
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = selected.get(choice2 - 1);

        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keyword = movies.get(i).getKeywords();
            keyword = keyword.toLowerCase();

            if (keyword.contains(searchTerm))
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        ArrayList<String> genres = new ArrayList<String>();
        for(int i = 0; i < movies.size();i++)
        {
            String alphaCast = movies.get(i).getGenres().toLowerCase();
            String[] listo = alphaCast.split("\\|");
            for(int d = 0; d < listo.length;d++)
            {
                boolean canAdd = true;
                for(int w = 0; w < genres.size();w++)
                {
                    if(genres.get(w).contains(listo[d]))
                    {
                        canAdd = false;
                    }
                }
                if(canAdd)
                {
                    genres.add(listo[d]);
                }
            }
        }
        Sort.selectionSortWordList(genres);
        int counter = 1;
        for(int i = 0; i < genres.size();i++)
        {
            System.out.println( counter + ". " + genres.get(i));
            counter++;
        }
        System.out.println("Which genre would you like to view?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String selectedGenre = genres.get(choice - 1);
        ArrayList<Movie> selected = new ArrayList<Movie>();
        for(int i = 0; i < movies.size();i++)
        {
            if(movies.get(i).getGenres().toLowerCase().contains(selectedGenre))
            {
                selected.add(movies.get(i));
            }
        }
        int county = 1;
        for(int i = 0; i < selected.size();i++)
        {
            System.out.println(county + ". " + selected.get(i).getTitle());
            county++;
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = selected.get(choice2 - 1);

        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {
        ArrayList<Movie> selected = new ArrayList<Movie>();
        ArrayList<Movie> checker = new ArrayList<Movie>();
        for(int i = 0; i < movies.size();i++)
        {
            checker.add(movies.get(i));
        }
        for(int max = 0; max < 51;max++)
        {
            Movie biggest = checker.get(0);
            for(int i = 1; i < checker.size();i++)
            {
                if(biggest.getUserRating() < checker.get(i).getUserRating())
                {
                    biggest = checker.get(i);
                    checker.remove(i);
                    i--;
                }
            }
            selected.add(biggest);
        }
        for(int i = 0; i < 50;i++)
        {
            System.out.println((i+1) + ". " + selected.get(i).getTitle() + ": " + selected.get(i).getUserRating());
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = selected.get(choice2 - 1);
        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue()
    {
        ArrayList<Movie> selected = new ArrayList<Movie>();
        ArrayList<Movie> checker = new ArrayList<Movie>();
        for(int i = 0; i < movies.size();i++)
        {
            checker.add(movies.get(i));
        }
        for(int max = 0; max < 51;max++)
        {
            Movie biggest = checker.get(0);
            for(int i = 0; i < checker.size();i++)
            {
                if(biggest.getRevenue() < checker.get(i).getRevenue())
                {
                    biggest = checker.get(i);
                    checker.remove(i);
                    i--;
                }
                else if(biggest.getRevenue() == checker.get(i).getRevenue())
                {
                    checker.remove(i);
                    i--;
                }
            }
            selected.add(biggest);
        }
        for(int i = 0; i < 50;i++)
        {
            System.out.println((i+1) + ". " + selected.get(i).getTitle() + ": " + selected.get(i).getRevenue());
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = selected.get(choice2 - 1);
        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}