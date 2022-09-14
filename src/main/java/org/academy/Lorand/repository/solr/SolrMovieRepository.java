package org.academy.Lorand.repository.solr;

import org.academy.Lorand.model.SolrMovie;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

public interface SolrMovieRepository extends SolrCrudRepository<SolrMovie,Integer> {

    SolrMovie findByTitle(String title);
}
