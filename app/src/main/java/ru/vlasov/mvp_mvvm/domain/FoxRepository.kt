package ru.vlasov.mvp_mvvm.domain

import ru.vlasov.mvp_mvvm.domain.entities.FoxEntity

interface FoxRepository {

    fun getFox(callback: (ResultFox<FoxEntity>) -> Unit)
}