package com.ebay.lzhang.model;

public class GitRepository {
    /*
     * Name each attribute exactly as they appear in the JSON output 
     */
    private User owner; // this is under "owner"
    private long id;
    private String name;
    private String full_name;
    private String description;
    private String html_url;
    private String downloads_url;
    
    private String created_at;
    private String updated_at;
    
    private long stargazers_count;
    private long forks_count;
    
    /**
     * @return the Owner
     */
    public User getOwner() {
        return owner;
    }
    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the full_name
     */
    public String getFull_name() {
        return full_name;
    }
    /**
     * @param full_name the full_name to set
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the html_url
     */
    public String getHtml_url() {
        return html_url;
    }
    /**
     * @param html_url the html_url to set
     */
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
    
    /**
     * @return the downloads_url
     */
    public String getDownloads_url() {
        return downloads_url;
    }
    /**
     * @param downloads_url the downloads_url to set
     */
    public void setDownloads_url(String downloads_url) {
        this.downloads_url = downloads_url;
    }
    
    /**
     * @return the created_at
     */
    public String getCreated_at() {
        return created_at;
    }
    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    /**
     * @return the updated_at
     */
    public String getUpdated_at() {
        return updated_at;
    }
    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
    
    /**
     * @return the stargazers_count
     */
    public long getStargazers_count() {
        return stargazers_count;
    }
    /**
     * @param stargazers_count the stargazers_count to set
     */
    public void setStargazers_count(long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }
    
    /**
     * @return the forks_count
     */
    public long getForks_count() {
        return forks_count;
    }
    /**
     * @param forks_count the forks_count to set
     */
    public void setForks_count(long forks_count) {
        this.forks_count = forks_count;
    }
}
