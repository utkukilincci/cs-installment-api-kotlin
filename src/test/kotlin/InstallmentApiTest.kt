import base.BaseTest
import base.Constants.INSTALLMENT
import base.Constants.NON_INSTALLMENT
import controller.InstallmentApiController
import matcher.BaseMatchers.Companion.shouldStatusCodeSameAs
import matcher.InstallmentMatcher.Companion.shouldExistInstallmentFalseProducts
import matcher.InstallmentMatcher.Companion.shouldExistInstallmentTrueProducts
import org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.MatcherAssert.assertThat
import org.testng.annotations.Test

class InstallmentApiTest: BaseTest() {
    private var installmentApiController = InstallmentApiController();

    @Test
    fun shouldGetInstallmentTrueProductsWithInstallmentParam1() {
        val response = installmentApiController.getInstallment(INSTALLMENT)

        assertThat(
            "When a user tries to get installment products, status code should be '200' ",
            response,
            shouldStatusCodeSameAs(SC_OK)
        )
        assertThat(
            "When a user tries to get installment products with instalment 1 param, should be able to see correct values for installment, productGroupId, installmentText ",
            response,
            shouldExistInstallmentTrueProducts()
        )
    }

    @Test
    fun shouldGetInstallmentFalseProductsWithInstallmentParam0() {
        val response = installmentApiController.getInstallment(NON_INSTALLMENT)

        assertThat(
            "When a user tries to get installment products, status code should be '200' ",
            response,
            shouldStatusCodeSameAs(SC_OK)
        )
        assertThat(
            "When a user tries to get installment products with instalment 0 param, should be able to see correct values for installment, productGroupId, installmentText ",
            response,
            shouldExistInstallmentFalseProducts()
        )
    }

    @Test
    fun shouldNotGetInstallmentProductsWithInstallmentNull() {
        val response = installmentApiController.getInstallment()

        assertThat(
            "When a user tries to get installment true products with null param, status code should be '500' ",
            response,
            shouldStatusCodeSameAs(SC_INTERNAL_SERVER_ERROR)
        )
    }
}