package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.LoadInitialViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.RecipeRefreshViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.RecipeRetryViewIntent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeViewIntentProcessorTest {

    private val recipeViewIntentProcessor = RecipeViewIntentProcessor()

    @Test
    fun `check that LoadInitialViewIntent is mapped to LoadInitialAction`() {
        val action: RecipeViewAction =
            recipeViewIntentProcessor.intentToAction(LoadInitialViewIntent)
        assertThat(action).isInstanceOf(LoadInitialAction::class.java)
    }

    @Test
    fun `check that RecipeRetryViewIntent is mapped to LoadInitialAction`() {
        val action: RecipeViewAction =
            recipeViewIntentProcessor.intentToAction(RecipeRetryViewIntent)
        assertThat(action).isInstanceOf(RetryFetchAction::class.java)
    }

    @Test
    fun `check that RecipeRefreshViewIntent is mapped to RecipeRefreshViewIntent`() {
        val action: RecipeViewAction =
            recipeViewIntentProcessor.intentToAction(RecipeRefreshViewIntent)
        assertThat(action).isInstanceOf(RefreshRecipesAction::class.java)
    }
}
