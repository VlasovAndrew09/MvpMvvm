package ru.vlasov.mvp_mvvm.ui

import ru.vlasov.mvp_mvvm.domain.FoxRepository
import ru.vlasov.mvp_mvvm.domain.ResultFox
import ru.vlasov.mvp_mvvm.utils.Observer

class FoxViewModel(
    private val foxRepository: FoxRepository
) {

    val showLoading = Observer<Boolean>()
    val urlImageFox = Observer<String?>()
    val textError = Observer<String?>()

    fun getFox() {
        showLoading.post(true)
        textError.post(null)
        foxRepository.getFox { result ->
            showLoading.post(false)
            when(result) {
                is ResultFox.Success -> {
                    result.data?.let { urlImageFox.post(it.urlImageFox) }
                }
                is ResultFox.Error -> {
                    urlImageFox.post(null)
                    result.message?.let { textError.post(it) }
                }
            }
        }
    }

    fun removeAllObservers() {
        showLoading.removeAllObservers()
        urlImageFox.removeAllObservers()
        textError.removeAllObservers()
    }
}