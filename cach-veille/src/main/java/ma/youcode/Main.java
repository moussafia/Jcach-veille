package ma.youcode;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CachingProvider provider = Caching.getCachingProvider();
        CacheManager manager = provider.getCacheManager();
        CacheManager manager1 = provider.getCacheManager();
        MutableConfiguration<Integer, String> configuration = new MutableConfiguration<>();
        Cache<Integer, String> cache = manager.createCache("name", configuration);
        cache.put(1,"mohammed");
        cache.put(2,"hya");
        cache.put(3,"howa");
        cache.put(4,"ana");
        cache.forEach(entry -> System.out.println(entry.getValue()));
    }
}