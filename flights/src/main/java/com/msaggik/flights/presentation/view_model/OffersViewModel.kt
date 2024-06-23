package com.msaggik.flights.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaggik.flights.domain.api.OffersInteractor
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.presentation.view_model.state.OffersState

class OffersViewModel (
    private val offersInteractor: OffersInteractor
) : ViewModel() {

    init {
        readOffers()
    }

    private val stateLiveData = MutableLiveData<OffersState>()
    fun getStateLiveData(): LiveData<OffersState> = mediatorStateLiveData

    private val mediatorStateLiveData = MediatorLiveData<OffersState>().also { liveData ->
        liveData.addSource(stateLiveData) { state ->
            liveData.value = when (state) {
                is OffersState.Content -> OffersState.Content(state.offers)
                is OffersState.Empty -> state
                is OffersState.Error -> state
                is OffersState.Loading -> state
            }
        }
    }

    private fun readOffers() {
        offersInteractor.getOffers(object : OffersInteractor.OffersConsumer {
            override fun consume(listOffer: List<Offer>?, errorMessage: String?) {
                val offers = mutableListOf<Offer>()
                if (listOffer != null) {
                    offers.addAll(listOffer)
                }
                when {
                    errorMessage != null -> {
                        renderState(
                            OffersState.Error(
                                errorMessage = errorMessage,
                            )
                        )
                    }

                    offers.isEmpty() -> {
                        renderState(
                            OffersState.Empty
                        )
                    }

                    else -> {
                        renderState(
                            OffersState.Content(
                                offers = offers
                            )
                        )
                    }
                }
            }
        })
    }

    private fun renderState(state: OffersState) {
        stateLiveData.postValue(state)
    }
}