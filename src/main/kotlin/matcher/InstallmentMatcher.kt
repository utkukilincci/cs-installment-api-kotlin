package matcher

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import models.InstallmentModel
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.json.JSONObject
import serviceBase.ReadableResponse

class InstallmentMatcher {
    @ExperimentalSerializationApi
    companion object {
        fun shouldExistInstallmentTrueProducts(): Matcher<ReadableResponse> {
            return object : BaseMatcher<ReadableResponse>() {
                var actualInstallmentData: InstallmentModel? = null
                var expectedInstallmentData: InstallmentModel? = null

                override fun matches(item: Any): Boolean {
                    val response: ReadableResponse = item as ReadableResponse
                    var isInstallment = true
                    var isProductGroupId = true
                    var isInstallmentTextTrue = true

                    actualInstallmentData = Json.decodeFromString(response.fullBodyMessage())
                    
                    actualInstallmentData!!.result!!.data!!.products!!.forEach{  productModel ->
                        isInstallment = productModel.installment!!
                        isProductGroupId = productModel.productGroupId!! == 1
                        isInstallmentTextTrue = !productModel.installmentText.isNullOrEmpty()

                        if (!(isInstallment && isInstallmentTextTrue && isProductGroupId)) {
                            return false
                        }
                    }

                    return true
                }

                override fun describeTo(description: Description) {
                    description.appendText(
                        "describeTo -> \n" +
                            "response is incorrect -> \n" +
                            "${JSONObject(Json.encodeToJsonElement(actualInstallmentData).toString())}"
                    )
                }

                override fun describeMismatch(item: Any, description: Description) {
                    description.appendText(
                        "describeMismatch -> \n" +
                            "shouldExistInstallmentTrueProducts Mismatch !!!"
                    )
                }
            }
        }

        fun shouldExistInstallmentFalseProducts(): Matcher<ReadableResponse> {
            return object : BaseMatcher<ReadableResponse>() {
                var actualInstallmentData: InstallmentModel? = null
                var expectedInstallmentData: InstallmentModel? = null

                override fun matches(item: Any): Boolean {
                    val response: ReadableResponse = item as ReadableResponse
                    var isInstallment = true
                    var isProductGroupId = true
                    var isInstallmentTextTrue = true

                    actualInstallmentData = Json.decodeFromString(response.fullBodyMessage())

                    actualInstallmentData!!.result!!.data!!.products!!.forEach{  productModel ->
                        isInstallment = !productModel.installment!!
                        isProductGroupId = productModel.productGroupId!! == 2
                        isInstallmentTextTrue = productModel.installmentText.isNullOrEmpty()

                        if (!(isInstallment && isInstallmentTextTrue && isProductGroupId)) {
                            return false
                        }
                    }

                    return true
                }

                override fun describeTo(description: Description) {
                    description.appendText(
                        "describeTo -> \n" +
                            "response is incorrect -> \n" +
                            "${JSONObject(Json.encodeToJsonElement(actualInstallmentData).toString())}"
                    )
                }

                override fun describeMismatch(item: Any, description: Description) {
                    description.appendText(
                        "describeMismatch -> \n" +
                            "shouldExistInstallmentTrueProducts Mismatch !!!"
                    )
                }
            }
        }
    }
}