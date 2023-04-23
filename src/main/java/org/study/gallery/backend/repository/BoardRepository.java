package org.study.gallery.backend.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.gallery.backend.entity.Item;

@Repository
public interface BoardRepository extends JpaRepository <Item, Integer> {
        //검색리스트
        Page<Item> findByNameContaining  (String searchKeyword, Pageable pageable);

}
