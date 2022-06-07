package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstallmentModel(
    @SerialName("result")
    var result: InstallmentDatas? = null,

    @SerialName("error")
    var error: InstallmentError? = null,
) {
}

@Serializable
data class InstallmentDatas(
    @SerialName("data")
    var data: InstallmentData? = null
)

@Serializable
data class InstallmentData(
    @SerialName("categoryName")
    var categoryName: String? = null,

    @SerialName("products")
    var products: List<ProductModel>? = null,

    @SerialName("filter")
    var filter: String? = null,

    @SerialName("banners")
    var banners: String? = null,

    @SerialName("branch")
    var branch: String? = null,

    @SerialName("message")
    var message: String? = null,

    @SerialName("productCount")
    var productCount: Int? = null,

    @SerialName("title")
    var title: String? = null,

    @SerialName("isAlternate")
    var isAlternate: Boolean? = null,

    @SerialName("storeId")
    var storeId: Int? = null
)

@Serializable
data class InstallmentError(
    @SerialName("type")
    var type: Int? = null,

    @SerialName("title")
    var title: String? = null,

    @SerialName("message")
    var message: String? = null,

    @SerialName("returnUrl")
    var returnUrl: String? = null
)