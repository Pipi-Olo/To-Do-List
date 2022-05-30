package com.item.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Override
    Optional<Item> findById(Long itemId);

    @Override
    List<Item> findAll();

    @Override
    <S extends Item> S save(S Item);

    @Override
    void delete(Item item);

    @Override
    void deleteById(Long itemId);

    @Override
    void deleteAll();
}
