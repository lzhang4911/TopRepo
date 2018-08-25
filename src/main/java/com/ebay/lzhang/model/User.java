package com.ebay.lzhang.model;

/**
 * This the the "owner" of each GitHub repository
 * 
 * @author lzhang
 *
 */
public class User {
    private long id;
    private String login;
    private String html_url;
    
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }
    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
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
}
