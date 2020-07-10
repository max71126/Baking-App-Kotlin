package com.example.eziketobenna.bakingapp.recipe.presentation.data

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepository.Companion.ERROR_MSG
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState

internal object DummyData {

    val recipe = Recipe(
        id = 3,
        name = "Burritos",
        image = "imgurl.com",
        servings = 1,
        ingredients = listOf(ingredient),
        steps = listOf(step)
    )

    private val ingredient: Ingredient
        get() = Ingredient(
            quantity = 1.4,
            measure = "3",
            ingredient = "salt"
        )

    private val step: Step
        get() = Step(
            id = 1,
            description = "pour stuff",
            shortDescription = "pour",
            videoURL = "url.com",
            thumbnailURL = "thumb.com"
        )

    val recipeList: List<Recipe> = listOf(recipe)

    fun recipeModelList(recipeModelMapper: RecipeModelMapper): List<RecipeModel> =
        recipeModelMapper.mapToModelList(recipeList)
}

class DummyViewState(private val recipeModelMapper: RecipeModelMapper) {

    private val initialState: RecipeViewState
        get() = RecipeViewState.init

    private val loadedState: RecipeViewState
        get() = initialState.loadedState(DummyData.recipeModelList(recipeModelMapper))

    val loadInitialViewState: Array<RecipeViewState>
        get() = arrayOf(
            initialState,
            initialState.loadingState,
            initialState.loadingState.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
        )

    val refreshRecipesViewState: Array<RecipeViewState>
        get() = arrayOf(
            loadedState.refreshingState,
            loadedState.refreshingState.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
        )

    val retryFetchViewState: Array<RecipeViewState>
        get() = arrayOf(
            initialState.noDataErrorState(ERROR_MSG).loadingState,
            initialState.noDataErrorState(ERROR_MSG).loadingState.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
        )

    val emptyViewState: Array<RecipeViewState>
        get() = arrayOf(
            initialState,
            initialState.loadingState,
            initialState.loadingState.emptyState
        )

    val noDataErrorViewState: Array<RecipeViewState>
        get() = arrayOf(
            initialState,
            initialState.loadingState,
            initialState.loadingState.noDataErrorState(ERROR_MSG)
        )

    val dataErrorViewState: Array<RecipeViewState>
        get() = arrayOf(
            loadedState.loadingState,
            loadedState.loadingState.dataAvailableErrorState(ERROR_MSG)
        )
}
