package com.net.taipeizoo.repository

import android.content.Context
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert


@SmallTest
class RepositoryTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testZooOpenDataApi() {
        runBlocking {
            val resultAooArea = ZooDataService().fetchZooAreas()
            Assert.assertNotNull(resultAooArea)

            val resultZooPlant = ZooDataService().fetchZooPlant("蟲蟲探索谷")
            Assert.assertNotNull(resultZooPlant)
        }
    }

}