package ru.vlasov.mvp_mvvm.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.vlasov.mvp_mvvm.app
import ru.vlasov.mvp_mvvm.databinding.ActivityFoxBinding
import ru.vlasov.mvp_mvvm.utils.observe

class FoxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoxBinding
    private lateinit var viewModel: FoxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        binding.buttonGetFox.setOnClickListener {
            viewModel.getFox()
        }

        viewModel.showLoading.observe { showLoading ->
            if (showLoading == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        viewModel.urlImageFox.observe { urlImageFox ->
            if (urlImageFox == null) {
                hideFox()
            } else {
                showFox(urlImageFox)
            }
        }

        viewModel.textError.observe { textError ->
            if (textError == null) {
                hideError()
            } else {
                showError(textError)
            }
        }
    }

    private fun getViewModel(): FoxViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? FoxViewModel
        return viewModel ?: FoxViewModel(app.foxRepository)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return viewModel
    }

    private fun showLoading() {
        binding.buttonGetFox.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.buttonGetFox.isEnabled = true
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showFox(urlImageFox: String) {
        binding.imageFox.visibility = View.VISIBLE
        Glide.with(this)
            .load(urlImageFox)
            .into(binding.imageFox)
    }

    private fun hideFox() {
        binding.imageFox.visibility = View.INVISIBLE
    }

    private fun showError(error: String) {
        binding.textError.visibility = View.VISIBLE
        binding.textError.text = error
    }

    private fun hideError() {
        binding.textError.visibility = View.INVISIBLE
        binding.textError.text = ""
    }

    override fun onDestroy() {
        viewModel.removeAllObservers()
        super.onDestroy()
    }
}