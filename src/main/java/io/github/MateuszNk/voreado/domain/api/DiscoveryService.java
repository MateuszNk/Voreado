package io.github.MateuszNk.voreado.domain.api;

import io.github.MateuszNk.voreado.domain.discovery.Discovery;
import io.github.MateuszNk.voreado.domain.discovery.DiscoveryDao;

import java.util.List;
import java.util.stream.Collectors;

public class DiscoveryService {
    private final DiscoveryDao discoveryDao = new DiscoveryDao();

    public List<DiscoveryBasicInfo> findAll() {
        return discoveryDao.findAll()
                .stream().map(DiscoveryMapper::map)
                .collect(Collectors.toList());
    }

    private static class DiscoveryMapper {
        static DiscoveryBasicInfo map(Discovery d) {
            return new DiscoveryBasicInfo(
                    d.getTitle(),
                    d.getUrl(),
                    d.getDescription(),
                    d.getDateAdded()
            );
        }
    }

    public List<DiscoveryBasicInfo> findByCategory(int categoryId) {
        return discoveryDao.findByCategory(categoryId)
                .stream().map(DiscoveryMapper::map)
                .collect(Collectors.toList());
    }
}
