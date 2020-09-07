package com.hofinity.weatherito.presentation.ui.component.home.viewModels

import com.hofinity.weatherito.data.error.mapper.ErrorMapper
import com.hofinity.weatherito.domain.repository.remote.RemoteDataRepositorySource
import com.hofinity.weatherito.domain.usecase.errors.ErrorManager
import com.hofinity.weatherito.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel  @Inject
constructor() : BaseViewModel() {
    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())
}