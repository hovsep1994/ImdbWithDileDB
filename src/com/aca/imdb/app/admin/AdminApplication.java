package com.aca.imdb.app.admin;

import com.aca.imdb.movie.Movie;
import com.aca.imdb.moviemaker.model.Actor;
import com.aca.imdb.moviemaker.model.Producer;
import com.aca.imdb.moviemaker.model.Writer;
import com.aca.imdb.moviemaker.repository.ActorRepository;
import com.aca.imdb.moviemaker.repository.ProducerRepository;
import com.aca.imdb.moviemaker.repository.WriterRepository;
import com.aca.imdb.user.Admin;
import com.aca.imdb.user.AdminRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdminApplication {

    private static ProducerRepository producerRepository = ProducerRepository.getInstance();
    private static ActorRepository actorRepository = ActorRepository.getInstance();
    private static WriterRepository writerRepository = WriterRepository.getInstance();

    private Scanner scanner;
    private Admin admin;

    public AdminApplication() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        login();
        AdminAction adminAction;
        do {
            System.out.println("Choose action");
            System.out.println(Arrays.toString(AdminAction.values()));
            adminAction = AdminAction.get(scanner.nextInt());
            switch (adminAction) {
                case MOVIE:
                    admin.createMovie(createMovie());
                    break;
                case WRITER:
                    admin.createWriter(createWriter());
                    break;
                case PRODUCER:
                    admin.createProducer(createProducer());
                    break;
                case ACTOR:
                    admin.createActor(createActor());
                    break;
            }
        } while (adminAction != AdminAction.QUITE);
    }

    private void login() {
        System.out.println("Please type username");
        String username = scanner.next();
        System.out.println("Please type password");
        String password = scanner.next();
        admin = AdminRepository.getInstance().getUser(username, password);
        if (admin == null) {
            System.out.println("No such admin please try again");
            login();
        }
    }


    private Movie createMovie() {
        System.out.println("Movie title");
        String title = scanner.next();
        System.out.println("Movie description");
        String description = scanner.next();
        System.out.println("Movie premiere date");
        LocalDate date = LocalDate.parse(scanner.next());
        return new Movie(title, description, date, getProducers(), getWriters(), getActors());
    }

    private List<Producer> getProducers() {
        System.out.println("Choose producers (for quiting producer choosing type -1)");
        System.out.println(producerRepository.getAll());
        List<Producer> producers = new ArrayList<>();
        long typed;
        do {
            typed = scanner.nextLong();
            Producer producer = producerRepository.getById(typed);
            if (producer != null) {
                producers.add(producer);
            } else {
                System.out.println("Typed id doesn't exist");
            }
        } while (typed != -1);
        return producers;
    }

    private List<Writer> getWriters() {
        System.out.println("Choose writers (for quiting writer choosing type -1)");
        System.out.println(writerRepository.getAll());
        List<Writer> writers = new ArrayList<>();
        long typed;
        do {
            typed = scanner.nextLong();
            Writer writer = writerRepository.getById(typed);
            if (writer != null) {
                writers.add(writer);
            } else {
                System.out.println("Typed id doesn't exist");
            }
        } while (typed != -1);
        return writers;
    }

    private List<Actor> getActors() {
        System.out.println("Choose writers (for quiting writer choosing type -1)");
        System.out.println(actorRepository.getAll());
        List<Actor> actors = new ArrayList<>();
        long typed;
        do {
            typed = scanner.nextLong();
            Actor actor = actorRepository.getById(typed);
            if (actor != null) {
                actors.add(actor);
            } else {
                System.out.println("Typed id doesn't exist");
            }
        } while (typed != -1);
        return actors;
    }

    private Producer createProducer() {
        System.out.println("Producer name");
        String name = scanner.next();
        return new Producer(name);
    }

    private Writer createWriter() {
        System.out.println("Writer name");
        String name = scanner.next();
        return new Writer(name);
    }

    private Actor createActor() {
        System.out.println("Actor name");
        String name = scanner.next();
        return new Actor(name);
    }

}
