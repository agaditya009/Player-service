package com.app.playerservicejava.service;

import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;

    public Players getPlayers(int page, int size) {
        int sanitizedPage = Math.max(page, 0);
        int sanitizedSize = size > 0 ? size : 10;

        Pageable pageable = PageRequest.of(sanitizedPage, sanitizedSize);
        Page<Player> playerPage = playerRepository.findAll(pageable);

        Players players = new Players();
        players.setPlayers(playerPage.getContent());

        Players.PageMetadata metadata = new Players.PageMetadata();
        metadata.setTotalElements(playerPage.getTotalElements());
        metadata.setTotalPages(playerPage.getTotalPages());
        metadata.setPage(playerPage.getNumber());
        metadata.setSize(playerPage.getSize());
        players.setMetadata(metadata);
        return players;
    }

    public Optional<Player> getPlayerById(String playerId) {
        Optional<Player> player = null;

        /* simulated network delay */
        try {
            player = playerRepository.findById(playerId);
            Thread.sleep((long)(Math.random() * 2000));
        } catch (Exception e) {
            LOGGER.error("message=Exception in getPlayerById; exception={}", e.toString());
            return Optional.empty();
        }
        return player;
    }

}
