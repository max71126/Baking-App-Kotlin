package com.example.eziketobenna.bakingapp.recipedetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailStateMachine
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val detailStateMachine: RecipeDetailStateMachine
) : ViewModel() {

    val viewState: StateFlow<RecipeDetailViewState> = detailStateMachine.viewState

    init {
        detailStateMachine.processor.launchIn(viewModelScope)
    }

    fun processIntent(intents: Flow<RecipeDetailViewIntent>) {
        detailStateMachine
            .processIntents(intents)
            .launchIn(viewModelScope)
    }
}
