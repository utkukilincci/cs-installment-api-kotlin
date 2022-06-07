package serviceBase

import LogUtils.Companion.logInfo
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.json.JSONArray
import org.json.JSONObject

open class BaseApiController(baseUrl: String?) {

    private var spec: RequestSpecification = RequestSpecBuilder().build()!!

    init {
        this.spec = RequestSpecBuilder()
            .setBaseUri(baseUrl.toString())
            .setBasePath("/")
            .setContentType(ContentType.JSON)
            .build()
    }

    companion object {
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json;charset=UTF-8"
    }

    // get-request

    fun getRequest(endPoint: String): ReadableResponse {
        val response = RestAssured.given()
            .spec(this.spec)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .`when`()[endPoint]
            .then()
            .extract()
            .response()

        return ReadableResponse(response)
    }

    // post-request

    fun postRequest(jsonObject: JSONObject, endPoint: String): ReadableResponse {
        val response = RestAssured.given()
            .spec(this.spec)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .body(jsonObject.toString())
            .`when`()
            .post(endPoint)
            .then()
            .extract()
            .response()

        return ReadableResponse(response)
    }

    // patch-request

    fun patchRequest(jsonObject: JSONObject, endPoint: String): ReadableResponse {
        val response = RestAssured.given()
            .spec(this.spec)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .body(jsonObject.toString())
            .`when`()
            .patch(endPoint)
            .then()
            .extract()
            .response()

        return ReadableResponse(response)
    }

    // put-request

    open fun putRequest(jsonObject: JSONObject, endPoint: String): ReadableResponse {
        val response = RestAssured.given()
            .spec(spec)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .body(jsonObject.toString())
            .`when`()
            .put(endPoint)
            .then()
            .extract()
            .response()

        return ReadableResponse(response)
    }
}
