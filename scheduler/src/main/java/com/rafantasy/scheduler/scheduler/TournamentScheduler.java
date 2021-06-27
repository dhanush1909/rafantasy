package com.rafantasy.scheduler.scheduler;

import com.rafantasy.scheduler.config.TournamentApi;
import com.rafantasy.shared.common.feign.TennisClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TournamentScheduler {

    private final TennisClient tennisClient;

    private final TournamentApi tournamentApi;

    @Scheduled(cron = "0 10 17 * * *", zone = "IST")
    public void tournamentScheduler() {

        // todo: map to proper dto
        System.out.println(tennisClient.getTournamentMatches(tournamentApi.getCategory(),"wimbledon", "mens-singles"));

    }
}
