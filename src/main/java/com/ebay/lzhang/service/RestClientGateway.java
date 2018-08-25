package com.ebay.lzhang.service;

import java.net.URLEncoder;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.ebay.lzhang.model.GitRepository;
import com.ebay.lzhang.util.JsonUtil;
import com.ebay.lzhang.util.Log;

/**
 * Use Apache HttpClient to interact with remote GitHub using their
 * RESTful api.
 * 
 * @author lzhang
 *
 */
@Component
public class RestClientGateway {
    /**
     * This is a REST api gateway designed to request public repositories from GitHub.
     * Without modifications, you can use this implementation to search for repositories
     * in many different preferences. For example, you can ask for repositories with key words,
     * most or least beloved (stars), most/least forked, most/least recently updated, and more
     * if you are familiar with GitHub api documentation.
     * 
     * You can contact me for questions. But I by no means an expert on their api's. I only spent
     * about less than an hour to understand this part of doc (query for repositories) solely to
     * for completing this assignment.
     *  
     * @param query
     * @param sortBy
     * @param sortOrder
     * @param pageIndex
     * @param itemsPerPage
     * @return
     * @throws Exception
     */
    public List<GitRepository> getTopForkedRepositories(String query, String sortBy, String sortOrder, int pageIndex, int itemsPerPage) throws Exception {
        //String stubsApiBaseUri = "https://api.github.com/search/repositories?q=forks:>1&sort=forks&page=1&per_page=10&order=desc";
        //String stubsApiBaseUri = "https://api.github.com/search/repositories?q=forks%3A%3E1&sort=forks&page=1&per_page=10&order=desc";
        //String apiBaseUri = "/search/repositories?q=forks%3A%3E1&sort=forks&page=1&per_page=10&order=desc";
        
        /*
         * Note that query string may contain invalid URL characters. Encode it first. 
         */
        query = URLEncoder.encode(query, "UTF-8");
        String apiBaseUri = String.format("/search/repositories?q=%s&sort=%s&order=%s&page=%d&per_page=%d", query, sortBy, sortOrder, pageIndex, itemsPerPage);

        HttpClient httpclient = HttpClients.createDefault();
        HttpGet getRequest = null;

        try {
            // specify the host, protocol, and port
            HttpHost target = new HttpHost("api.github.com", 443, "https");

            // specify the get request
            getRequest = new HttpGet(apiBaseUri);

            Log.log("executing request to " + target);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Log.log("statusCode: " + statusCode);
            if (statusCode < 200 || statusCode >= 300) {
                // Handle non-2xx status code
                throw new Exception("GitHub returns unexpected sattus: " + statusCode);
            }

            HttpEntity entity = httpResponse.getEntity();

            Log.log("----------------------------------------");
            Header[] headers = httpResponse.getAllHeaders();
            for (int i = 0; i < headers.length; i++) {
                Log.log("header " + headers[i]);
            }
            Log.log("----------------------------------------");

            if (entity == null) {
                throw new Exception("GitHub returns invalid response - null entity");
            }
            
            String json = EntityUtils.toString(entity);
            Log.log(json);
            List<GitRepository> repos = JsonUtil.fromJsonString(json);
            return repos;
        } finally {
            if (getRequest != null) {
                getRequest.releaseConnection();
            }
        }
    }
}
