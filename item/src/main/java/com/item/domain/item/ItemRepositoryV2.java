package com.item.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {

    @Override
    Optional<Item> findById(Long aLong);

    @Override
    List<Item> findAll();

    @Override
    <S extends Item> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    void deleteAll();
}
