package net.personalProject.journalApp.Cache;

import net.personalProject.journalApp.Repository.ConfigAppCacheRepository;
import net.personalProject.journalApp.entity.ConfigAppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AppCache {

    public Map<String,String> appCache;

    @Autowired
    ConfigAppCacheRepository cacheRepository;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigAppCache> all = cacheRepository.findAll();
        for(ConfigAppCache cache:all){
            appCache.put(cache.getKey(),cache.getValue());
        }

    }
}
