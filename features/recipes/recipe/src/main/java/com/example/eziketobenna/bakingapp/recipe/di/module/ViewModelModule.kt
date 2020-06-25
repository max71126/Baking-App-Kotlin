package com.example.eziketobenna.bakingapp.recipe.di.module

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.core.di.mapkeys.ViewModelKey
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@DisableInstallInCheck
@Module
interface ViewModelModule {

    @get:[Binds IntoMap ViewModelKey(RecipeViewModel::class)]
    val RecipeViewModel.recipeViewModel: ViewModel
}
