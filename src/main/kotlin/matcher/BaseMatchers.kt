package matcher

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import serviceBase.ReadableResponse

class BaseMatchers {

    companion object {

        fun shouldStatusCodeSameAs(statusCode: Int): Matcher<ReadableResponse> {
            return object : BaseMatcher<ReadableResponse>() {
                private lateinit var response: ReadableResponse
                override fun matches(item: Any): Boolean {
                    response = item as ReadableResponse
                    return response.statusCode() == statusCode
                }

                override fun describeTo(description: Description) {
                    description.appendText("Status code should be $statusCode")
                }

                override fun describeMismatch(item: Any, description: Description) {
                    description.appendText(
                        " displayed as: $response.statusCode() , error message: $response.fullBodyMessage()",
                    )
                }
            }
        }

        fun shouldResponseContainsMessageAs(path: String, message: String): Matcher<ReadableResponse> {
            return object : BaseMatcher<ReadableResponse>() {
                private lateinit var response: ReadableResponse
                override fun matches(item: Any): Boolean {
                    response = item as ReadableResponse
                    return response.bodyMessage(path).contains(message)
                }

                override fun describeTo(description: Description) {
                    description.appendText("Response should have $path as, $message")
                }

                override fun describeMismatch(item: Any, description: Description) {
                    description.appendText(" displayed as, ").appendValue(response.bodyMessage("."))
                }
            }
        }
    }
}
