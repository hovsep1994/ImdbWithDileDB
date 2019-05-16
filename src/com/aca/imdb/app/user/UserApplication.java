package com.aca.imdb.app.user;

import com.aca.imdb.movie.Movie;
import com.aca.imdb.movie.MovieRepository;
import com.aca.imdb.user.RegularUser;
import com.aca.imdb.user.RegularUserRepository;
import java.util.Arrays;
import java.util.Scanner;

public class UserApplication {

    private RegularUserRepository userRepository = RegularUserRepository.getInstance();
    private MovieRepository movieRepository = MovieRepository.getInstance();

    private RegularUser user;
    private Scanner scanner;

    public UserApplication() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Choose action");
        System.out.println(Arrays.toString(PreLoginAction.values()));
        PreLoginAction preLoginAction = PreLoginAction.getPreLoginAction(scanner.nextInt());
        switch (preLoginAction) {
            case LOGIN:
                login();
                break;
            case REGISTER:
                register();
                break;
        }
        UserAction userAction;
        do {
            System.out.println("Choose action");
            System.out.println(Arrays.toString(UserAction.values()));
            userAction = UserAction.getUserAction(scanner.nextInt());
            switch (userAction) {
                case RATE:
                    System.out.println("Choose movie");
                    System.out.println(movieRepository.getAll());
                    long movieId = scanner.nextLong();
                    if (movieRepository.getById(movieId) == null) {
                        System.out.println("No such movie");
                        break;
                    }
                    System.out.println("Choose rating");
                    double rate = scanner.nextDouble();
                    user.rateMovie(movieId, rate);
                    break;
                case SEARCH:
                    System.out.println("Type movie name");
                    Movie movie = user.search(scanner.next());
                    if (movie != null) {
                        System.out.println("Found movie:");
                        System.out.println(movie.getFullDescription());
                    } else {
                        System.out.println("Movie doesn't exist");
                    }
                    break;
            }
        } while (userAction != UserAction.QUITE);

    }

    private void login() {
        System.out.println("Please type username");
        String username = scanner.next();
        System.out.println("Please type password");
        String password = scanner.next();
        user = userRepository.getUser(username, password);
        if (user == null) {
            System.out.println("No such user please try again");
            login();
        }
    }

    private void register() {
        System.out.println("Please type username");
        String username = scanner.next();
        System.out.println("Please type password");
        String password = scanner.next();
        try {
            user = userRepository.create(new RegularUser(username, password));
        } catch (IllegalArgumentException e) {
            System.out.println("Username exists, please try again");
            register();
        }
    }

}
