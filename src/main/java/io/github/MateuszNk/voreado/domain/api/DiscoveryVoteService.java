package io.github.MateuszNk.voreado.domain.api;

import io.github.MateuszNk.voreado.domain.user.User;
import io.github.MateuszNk.voreado.domain.user.UserDao;
import io.github.MateuszNk.voreado.domain.vote.Vote;
import io.github.MateuszNk.voreado.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.Optional;

public class DiscoveryVoteService {
    private VoteDao voteDao = new VoteDao();
    private DiscoveryVoteMapper voteMapper = new DiscoveryVoteMapper();

    public void addVote(DiscoveryVote discoveryVote) {
        Vote voteToSave = voteMapper.map(discoveryVote);
        voteDao.save(voteToSave);
    }

    private static class DiscoveryVoteMapper {
        private final UserDao userDao = new UserDao();
        Vote map(DiscoveryVote discoveryVote) {
            Optional<User> user = userDao.findByUsername(discoveryVote.getUsername());
            return new Vote(
                    user.orElseThrow().getId(),
                    discoveryVote.getDiscoveryId(),
                    Vote.Type.valueOf(discoveryVote.getType()),
                    LocalDateTime.now()
            );
        }

    }
}
