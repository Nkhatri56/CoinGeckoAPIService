package com.coin_geico_api.coingeicoapi.service;

import com.coin_geico_api.coingeicoapi.model.CoinEntity;

import java.util.List;

public interface CoinService {

    List<CoinEntity> getCoinList(int page) throws Exception;
    String getMarketChart(String coinId, int days) throws Exception;
    String getCoinDetails(String coinId ) throws Exception;
    CoinEntity findById(String coinId);
String searchCoin(String keyword) throws Exception;
String getTop50CoinByMarketCapRank() throws Exception;
String getTradingCoin() throws Exception;
}
