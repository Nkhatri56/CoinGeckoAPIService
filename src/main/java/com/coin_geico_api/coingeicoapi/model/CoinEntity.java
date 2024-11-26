package com.coin_geico_api.coingeicoapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Data
public class CoinEntity {
    @Id
        @JsonProperty("id")
        private String id;

        @JsonProperty("symbol")
        private String symbol;

        @JsonProperty("name")
        private String name;

        @JsonProperty("image")
        private String image;

        @JsonProperty("current_price")
        private BigDecimal currentPrice;

        @JsonProperty("market_cap")
        private Long marketCap;

        @JsonProperty("market_cap_rank")
        private Integer marketCapRank;

        @JsonProperty("fully_diluted_valuation")
        private Long fullyDilutedValuation;

        @JsonProperty("total_volume")
        private Long totalVolume;

        @JsonProperty("high_24h")
        private BigDecimal high24h;

        @JsonProperty("low_24h")
        private BigDecimal low24h;

        @JsonProperty("price_change_24h")
        private BigDecimal priceChange24h;

        @JsonProperty("price_change_percentage_24h")
        private BigDecimal priceChangePercentage24h;

        @JsonProperty("market_cap_change_24h")
        private Long marketCapChange24h;

        @JsonProperty("market_cap_change_percentage_24h")
        private BigDecimal marketCapChangePercentage24h;

        @JsonProperty("circulating_supply")
        private BigDecimal circulatingSupply;

        @JsonProperty("total_supply")
        private BigDecimal totalSupply;

        @JsonProperty("max_supply")
        private BigDecimal maxSupply;

        @JsonProperty("ath")
        private BigDecimal ath;

        @JsonProperty("ath_change_percentage")
        private BigDecimal athChangePercentage;

        @JsonProperty("ath_date")
        private LocalDateTime athDate;

        @JsonProperty("atl")
        private BigDecimal atl;

        @JsonProperty("atl_change_percentage")
        private BigDecimal atlChangePercentage;

        @JsonProperty("atl_date")
        private LocalDateTime atlDate;

        @JsonProperty("roi")
        @JsonIgnore
        private String roi;

        @JsonProperty("last_updated")
        private LocalDateTime lastUpdated;

        // Getters and setters can be added here
    }

