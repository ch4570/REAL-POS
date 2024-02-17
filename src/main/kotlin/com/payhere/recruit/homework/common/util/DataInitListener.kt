package com.payhere.recruit.homework.common.util
import com.payhere.recruit.homework.domain.entity.CategoryJpaEntity
import com.payhere.recruit.homework.repository.CategoryRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * Component class for initializing data upon application startup.
 *
 * @property categoryRepository The CategoryRepository instance for managing category data.
 */
@Component
@Transactional
class DataInitListener(
    private val categoryRepository: CategoryRepository
) {

    /**
     * Initializes data upon application startup.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun initData() {
        val coffeeCategory = CategoryJpaEntity(categoryName = "커피")
        val desertCategory = CategoryJpaEntity(categoryName = "디저트")

        categoryRepository.save(coffeeCategory)
        categoryRepository.save(desertCategory)
    }
}