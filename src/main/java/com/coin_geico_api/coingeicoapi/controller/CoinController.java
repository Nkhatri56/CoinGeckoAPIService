package com.coin_geico_api.coingeicoapi.controller;

import com.coin_geico_api.coingeicoapi.model.CoinEntity;
import com.coin_geico_api.coingeicoapi.service.CoinService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CoinService coinService;
@GetMapping
public ResponseEntity<List<CoinEntity>> getCoinList(@RequestParam("page") int page) throws Exception {
 List<CoinEntity> coins=coinService.getCoinList(page);
 return new ResponseEntity<>(coins, HttpStatus.ACCEPTED);

}
    @GetMapping("/{coinId}/chart")
    public ResponseEntity<JsonNode> getMarketChart(@PathVariable String coinId, @RequestParam("days") int days) throws Exception {
        String marketchart=coinService.getMarketChart(coinId, days);
        JsonNode jsonNode=objectMapper.readTree(marketchart);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);


    }
    @GetMapping("/search")
    public ResponseEntity<JsonNode> getMarketChart( @RequestParam("keyword") String keyword) throws Exception {
        String coin=coinService.searchCoin(keyword);
        JsonNode jsonNode=objectMapper.readTree(coin);
        return ResponseEntity.ok(jsonNode);



    }

    @GetMapping("/top50")
    public ResponseEntity<JsonNode> getTop50( ) throws Exception {
        String coin=coinService.getTop50CoinByMarketCapRank();
        JsonNode jsonNode=objectMapper.readTree(coin);
        return ResponseEntity.ok(jsonNode);



    }
    @GetMapping("/treading")
    public ResponseEntity<JsonNode> getTradingCoins( ) throws Exception {
        String coin=coinService.getTradingCoin();
        JsonNode jsonNode=objectMapper.readTree(coin);
        return ResponseEntity.ok(jsonNode);



    }
    @GetMapping("/details/{coinId}")
    public ResponseEntity<JsonNode> getCoinDetails(@PathVariable String coinId  ) throws Exception {
        String coin=coinService.getCoinDetails(coinId);
        JsonNode jsonNode=objectMapper.readTree(coin);
        return ResponseEntity.ok(jsonNode);



    }


}
