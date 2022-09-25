package org.academy.Lorand.controller;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import org.academy.Lorand.model.Movie;
import org.academy.Lorand.repository.db.MovieRepository;
import org.academy.Lorand.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/tmdb")
public class AddMovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;


    //test the Api
    @GetMapping("/searchMovie")
    @ResponseBody
    public String searchMovieDbApi(@RequestParam String query){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl="https://api.themoviedb.org/3/search/movie?api_key=8b9bb031f80795a5c6523639af860e23&&language=en-US&page=1&include_adult=false&query=" + query;
        return restTemplate.getForEntity(apiUrl,String.class).getBody();
    }

    @GetMapping("/getUpcomingMovies")
    @ResponseBody
    public List<MovieDb> getMoviesFromTmdb(){
        TmdbMovies tmdbMovies = new TmdbApi("8b9bb031f80795a5c6523639af860e23").getMovies();
        return tmdbMovies.getUpcoming("en",1,"US").getResults();
    }

    @GetMapping("/getLatestMovies")
    @ResponseBody
    public MovieDb getLatestMovies(){
        TmdbMovies movieDb = new TmdbApi("8b9bb031f80795a5c6523639af860e23").getMovies();
        return (MovieDb) movieDb.getLatestMovie();
    }

    @GetMapping("/getMoviesPlayingInTheatres")
    @ResponseBody
    public List<MovieDb> getMoviesPlayingInTheatres(){
        TmdbMovies tmdbMovies = new TmdbApi("8b9bb031f80795a5c6523639af860e23").getMovies();
        return tmdbMovies.getNowPlayingMovies("en",1,"us").getResults();
    }


    //Starting to work with api data
    @GetMapping("/save")
    @ResponseBody
    public void saveMovies(@RequestParam String query, @RequestParam Integer pageStart, @RequestParam Integer pageEnd){
        movieService.uploadMoviesToDb(query,pageStart,pageEnd);
    } //select movies from external db to save in my db

    @GetMapping("/solr")
    @ResponseBody
    public void toSolr(){
        movieService.migrateToSolr();
    }//migrate movies from db to solr

    @GetMapping("/getAllMovies")
    @ResponseBody
    public List<Movie> getAllMovies(){
        return movieService.getAllMoviesFromDb();
    }

    @GetMapping("/findByTitle/{title}")
    @ResponseBody
    public Movie findMovieByTitle(@PathVariable String title){
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public Movie findMovieById(@PathVariable Integer id){
        return movieService.getMovieById(id);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        return movieService.deleteMovie(id);
    }

    @PutMapping ("/updateMovie/{id}")
    public Movie updateMovie(@PathVariable Integer id,@RequestBody Movie movie){
        return movieService.updateMovie(id,movie);
    }

    @PostMapping("/addMovie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie addMovie(@RequestBody Movie newMovie){
        return movieService.addMovie(newMovie);
    }

    @PostMapping("/createMovies")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Movie> addMovies(@RequestBody List<Movie> movies){
        return movieService.addMovies(movies);
    }

}
