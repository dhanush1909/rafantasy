package com.rafantasy.shared.common.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tennis", url = "${livescore-api.base-url}")
public interface TennisClient {

    @GetMapping("/list-by-league")
    String getTournamentMatches(@RequestParam(value = "Category") String category,
                                @RequestParam(value = "Ccd", required = false) String ccd,
                                @RequestParam(value = "Scd", required = false) String scd);
}
