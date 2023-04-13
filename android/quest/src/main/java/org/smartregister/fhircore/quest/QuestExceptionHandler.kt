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

import android.content.Context
import android.content.Intent
import android.os.Process
import android.os.Process.killProcess
import androidx.compose.material.ExperimentalMaterialApi
import kotlin.system.exitProcess
import org.smartregister.fhircore.engine.util.extension.showToast
import org.smartregister.fhircore.quest.ui.main.AppMainActivity

class QuestExceptionHandler(val context: Context) : Thread.UncaughtExceptionHandler {

  @OptIn(ExperimentalMaterialApi::class)
  override fun uncaughtException(thread: Thread, throwable: Throwable) {
    with(context) {
      showToast("A problem occurred!")
      startActivity(Intent(this, AppMainActivity::class.java))
    }

    // Make sure we die, otherwise the app will hangs got nothing to do with AppMainActivity ...
    killProcess(Process.myPid())
    exitProcess(0)
  }
}
