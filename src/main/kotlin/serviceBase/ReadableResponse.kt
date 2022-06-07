package serviceBase

import io.restassured.response.Response
import org.json.JSONObject
import java.util.LinkedHashMap

class ReadableResponse() {
    lateinit var response: Response
    var statusLine: String = ""
    var statusCode: Int = 0

    constructor(response: Response) : this() {
        this.statusLine = response.statusLine
        this.statusCode = response.statusCode
        this.response = response
    }

    fun bodyMessage(path: String): String {
        return response.path<Any>(path).toString()
            .replace("[", "")
            .replace("]", "")
    }

    fun statusCode(): Int {
        return statusCode
    }

    fun fullBodyMessage(): String {
        return response.print()
    }

    fun isPathExist(path: String): Boolean {
        return response.path<String>(path) != null
    }

    fun bodyJsonMessageList(path: String): List<JSONObject> {
        return response.body().jsonPath().getJsonObject(path)
    }

    fun bodyStringMessageList(path: String): List<String> {
        return response.body().jsonPath().getList(path)
    }

    fun bodyMessageLinkedList(path: String): List<LinkedHashMap<String, Any>> {
        return response.body().jsonPath().getList(path)
    }
}
