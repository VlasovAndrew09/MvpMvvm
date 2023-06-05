package ru.vlasov.mvp_mvvm.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.vlasov.mvp_mvvm.app
import ru.vlasov.mvp_mvvm.databinding.ActivityFoxBinding

class FoxActivity : AppCompatActivity(), FoxContract.View {

    private lateinit var binding: ActivityFoxBinding
    private lateinit var presenter: FoxContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = getPresenter()
        presenter.onAttach(this)

        binding.buttonGetFox.setOnClickListener {
            presenter.getFox()
        }
    }

    private fun getPresenter(): FoxPresenter {
        val presenter = lastCustomNonConfigurationInstance as? FoxPresenter
        return presenter ?: FoxPresenter(app.foxRepository)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    override fun showLoading() {
        binding.buttonGetFox.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.buttonGetFox.isEnabled = true
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun showFox(urlImageFox: String) {
        binding.imageFox.visibility = View.VISIBLE
        Glide.with(this)
            .load(urlImageFox)
            .into(binding.imageFox)
    }

    override fun hideFox() {
        binding.imageFox.visibility = View.INVISIBLE
    }

    override fun showError(error: String) {
        binding.textError.visibility = View.VISIBLE
        binding.textError.text = error
    }

    override fun hideError() {
        binding.textError.visibility = View.INVISIBLE
        binding.textError.text = ""
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}