package org.academy.Lorand.repository.solr;

import org.academy.Lorand.model.SolrMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SolrMovieRepository extends SolrCrudRepository<SolrMovie,Integer> {

    @Facet(fields = {"title"},minCount = 1, limit = 1)
    FacetPage<SolrMovie> findByTitle(String title, Pageable pageable);



    List<SolrMovie> findAllByPlot(String plot);

    SolrMovie findByPlot(String plot);

    List<SolrMovie> findAll();
}
