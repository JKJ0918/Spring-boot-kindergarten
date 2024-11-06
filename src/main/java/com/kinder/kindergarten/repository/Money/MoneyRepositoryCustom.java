package com.kinder.kindergarten.repository.Money;

import com.kinder.kindergarten.DTO.Money.MoneySearchDTO;
import com.kinder.kindergarten.entity.Money.MoneyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MoneyRepositoryCustom {

    Page<MoneyEntity> getMoneyPage(MoneySearchDTO moneySearchDTO, Pageable pageable);
}
