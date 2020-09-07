package com.hofinity.weatherito.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.hofinity.weatherito.domain.usecase.errors.ErrorManager


/**
 *  @author Smooke
 */

abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    abstract val errorManager: ErrorManager

}
