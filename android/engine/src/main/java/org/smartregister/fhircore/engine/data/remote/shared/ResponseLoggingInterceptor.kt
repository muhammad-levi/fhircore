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

package org.smartregister.fhircore.engine.data.remote.shared

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

class ResponseLoggingInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request: Request = chain.request()
    val response = chain.proceed(request)
    val body = response.body
    if (body != null) {
      val contentEncoding = response.header(CONTENT_ENCODING)
      if (GZIP == contentEncoding) {
        Timber.e("GZIP enabled")
      } else {
        Timber.e("GZIP disabled")
      }
    }
    return response
  }

  companion object {
    const val GZIP = "gzip"
    const val CONTENT_ENCODING = "content-encoding"
  }
}
