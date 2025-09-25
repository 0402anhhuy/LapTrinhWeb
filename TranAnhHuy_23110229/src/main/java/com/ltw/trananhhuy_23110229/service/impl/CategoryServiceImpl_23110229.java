package com.ltw.trananhhuy_23110229.service.impl;

import com.ltw.trananhhuy_23110229.entity.Category_23110229;
import com.ltw.trananhhuy_23110229.repository.CategoryRepository_23110229;
import com.ltw.trananhhuy_23110229.service.CategoryService_23110229;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl_23110229 implements CategoryService_23110229 {

    private final CategoryRepository_23110229 categoryRepository;

    @Override
    public List<Category_23110229> findAll_23110229() {
        return categoryRepository.findByStatus_23110229True();
    }
}
