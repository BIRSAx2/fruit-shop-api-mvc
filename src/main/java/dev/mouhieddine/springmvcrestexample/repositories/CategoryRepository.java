package dev.mouhieddine.springmvcrestexample.repositories;

import dev.mouhieddine.springmvcrestexample.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
