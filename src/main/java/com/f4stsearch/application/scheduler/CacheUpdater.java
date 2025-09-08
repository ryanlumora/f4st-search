package com.f4stsearch.application.scheduler;

import com.f4stsearch.adapter.out.FakeStoreClient;
import com.f4stsearch.domain.service.ProductCacheService;
import com.f4stsearch.domain.service.SnapshotService;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheUpdater {

    private final FakeStoreClient client;
    private final ProductCacheService cacheService;
    private final SnapshotService snapshotService;

    public CacheUpdater(FakeStoreClient client, ProductCacheService cacheService, SnapshotService snapshotService) {
        this.client = client;
        this.cacheService = cacheService;
        this.snapshotService = snapshotService;
    }

    @PostConstruct
    public void initCache(){
        updateCache();
    }

        @Scheduled(cron = "0 0 0 * * *")
    public void updateCache() {
        client.getAll().forEach(dto -> {
            var product = cacheService.saveOrUpdate(dto);
            snapshotService.createSnapshot(product);
        });
    }
}
