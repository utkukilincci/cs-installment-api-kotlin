package controller

import base.Constants.API_URL
import serviceBase.BaseApiController
import serviceBase.ReadableResponse

class InstallmentApiController: BaseApiController {
    constructor() : super(API_URL)

    fun getInstallment(installmentType: Int): ReadableResponse {
        return getRequest("/installment=$installmentType")
    }

    fun getInstallment(): ReadableResponse {
        return getRequest("/?installment=")
    }
}