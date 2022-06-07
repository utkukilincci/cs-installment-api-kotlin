package base

import LogUtils.Companion.logError
import LogUtils.Companion.logInfo
import LogUtils.Companion.logWarn
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult

class TestListener : ITestListener {
    override fun onTestStart(iTestResult: ITestResult) {
        try {
            logInfo("TEST STARTED: { ${iTestResult.name} }")
        } catch (ex: ArrayIndexOutOfBoundsException) {
            logError("Cannot log test name!")
        }
    }

    override fun onStart(p0: ITestContext) {
    }

    override fun onTestSuccess(iTestResult: ITestResult) {
        logInfo("Test Finished Successfully: ${iTestResult.name}")
    }

    override fun onTestFailure(iTestResult: ITestResult) {
        logError("Test Failed: ${iTestResult.name}")
    }

    override fun onTestFailedButWithinSuccessPercentage(p0: ITestResult) {
    }

    override fun onFinish(p0: ITestContext) {
    }

    override fun onTestSkipped(iTestResult: ITestResult) {
        if (iTestResult.parameters.isEmpty()) {
            logWarn("Test Has Been Skipped: ${iTestResult.name}")
        } else {
            iTestResult.status = 2
            iTestResult.parameters = arrayOf<String>()
        }
    }
}
