package org.academy.Lorand.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@SolrDocument(collection = "MovieSolrCore")
public class SolrMovie implements Serializable {

    @Id
    @Field
    @Indexed
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Field
    @Indexed
    @JsonProperty("title")
    private String title;

    @Field
    @Indexed
    @JsonProperty("year")
    private String year;

    @Field
    @Indexed
    @JsonProperty("released")
    private String released;

    @Field
    @Indexed
    @JsonProperty("plot")
    private String plot;

    @Field
    @JsonProperty("genre")
    private String genre;

    @Field
    @JsonProperty("imdbRating")
    private String imdbRating;

    @Field
    @Indexed
    @JsonProperty("actors")
    private String actors;

    @Field
    @JsonProperty("writer")
    private String writer;

    @Field
    @JsonProperty("director")
    private String director;

    public SolrMovie() {
    }

    public SolrMovie(Integer id, String title, String year, String released, String plot, String genre, String imdbRating, String actors, String writer, String director) {
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
