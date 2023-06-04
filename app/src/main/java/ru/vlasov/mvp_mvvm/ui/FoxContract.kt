package ru.vlasov.mvp_mvvm.ui

interface FoxContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showFox(urlImageFox: String)
        fun hideFox()
        fun showError(error: String)
        fun hideError()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun getFox()
    }
}