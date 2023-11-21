package ma.youcode.annotation;

import ma.youcode.annotation.entities.Employes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;


@SpringBootApplication
public class JavaCachApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCachApplication.class, args);
    }

    @Bean(destroyMethod = "close")
    public CachingProvider cachingProvider(){
        CachingProvider cachingProvider = Caching.getCachingProvider();
        return cachingProvider;
    }
    @Bean(destroyMethod = "close")
    public CacheManager cacheManager(CachingProvider cachingProvider){
        CacheManager cache = cachingProvider.getCacheManager();
        return cache;
    }
    @Bean("employeCachConfig")
    public MutableConfiguration<Integer, Employes> CreateEmloyeCacheConfig(){
        MutableConfiguration<Integer, Employes> config =
                new MutableConfiguration<Integer, Employes>()
                        .setCacheWriterFactory(new EmployeeCacheWriterFactory())
                        .setWriteThrough(true)
                        .setCacheLoaderFactory(new EmployeesCacheLoaderFactory())
                        .setReadThrough(true);
        return config;
    }
    @Bean(value = "employeecache", destroyMethod = "close")
    public Cache<Integer, Employes> createEmployeCache(CacheManager cacheManager,
                                                     @Qualifier("employeCachConfig")
                                                     MutableConfiguration<Integer, Employes> config){
        return cacheManager.createCache("employe", config);
    }

    @Bean
    public JCacheCacheManager cacheCacheManager(CacheManager cacheManager){
        return new JCacheCacheManager(cacheManager);
    }


}
