package org.academy.Lorand.service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import org.academy.Lorand.model.Movie;
import org.academy.Lorand.model.QMovie;
import org.academy.Lorand.model.SolrMovie;
import org.academy.Lorand.repository.db.MovieRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.academy.Lorand.repository.solr.SolrMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SolrMovieRepository solrMovieRepository;

    //--------------------------------------------******--------------------------------------------------------\\
    public Movie convertMovie(MovieDb movieResult) {
        Movie movie = new Movie();
        movie.setYear(movieResult.getReleaseDate());
        movie.setTitle(movieResult.getTitle());
        movie.setReleased(movieResult.getReleaseDate());
        movie.setPlot(movieResult.getOverview());

        List<PersonCast> actors = new TmdbApi("8b9bb031f80795a5c6523639af860e23").getMovies().getCredits(movieResult.getId()).getCast();

        List<String> actorNames = new ArrayList<>();
        for (PersonCast person : actors
        ) {
            actorNames.add(person.getName());
        }
        movie.setActors(String.join(",", actorNames)
        );

/*        List<PersonCrew> crew = new TmdbApi("8b9bb031f80795a5c6523639af860e23").getMovies().getCredits(movieResult.getId()).getCrew();
        List<String> crewNames = new ArrayList<>();
        for (PersonCrew crewMember:crew
             ) {
            movie.setDirector(String.join(",",crewNames));
        }*/

        return movie;
    }

    public List<Movie> convertMovies(List<MovieDb> results) {

        List<Movie> list = new ArrayList<>();

        for (MovieDb movie : results
        ) {
            list.add(convertMovie(movie));
        }
        return list;
    }

    public void uploadMoviesToDb(String query, Integer pageStart, Integer pageEnd) {
        TmdbSearch tmdbSearch = new TmdbApi("8b9bb031f80795a5c6523639af860e23").getSearch();
        for (int page = pageStart; page < pageEnd; page++) {
            MovieResultsPage movieResultsPage = tmdbSearch.searchMovie(query, null, null, false, page);
            List<Movie> movies = convertMovies(movieResultsPage.getResults());
            movieRepository.saveAll(movies);
        }
    }

    public SolrMovie toSolr(Movie movie) {
        SolrMovie solrMovie = new SolrMovie();

        solrMovie.setYear(movie.getReleased());
        solrMovie.setTitle(movie.getTitle());
        solrMovie.setReleased(movie.getReleased());
        solrMovie.setPlot(movie.getPlot());
        solrMovie.setActors(movie.getActors());

        return solrMovie;
    }

    public List<SolrMovie> convertForSolr(List<Movie> results) {
        List<SolrMovie> list = new ArrayList<>();

        for (Movie result : results
        ) {
            list.add(toSolr(result));
        }
        return list;
    }

    public void migrateToSolr() {
        List<Movie> dbMovies = movieRepository.findAll();

        solrMovieRepository.saveAll(convertForSolr(dbMovies));

    }

    public List<Movie> getAllMoviesFromDb() {
        return movieRepository.findAll();
    }

    public String deleteMovie(Integer id) {
        movieRepository.deleteById(id);
        return "Movie with id: " + id + " was deleted form db";
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie updateMovie(Integer Id, Movie movie) {
        Movie dbMovie = movieRepository.findById(movie.getId()).orElse(null);
        assert dbMovie != null;
        dbMovie.setYear(dbMovie.getYear());
        dbMovie.setTitle(dbMovie.getTitle());
        dbMovie.setReleased(dbMovie.getReleased());
        dbMovie.setPlot(dbMovie.getPlot());

        return movieRepository.save(dbMovie);
    }

    public Movie addMovie(Movie newMovie) {

        return movieRepository.save(newMovie);
        //raw json in postman
    }

    public List<Movie> addMovies(List<Movie> movies){
        return movieRepository.saveAll(movies);
    }

}
