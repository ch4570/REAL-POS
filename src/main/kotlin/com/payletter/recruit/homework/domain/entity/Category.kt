package com.payletter.recruit.homework.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    val categoryId: Long? = null,

    @Column(name = "CATEGORY_NAME")
    val categoryName: String
) : BaseTimeEntity()