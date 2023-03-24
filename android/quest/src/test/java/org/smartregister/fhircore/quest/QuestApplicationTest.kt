/*
 * Copyright 2021-2023 Ona Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.smartregister.fhircore.quest

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.smartregister.fhircore.quest.robolectric.RobolectricTest

@HiltAndroidTest
class QuestApplicationTest : RobolectricTest() {
  @get:Rule(order = 0) var hiltRule = HiltAndroidRule(this)
  private lateinit var application: QuestApplication

  @Before
  fun setUp() {
    hiltRule.inject()
    application = QuestApplication()
    application.referenceUrlResolver = mockk()
    application.xFhirQueryResolver = mockk()
    application.workerFactory = mockk()
  }

  @Test
  fun testGetDataCaptureConfig() {
    val config = application.getDataCaptureConfig()

    Assert.assertNotNull(config)
  }

  @Test
  fun testGetDataCaptureConfigWhenAlreadySet() {
    val config = application.getDataCaptureConfig()
    val config2 = application.getDataCaptureConfig()

    Assert.assertEquals(config, config2)
  }

  @Test
  fun testGetWorkManagerConfiguration() {
    val config = application.workManagerConfiguration

    Assert.assertNotNull(config)
  }
}