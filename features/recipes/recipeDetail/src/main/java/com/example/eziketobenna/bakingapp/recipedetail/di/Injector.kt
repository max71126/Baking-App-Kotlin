package com.example.eziketobenna.bakingapp.recipedetail.di

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.di.AppComponent
import com.example.eziketobenna.bakingapp.recipedetail.di.component.DaggerRecipeDetailComponent
import com.example.eziketobenna.bakingapp.recipedetail.ui.RecipeDetailFragment
import dagger.hilt.android.EntryPointAccessors

internal fun inject(fragment: RecipeDetailFragment) {
    DaggerRecipeDetailComponent
        .factory()
        .create(appComponent(fragment), coreComponent(fragment))
        .inject(fragment)
}

private fun appComponent(fragment: RecipeDetailFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )

private fun coreComponent(fragment: RecipeDetailFragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        CoreComponent::class.java
    )
