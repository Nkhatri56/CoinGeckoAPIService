package com.coin_geico_api.coingeicoapi.repositories;

import com.coin_geico_api.coingeicoapi.model.CoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinReposotory extends JpaRepository<CoinEntity, String> {
}
