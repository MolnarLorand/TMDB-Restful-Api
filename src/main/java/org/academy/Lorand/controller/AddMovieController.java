package org.academy.Lorand.controller;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import org.academy.Lorand.model.Movie;
import org.academy.Lorand.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/tmdb")
public class AddMovieController {

    @Autowired
    private MovieService movieService;

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

    @GetMapping("/save")
    @ResponseBody
    public void saveMovies(@RequestParam String query, @RequestParam Integer pageStart, @RequestParam Integer pageEnd){
        movieService.uploadMoviesToDb(query,pageStart,pageEnd);
    }

    @GetMapping("/solr")
    @ResponseBody
    public void toSolr(){
        movieService.migrateToSolr();
    }

    @GetMapping("/getAllMovies")
    @ResponseBody
    public List<Movie> getAllMovies(){
        return movieService.getAllMoviesFromDb();
    }
}
