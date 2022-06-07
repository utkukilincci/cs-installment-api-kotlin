package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    @SerialName("code")
    var code: String? = null,

    @SerialName("name")
    var name: String? = null,

    @SerialName("price")
    var price: PriceModel? = null,

    @SerialName("installment")
    var installment: Boolean? = null,

    @SerialName("installmentText")
    var installmentText: String? = null,

    @SerialName("productGroupId")
    var productGroupId: Int? = null,

    @SerialName("variantCode")
    var variantCode: String? = null,

    @SerialName("deliveryChargeMessage")
    var deliveryChargeMessage: String? = null,
)

@Serializable
data class PriceModel(
    @SerialName("current")
    var current: Float? = null,

    @SerialName("total")
    var total: Float? = null,

    @SerialName("currency")
    var currency: String? = null,

    @SerialName("currencyCode")
    var currencyCode: String? = null
)