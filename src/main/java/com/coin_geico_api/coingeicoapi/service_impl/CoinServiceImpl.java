package com.coin_geico_api.coingeicoapi.service_impl;

import com.coin_geico_api.coingeicoapi.model.CoinEntity;
import com.coin_geico_api.coingeicoapi.repositories.CoinReposotory;
import com.coin_geico_api.coingeicoapi.service.CoinService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CoinServiceImpl implements CoinService {
    @Autowired
    private CoinReposotory coinReposotory;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public List<CoinEntity> getCoinList(int page) throws Exception {
String url="https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=10&page="+page;
        RestTemplate restTemplate=new RestTemplate();
        try{
            HttpHeaders headers =new HttpHeaders();
            HttpEntity<String> entity=new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET, entity,String.class);
            List<CoinEntity> coinList=objectMapper.readValue(response.getBody(), new TypeReference<List<CoinEntity>>() {

            });
            return coinList;
        }catch(HttpClientErrorException | HttpServerErrorException e)
        {
throw new Exception(e.getMessage());
        }


    }

    @Override
    public String getMarketChart(String coinId, int days) throws Exception {
        String url="https://api.coingecko.com/api/v3/coins/"+coinId+"/market_chart?vs_currency=usd&days="+days;
        RestTemplate restTemplate=new RestTemplate();
        try{
            HttpHeaders headers =new HttpHeaders();
            HttpEntity<String> entity=new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET, entity,String.class);

            return response.getBody();
        }catch(HttpClientErrorException | HttpServerErrorException e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getCoinDetails(String coinId) throws Exception {
        String url="https://api.coingecko.com/api/v3/coins/"+coinId;
        RestTemplate restTemplate=new RestTemplate();
        try{
            HttpHeaders headers =new HttpHeaders();
            HttpEntity<String> entity=new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET, entity,String.class);
            JsonNode jsonNode=objectMapper.readTree(response.getBody());
            CoinEntity coin=new CoinEntity();
            coin.setId(jsonNode.get("id").asText());
            coin.setName(jsonNode.get("name").asText());
            coin.setSymbol(jsonNode.get("symbol").asText());
            coin.setImage(jsonNode.get("image").get("large").asText());
            JsonNode marketData=jsonNode.get("market_data");
            coin.setCurrentPrice(BigDecimal.valueOf(marketData.get("current_price").get("usd").asDouble()));
            coin.setMarketCap(marketData.get("market_cap").asLong());
            coin.setMarketCapRank(marketData.get("market_cap_rank").asInt());
            coin.setTotalVolume(marketData.get("total_volume").get("usd").asLong());
            coin.setHigh24h(BigDecimal.valueOf(marketData.get("high24h").get("usd").asLong()));
            coin.setLow24h(BigDecimal.valueOf(marketData.get("low24h").get("usd").asLong()));
            coin.setPriceChange24h(BigDecimal.valueOf(marketData.get("price_change_24h").get("usd").asDouble()));
            coin.setPriceChangePercentage24h(BigDecimal.valueOf(marketData.get("price_change_percentage_24h").get("usd").asDouble()));
            coin.setMarketCapChangePercentage24h(BigDecimal.valueOf(marketData.get("market_cap_change_percentage_24h").asDouble()));
            coin.setMarketCapChange24h(marketData.get("market_cap_change_24h").get("usd").asLong());
            coin.setTotalSupply(BigDecimal.valueOf(marketData.get("total_supply").asDouble()));

            coinReposotory.save(coin);




            return response.getBody();
        }catch(HttpClientErrorException | HttpServerErrorException e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CoinEntity findById(String coinId) {
        Optional<CoinEntity> optional= coinReposotory.findById( coinId);
        return optional.orElseThrow(()-> new EntityNotFoundException("coin with this "+coinId+" is not found"));
    }

    @Override
    public String searchCoin(String keyword) throws Exception {
        String url="https://api.coingecko.com/api/v3/search?query="+keyword;
        RestTemplate restTemplate=new RestTemplate();
        try{
            HttpHeaders headers =new HttpHeaders();
            HttpEntity<String> entity=new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET, entity,String.class);



            return response.getBody();
        }catch(HttpClientErrorException | HttpServerErrorException e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getTop50CoinByMarketCapRank() throws Exception {
        String url="https://api.coingecko.com/api/v3/coins/markets/?vs_currency=usd&per_page=50&page="+1;
        RestTemplate restTemplate=new RestTemplate();
        try{
            HttpHeaders headers =new HttpHeaders();
            HttpEntity<String> entity=new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET, entity,String.class);



            return response.getBody();
        }catch(HttpClientErrorException | HttpServerErrorException e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getTradingCoin() throws Exception {
        String url="https://api.coingecko.com/api/v3/search/trading";
        RestTemplate restTemplate=new RestTemplate();
        try{
            HttpHeaders headers =new HttpHeaders();
            HttpEntity<String> entity=new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET, entity,String.class);



            return response.getBody();
        }catch(HttpClientErrorException | HttpServerErrorException e)
        {
            throw new Exception(e.getMessage());
        }
    }
}
