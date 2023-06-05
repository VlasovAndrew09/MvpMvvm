package ru.vlasov.mvp_mvvm.ui

import ru.vlasov.mvp_mvvm.domain.FoxRepository
import ru.vlasov.mvp_mvvm.domain.ResultFox

class FoxPresenter(
    private val foxRepository: FoxRepository
) : FoxContract.Presenter {

    private var view: FoxContract.View? = null
    private var urlImageFox: String? = null
    private var textError: String? = null

    override fun onAttach(view: FoxContract.View) {
        this.view = view
        urlImageFox?.let {
            view.showFox(it)
        }
        textError?.let {
            view.showError(it)
        }
    }

    override fun onDetach() {
        view = null
    }

    override fun getFox() {
        view?.showLoading()
        view?.hideError()
        textError = null
        foxRepository.getFox { result ->
            view?.hideLoading()
            when(result) {
                is ResultFox.Success -> {
                    urlImageFox = result.data?.urlImageFox
                    urlImageFox?.let { view?.showFox(it) }
                }
                is ResultFox.Error -> {
                    view?.hideFox()
                    textError = result.message
                    textError?.let { view?.showError(it) }
                    urlImageFox = null
                }
            }
        }
    }
}