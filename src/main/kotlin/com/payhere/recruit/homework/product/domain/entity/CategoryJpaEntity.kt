package com.payhere.recruit.homework.product.domain.entity

import com.payhere.recruit.homework.common.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "CATEGORY")
class CategoryJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    val categoryId: Long? = null,

    @Column(name = "CATEGORY_NAME")
    val categoryName: String
) : BaseTimeEntity()