package com.aca.imdb.app;

import com.aca.imdb.app.admin.AdminApplication;
import com.aca.imdb.app.user.UserApplication;
import com.aca.imdb.movie.Movie;
import com.aca.imdb.movie.MovieRepository;
import com.aca.imdb.moviemaker.model.Actor;
import com.aca.imdb.moviemaker.model.Producer;
import com.aca.imdb.moviemaker.model.Writer;
import com.aca.imdb.moviemaker.repository.ActorRepository;
import com.aca.imdb.moviemaker.repository.ProducerRepository;
import com.aca.imdb.moviemaker.repository.WriterRepository;
import com.aca.imdb.user.Admin;
import com.aca.imdb.user.AdminRepository;
import com.aca.imdb.user.RegularUser;
import com.aca.imdb.user.RegularUserRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public static void start() {
//        setupDate();
        Scanner scanner = new Scanner(System.in);
        ApplicationMode applicationMode;
        do {
            System.out.println("Choose app mode");
            System.out.println(Arrays.toString(ApplicationMode.values()));
            applicationMode = ApplicationMode.get(scanner.nextInt());
            start(applicationMode);
        } while (applicationMode != ApplicationMode.QUIT);
    }

    private static void setupDate() {
        RegularUserRepository.getInstance().create(new RegularUser("user", "user"));
        AdminRepository.getInstance().create(new Admin("admin", "admin"));
        Writer writer = WriterRepository.getInstance().create("Writer");
        Actor actor = ActorRepository.getInstance().create("Actor");
        Producer producer = ProducerRepository.getInstance().create("Producer");
        MovieRepository.getInstance().create(new Movie(
                "Title",
                "Decription",
                LocalDate.now(),
                Arrays.asList(producer),
                Arrays.asList(writer),
                Arrays.asList(actor)
        ));
    }

    private static void start(ApplicationMode mode) {
        switch (mode) {
            case USER:
                new UserApplication().start();
                break;
            case ADMIN:
                new AdminApplication().start();
                break;
        }
    }

}
