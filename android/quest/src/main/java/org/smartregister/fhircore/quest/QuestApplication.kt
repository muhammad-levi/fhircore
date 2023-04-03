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

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.database.CursorWindow
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.google.android.fhir.datacapture.DataCaptureConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import org.smartregister.fhircore.engine.data.remote.fhir.resource.ReferenceUrlResolver
import org.smartregister.fhircore.engine.util.extension.showToast
import org.smartregister.fhircore.quest.data.QuestXFhirQueryResolver
import org.smartregister.fhircore.quest.ui.questionnaire.QuestionnaireItemViewHolderFactoryMatchersProviderFactoryImpl
import timber.log.Timber

@HiltAndroidApp
class QuestApplication :
  Application(),
  DataCaptureConfig.Provider,
  Configuration.Provider,
  Application.ActivityLifecycleCallbacks {

  @Inject lateinit var workerFactory: HiltWorkerFactory

  @Inject lateinit var referenceUrlResolver: ReferenceUrlResolver

  @Inject lateinit var xFhirQueryResolver: QuestXFhirQueryResolver

  private var configuration: DataCaptureConfig? = null

  private var previousActivityReference: String? = null
  private var currentActivity: String? = null
  private var current: Activity? = null

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    if (BuildConfig.DEBUG) {
      Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler)
    }

    registerActivityLifecycleCallbacks(this)

    // TODO Fix this workaround for cursor size issue. Currently size set to 10 MB
    try {
      val field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
      field.apply {
        isAccessible = true
        set(null, 10 * 1024 * 1024) // 10MB
      }
    } catch (e: Exception) {
      Timber.e(e)
    }
  }

  override fun getDataCaptureConfig(): DataCaptureConfig {
    configuration =
      configuration
        ?: DataCaptureConfig(
          urlResolver = referenceUrlResolver,
          xFhirQueryResolver = xFhirQueryResolver,
          questionnaireItemViewHolderFactoryMatchersProviderFactory =
            QuestionnaireItemViewHolderFactoryMatchersProviderFactoryImpl
        )
    return configuration as DataCaptureConfig
  }

  override fun getWorkManagerConfiguration(): Configuration =
    Configuration.Builder()
      .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.VERBOSE else Log.INFO)
      .setWorkerFactory(workerFactory)
      .build()

  private val globalExceptionHandler =
    Thread.UncaughtExceptionHandler { _: Thread, e: Throwable -> handleUncaughtException(e) }

  /**
   * This method captures all uncaught exceptions in the app and redirects to the Launch Page in the
   * case that the exception was thrown on the main thread This will therefore prevent any app
   * crashes so we need some more handling for reporting the errors once we have a crash manager
   * installed
   *
   * TODO add crash reporting when a crash reporting tool is selected e.g. Fabric Crashlytics or
   * Sentry
   */
  private fun handleUncaughtException(e: Throwable) {
    showToast(this.getString(R.string.error_occurred))
    Timber.e(e)

    if (Looper.myLooper() == Looper.getMainLooper()) {

      val intent = Intent(applicationContext, getActivityClassByName(previousActivityReference!!))
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
      startActivity(intent)

      if (previousActivityReference != currentActivity.toString()) current?.finish()
    }
  }
  @Throws(java.lang.Exception::class)
  private fun getActivityClassByName(qualifiedActivityClassName: String): Class<out Activity?>? {
    val classLoader: ClassLoader = applicationContext.classLoader
    return classLoader.loadClass(qualifiedActivityClassName) as Class<out Activity?>
  }

  override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
    currentActivity = activity.localClassName
    current = activity
    Timber.d("###### onActivityLog Created $currentActivity")
  }

  override fun onActivityStarted(p0: Activity) {
    // Do nothing
  }

  override fun onActivityResumed(p0: Activity) {
    // Do nothing
  }

  override fun onActivityPaused(activity: Activity) {
    previousActivityReference = activity.localClassName
    Timber.d("###### onActivityLog Paused $previousActivityReference")
  }

  override fun onActivityStopped(p0: Activity) {
    // Do nothing
  }

  override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    // Do nothing
  }

  override fun onActivityDestroyed(p0: Activity) {
    // Do nothing
  }
}
