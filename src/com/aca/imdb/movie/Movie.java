package com.aca.imdb.movie;

import com.aca.imdb.moviemaker.model.Actor;
import com.aca.imdb.moviemaker.model.Producer;
import com.aca.imdb.moviemaker.model.Writer;
import com.aca.imdb.repository.Persistable;
import java.time.LocalDate;
import java.util.List;

public class Movie extends Persistable {

    private static long idCounter;

    private String title;
    private String description;
    private LocalDate premierDate;
    private List<Producer> producers;
    private List<Writer> writers;
    private List<Actor> actors;

    public Movie(String title,
            String description,
            LocalDate premierDate,
            List<Producer> producers,
            List<Writer> writers,
            List<Actor> actors) {
        super(idCounter++);
        this.title = title;
        this.description = description;
        this.premierDate = premierDate;
        this.producers = producers;
        this.writers = writers;
        this.actors = actors;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPremierDate() {
        return premierDate;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public List<Actor> getActors() {
        return actors;
    }

    @Override
    public String toString() {
        return super.toString() + ", title : " + title;
    }

    public String getFullDescription() {
        return "Movie{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", premierDate=" + premierDate +
                ", producers=" + producers +
                ", writers=" + writers +
                ", actors=" + actors +
                '}';
    }
}
