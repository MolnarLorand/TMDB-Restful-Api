package org.academy.Lorand.controller;


import org.academy.Lorand.model.SolrMovie;
import org.academy.Lorand.repository.solr.SolrMovieRepository;
import org.academy.Lorand.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/solr")
public class GetMovieFromSolr {

    @Autowired
    MovieService movieService;
    @Autowired
    SolrMovieRepository solrMovieRepository;

    @GetMapping("/getAllMovies")
    @ResponseBody
    public List<SolrMovie> getAllSolrMovies(){
        return solrMovieRepository.findAll();
    }

    @GetMapping("/movieByTitle")
    @ResponseBody
    public FacetPage<SolrMovie> getSolrMovieByTitle(@RequestParam String title){

        return solrMovieRepository.findByTitle(title, new SolrPageRequest(0,10));
    }

    @GetMapping("/movieByPlot")
    @ResponseBody
    public SolrMovie getSolrMovieByPlot(@RequestParam String plot){
        return solrMovieRepository.findByPlot(plot);
    }

    @GetMapping("/moviesByPlot")
    @ResponseBody
    public List<SolrMovie> getSolrMoviesByPlot(@RequestParam String plot){
        return solrMovieRepository.findAllByPlot(plot);
    }


}
