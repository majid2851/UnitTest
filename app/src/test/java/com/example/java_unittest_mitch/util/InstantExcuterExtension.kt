package com.example.java_unittest_mitch.util

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext

class InstantExcuterExtension :AfterEachCallback,BeforeEachCallback
{
    override fun afterEach(context: ExtensionContext?)
    {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    override fun beforeEach(context: ExtensionContext?)
    {
         ArchTaskExecutor.getInstance().setDelegate(object :TaskExecutor()
         {
             override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
             }

             override fun postToMainThread(runnable: Runnable) {
                runnable.run()
             }

             override fun isMainThread(): Boolean {
                return true
             }
         })
    }


}