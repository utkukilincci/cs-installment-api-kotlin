package base

import org.testng.ITestContext
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Listeners

@Listeners(TestListener::class)
open class BaseTest {
    @BeforeSuite(alwaysRun = true)
    open fun beforeSuite(context: ITestContext) {
        for (method in context.suite.allMethods) {
            method.retryAnalyzerClass = Retry::class.java
        }
    }
}
