package org.academy.Lorand.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="MovieCatalog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Serializable {

    @Id
    @Field
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Field
    @Column(name ="title")
    @JsonProperty("title")
    private String title;

    @Field
    @Column(name ="year")
    @JsonProperty("year")
    private String year;

    @Field
    @Column(name ="released")
    @JsonProperty("released")
    private String released;

    @Field
    @Column(name ="plot", columnDefinition = "TEXT")
    @JsonProperty("plot")
    private String plot;

    @Field
    @Column(name ="genre")
    @JsonProperty("genre")
    private String genre;

    @Field
    @Column(name ="imdbRating")
    @JsonProperty("imdbRating")
    private String imdbRating;

    @Field
    @Column(name ="actors", columnDefinition = "TEXT")
    @JsonProperty("actors")
    private String actors;

    @Field
    @Column(name ="writer")
    @JsonProperty("writer")
    private String writer;

    @Field
    @Column(name ="director")
    @JsonProperty("director")
    private String director;

    public Movie() {
    }

    public Movie(Integer id, String title, String year, String released, String plot, String genre, String imdbRating, String actors, String writer, String director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.released = released;
        this.plot = plot;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.actors = actors;
        this.writer = writer;
        this.director = director;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", released='" + released + '\'' +
                ", plot='" + plot + '\'' +
                ", genre='" + genre + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", actors='" + actors + '\'' +
                ", writer='" + writer + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
