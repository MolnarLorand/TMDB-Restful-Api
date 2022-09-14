/*
package org.academy.Lorand.solr;

import org.academy.Lorand.model.Movie;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SolrjConnection {
    public static void main(String[] args) throws SolrServerException, IOException {

        final String solrUrl="http://localhost:8983/solr";
        HttpSolrClient httpSolrClient = null;
        httpSolrClient = new HttpSolrClient.Builder("http://localhost:8983/solr")
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();


        final SolrInputDocument doc = new SolrInputDocument();
        //each desired field is added to the doc
        doc.addField("id", UUID.randomUUID().toString());
        doc.addField("name","Deadpool 2");
        final UpdateResponse updateResponse = httpSolrClient.add("movies",doc);
        httpSolrClient.commit("movies");

        final SolrQuery query = new SolrQuery("*:*");
        query.addField("id");
        query.addField("name");
        query.setSort("id", SolrQuery.ORDER.asc);

        final QueryResponse response = httpSolrClient.query("movies",query);
        System.out.println(response);
        final List<Movie> products = response.getBeans(Movie.class);

    }
}
*/
