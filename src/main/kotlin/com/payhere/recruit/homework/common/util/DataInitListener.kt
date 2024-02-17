package com.payhere.recruit.homework.common.util
import com.payhere.recruit.homework.product.domain.entity.CategoryJpaEntity
import com.payhere.recruit.homework.product.repository.CategoryRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 응용 프로그램 시작 시 데이터를 초기화하는 구성 요소 클래스입니다.
 *
 * @property categoryRepository 카테고리 데이터를 관리하는 CategoryRepository 인스턴스입니다.
 */
@Component
@Transactional
class DataInitListener(
    private val categoryRepository: CategoryRepository
) {

    /**
     * 응용 프로그램 시작 시 데이터를 초기화합니다.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun initData() {
        val coffeeCategory = CategoryJpaEntity(categoryName = "커피")
        val desertCategory = CategoryJpaEntity(categoryName = "디저트")

        categoryRepository.save(coffeeCategory)
        categoryRepository.save(desertCategory)
    }
}
