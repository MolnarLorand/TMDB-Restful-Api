package org.academy.Lorand.service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;
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

    public JPAQueryFactory getJpaQueryFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieCatalog");
        EntityManager em = emf.createEntityManager();
        return new JPAQueryFactory(em);
    }

    public List<Movie> getMovieListForController() {
        return getJpaQueryFactory().selectFrom(QMovie.movie).fetch();
    }


    public MovieService() {
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

/*
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
*/

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public String deleteMovie(Integer id) {
        movieRepository.deleteById(id);
        return "Movie removed for id: " + id + ".";
    }

    public Movie updateMovie(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        existingMovie.setReleased(movie.getReleased());
        existingMovie.setPlot(movie.getPlot());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setImdbRating(movie.getImdbRating());
        existingMovie.setActors(movie.getActors());
        existingMovie.setWriter(movie.getWriter());
        existingMovie.setDirector(movie.getDirector());
        return movieRepository.save(existingMovie);
    }

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

    public List<Movie> getAllMoviesFromDb(){
        return movieRepository.findAll();
    }
}
