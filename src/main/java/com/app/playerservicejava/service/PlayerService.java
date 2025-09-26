package com.app.playerservicejava.service;

import com.app.playerservicejava.model.PageMetadata;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Players getPlayers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Player> playerPage = playerRepository.findAll(pageable);

        PageMetadata metadata = new PageMetadata(
                playerPage.getTotalElements(),
                playerPage.getTotalPages(),
                playerPage.getNumber(),
                playerPage.getSize()
        );

        return Players.of(playerPage.getContent(), metadata);
    }

    public Optional<Player> getPlayerById(String playerId) {
        Optional<Player> player = null;

        /* simulated network delay */
        try {
            player = playerRepository.findById(playerId);
            Thread.sleep((long) (Math.random() * 2000));
        } catch (Exception e) {
            LOGGER.error("message=Exception in getPlayerById; exception={}", e.toString());
            return Optional.empty();
        }
        return player;
    }
}
