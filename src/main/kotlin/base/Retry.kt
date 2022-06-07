package base

import org.testng.IRetryAnalyzer
import org.testng.ITestResult

class Retry : IRetryAnalyzer {
    companion object {
        private const val maxTry: Int = 2
    }

    private var count = 0
    override fun retry(result: ITestResult): Boolean {
        if (!result.isSuccess) {
            when {
                count < maxTry -> {
                    count++
                    return true
                }
                else -> {
                    result.status = ITestResult.FAILURE
                }
            }
        } else {
            result.status = ITestResult.SUCCESS
        }
        return false
    }
}
