package io.github.MateuszNk.voreado.domain.api;

import io.github.MateuszNk.voreado.domain.discovery.Discovery;
import io.github.MateuszNk.voreado.domain.discovery.DiscoveryDao;
import io.github.MateuszNk.voreado.domain.user.UserDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DiscoveryService {
    private final DiscoveryDao discoveryDao = new DiscoveryDao();
    private final DiscoveryMapper discoveryMapper = new DiscoveryMapper();

    public void add(DiscoverySaveRequest discoverySaveRequest) {
        Discovery discoveryToSave = discoveryMapper.map(discoverySaveRequest);
        discoveryDao.save(discoveryToSave);
    }
    public List<DiscoveryBasicInfo> findAll() {
        return discoveryDao.findAll()
                .stream().map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    private static class DiscoveryMapper {
        private final UserDao userDao = new UserDao();
        DiscoveryBasicInfo map(Discovery d) {
            return new DiscoveryBasicInfo(
                    d.getId(),
                    d.getTitle(),
                    d.getUrl(),
                    d.getDescription(),
                    d.getDateAdded()
            );
        }

        Discovery map(DiscoverySaveRequest ds) {
            return new Discovery(
                    ds.getTitle(),
                    ds.getUrl(),
                    ds.getDescription(),
                    LocalDateTime.now(),
                    ds.getCategoryId(),
                    userDao.findByUsername(ds.getAuthor())
                            .orElseThrow()
                            .getId()
            );
        }
    }

    public List<DiscoveryBasicInfo> findByCategory(int categoryId) {
        return discoveryDao.findByCategory(categoryId)
                .stream().map(discoveryMapper::map)
                .collect(Collectors.toList());
    }
}
