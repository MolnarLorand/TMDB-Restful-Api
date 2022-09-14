/*
package org.academy.Lorand.controller;

import org.academy.Lorand.model.Movie;
import org.academy.Lorand.model.SolrMovie;
import org.academy.Lorand.repository.db.MovieRepository;
import org.academy.Lorand.repository.solr.SolrMovieRepository;
import org.academy.Lorand.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/movies/")
public class GetMoviesFromSqlController {

*/
/*    @Autowired
    MovieRepository movieRepository;*//*


    @Autowired
    MovieService movieService;

    @Autowired
    SolrMovieRepository solrMovieRepository;

*/
/*    @GetMapping(value = "all")
    public @ResponseBody
    List<Movie> getAllMovies(){
        return IteratorUtils.toList(movieRepository.findAll().iterator());
    }

    @GetMapping(value = "name")
    public @ResponseBody
    List<Movie> getMovie(@RequestParam String name){
        return IteratorUtils.toList(movieRepository.findMovieByActorName(name).iterator());
    }

    @GetMapping(value = "gen")
    public @ResponseBody
    List<Movie> getMovieGenres(@RequestParam String gen){
        return movieRepository.findByGenres(gen, new SolrPageRequest(0,10));
    }*//*


    @GetMapping("add-movie")
    public SolrMovie addMovieToSolr(Movie movie){
        SolrMovie movie1 = new SolrMovie(1,"bean","2000","3/2/2022","n/a","comedy","10","test","test2","director");
        return solrMovieRepository.save(movie1);
    }

    @GetMapping("all")
    public SolrMovie getMovie(){
        return solrMovieRepository.findAll().iterator().next();
    }


}
*/
