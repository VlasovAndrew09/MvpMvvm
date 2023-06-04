package ru.vlasov.mvp_mvvm.data.mapper

import ru.vlasov.mvp_mvvm.data.remote.model.FoxDto
import ru.vlasov.mvp_mvvm.domain.entities.FoxEntity

fun FoxDto.toFoxEntity() = FoxEntity(
    urlImageFox = image
)