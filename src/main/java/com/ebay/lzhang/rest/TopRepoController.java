package com.ebay.lzhang.rest;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.lzhang.model.GitRepository;
import com.ebay.lzhang.service.RestClientGateway;
import com.ebay.lzhang.util.Log;

@RestController
@RequestMapping("/api")
public class TopRepoController {
    @Autowired
    private RestClientGateway restGateway;
    
    @RequestMapping("")
    public List<String> index() {
        List<String> list = new ArrayList<String>();
        
        list.add("Greetings from Leon Zhang via Spring Boot!");
        list.add("This REST controller supports following 3 api's");
        list.add("1. GET /api/mostForked");
        list.add("2. GRT /api/mostRecentlyUpdated");
        list.add("3. GET /api/top10?q=<queryPattern>&sort=<sortBy_attribute>&order=[asc | desc]&page=[1,2,3..]&per_page=<items_per_page>");
        
        return list;
    }
    
    /**
     * Search for 10 most forked repositories
     * 
     * @return
     */
    @GetMapping("/mostForked")
    public List<GitRepository> mostForked() {
        String query = "forks:>1";
        String sortBy = "forks";
        String sortOrder = "desc";
        
        return this.getTopRepositories(query, sortBy, sortOrder, 1, 10);
    }
    
    /**
     * Search for 10 most recently updated repositories
     * 
     * @return
     */
    @GetMapping("/mostRecentlyUpdated")
    public List<GitRepository> mostRecentlyUpdated() {
        String query = "updated";
        String sortBy = "updated";
        String sortOrder = "desc";
        
        return this.getTopRepositories(query, sortBy, sortOrder, 1, 10);
    }
            

    /**
     * This api is more generic. User can provide various query parameters for different needs.
     * For example, if you want to search for 20 most forked repositories whose description
     * contain word "eBay", you provide "ebay+forks>1" for argument query and see if you are
     * surprised!
     * 
     * @param query
     * @param sortBy
     * @param sortOrder
     * @param pageIndex
     * @param itemsPerPage
     * @return
     */
    @GetMapping("/top10")
    public List<GitRepository> getTopRepositories(
            @RequestParam("q") String query, 
            @RequestParam("sort") String sortBy,
            @RequestParam(value = "order", defaultValue = "desc") final String sortOrder,
            @RequestParam(value = "page", defaultValue = "1") final int pageIndex,
            @RequestParam(value = "per_page", defaultValue = "10") final int itemsPerPage
            ) {
//        RestClientGateway restGateway = RestClientGateway.getInstance();
        
        try {
            return restGateway.getTopForkedRepositories(query, sortBy, sortOrder, pageIndex, itemsPerPage);
        } catch(Exception e) {
            Log.error("Failed in getTopRepositories()", e);
        }
        
        return null;
    }
}
