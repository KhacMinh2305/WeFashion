package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.R
import com.minhdk.wefashion.domain.data.onboarding.DtoOnboarding

object OnboardingRepository {

    private val items = listOf(
        DtoOnboarding(
            position = 0,
            title = "Various Collections Of The Latest Products",
            description = "Explore our curated collection of trendy clothing and accessories.",
            image = R.drawable.onboarding_1
        ),
        DtoOnboarding(
            position = 1,
            title = "Complete Collection Of Colors And Sizes",
            description = "Choose from a wide range of colors and sizes to perfectly match your style and fit.",
            image = R.drawable.onboarding_2
        ),
        DtoOnboarding(
            position = 2,
            title = "Find The Most Suitable Outfit For You",
            description = "Discover outfits tailored to your taste and make every look uniquely yours.",
            image = R.drawable.onboarding_3
        )
    )

    fun getOnboardingItems() = items

    fun getOnboardingItemByPosition(position: Int): DtoOnboarding? {
        return items.find { it.position == position }
    }

}