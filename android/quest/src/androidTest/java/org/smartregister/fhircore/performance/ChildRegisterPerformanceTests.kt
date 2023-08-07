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

package org.smartregister.fhircore.performance

import androidx.benchmark.junit4.BenchmarkRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.smartregister.fhircore.engine.data.local.DefaultRepository
import org.smartregister.fhircore.engine.data.local.register.RegisterRepository

@HiltAndroidTest
class ChildRegisterPerformanceTests : BaseRegisterPerformanceTest() {

  @get:Rule val benchmarkRule = BenchmarkRule()

  @Inject lateinit var defaultRepository: DefaultRepository

  @Inject lateinit var registerRepository: RegisterRepository

  @get:Rule val hiltRule = HiltAndroidRule(this)

  @Before
  fun setUp() {
    hiltRule.inject()

    beforeTestSetup(registerRepository, benchmarkRule, "childRegister")
  }

  @Test
  fun benchmarkPage0() {
    benchmarkingFunctionality(0)
  }

  @Test
  fun benchmarkPage1() {
    benchmarkingFunctionality(1)
  }

  @Test
  fun benchmarkPage2() {
    benchmarkingFunctionality(2)
  }
}
