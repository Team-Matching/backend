package com.cbnu.teammatching.common;

import com.cbnu.teammatching.category.domain.Category;
import com.cbnu.teammatching.category.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor
//public class DataInitializer {
//
//    private final CategoryRepository categoryRepository;
//
//    @PostConstruct
//    public void init() {
//        Category category1 = new Category("프로젝트");
//        Category category2 = new Category("취미");
//        Category category3 = new Category("스터디");
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
//    }
//}
