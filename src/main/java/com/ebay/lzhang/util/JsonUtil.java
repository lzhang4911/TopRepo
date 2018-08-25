package com.ebay.lzhang.util;

import java.util.ArrayList;
import java.util.List;

import com.ebay.lzhang.model.GitRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This is a simple GSON parser that looks for node "items" under which there
 * are a list of repository JSON elements.
 * 
 * As model objects indicated, I only retrieve some bare minimum information
 * from the original JSON structure for the purpose of this mini assignment!
 * 
 * If you need to include every attributes, just update the model objects, and
 * everything else should just work automatically.
 * 
 * Note that the parsing here is based on the live JSON structure coming off
 * of GitHub as of 08/25/2018. The code will not work properly if GitHub changes
 * their JSON structure in future.
 * 
 * @author lzhang
 *
 */
public class JsonUtil {
    private static final Gson gson = new Gson();
    private static final JsonParser jsonParser = new JsonParser();
    
    /**
     * The input is complete JSON structure that is retrieved directly
     * from GitHub. However, this project needs only the list of elements
     * under "items".
     * 
     * @param json
     * @return
     * @throws Exception
     */
    public static List<GitRepository> fromJsonString(String json) throws Exception {
        List<GitRepository> repos = new ArrayList<GitRepository>();
        
        JsonObject jo = (JsonObject)jsonParser.parse(json);
        assert(jo != null);
        
        JsonArray list = (JsonArray)jo.get("items");
        assert(list != null);
        
        for(JsonElement ele : list) {
            GitRepository repo = fromSingleJsonString(ele);
            if(repo != null) {
                repos.add(repo);
            }
        }
                
        return repos;
    }
    
    private static GitRepository fromSingleJsonString(JsonElement jsonElement) throws Exception {
        return gson.fromJson(jsonElement, GitRepository.class);
    }
}
