/*
 * Copyright 2021 Ona Systems, Inc
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

package org.smartregister.fhircore.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.parser.IParser
import java.math.BigDecimal
import java.util.UUID
import org.hl7.fhir.r4.model.BooleanType
import org.hl7.fhir.r4.model.Observation
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.Questionnaire
import org.hl7.fhir.r4.model.QuestionnaireResponse
import org.hl7.fhir.r4.model.RiskAssessment
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.robolectric.annotation.Config
import org.smartregister.fhircore.RobolectricTest
import org.smartregister.fhircore.shadow.FhirApplicationShadow

@Config(shadows = [FhirApplicationShadow::class])
class QuestionnaireUtilsTest : RobolectricTest() {
  private val context = ApplicationProvider.getApplicationContext<Context>()
  private lateinit var questionnaire: Questionnaire
  private lateinit var questionnaireResponse: QuestionnaireResponse

  @Before
  fun setup() {
    val iParser: IParser = FhirContext.forR4().newJsonParser()

    val qJson =
      context.assets.open("patient-registration.json").bufferedReader().use { it.readText() }

    questionnaire = iParser.parseResource(qJson) as Questionnaire

    val qrJson =
      qJson.replace("Questionnaire", "QuestionnaireResponse").replace("status", "completed")

    questionnaireResponse = iParser.parseResource(qrJson) as QuestionnaireResponse
  }

  @Test
  fun testAsCodeableConcept_shouldReturnCorrectTextAndCodeSystemMapping() {
    val result =
      QuestionnaireUtils.asCodeableConcept(
        questionnaire.item[1].item.single { it.linkId.contentEquals("diabetes_mellitus") }
      )

    Assert.assertEquals("Diabetes Mellitus (DM)", result.text)

    Assert.assertEquals("73211009", result.coding[0].code)
    Assert.assertEquals("https://www.snomed.org", result.coding[0].system)

    Assert.assertEquals("diabetes_mellitus", result.coding[1].code)
    Assert.assertEquals(
      "http://hl7.org/fhir/StructureDefinition/Observation",
      result.coding[1].system
    )
  }

  @Test
  fun testAsObs_shouldReturnCorrectObsData() {
    val patient = Patient()
    patient.id = "1122"

    val qItem = questionnaire.item[1].item.single { it.linkId.contentEquals("diabetes_mellitus") }
    val qrItem =
      questionnaireResponse.item[1].item.single { it.linkId.contentEquals("diabetes_mellitus") }
    qrItem.addAnswer().value = BooleanType(true)

    val result = QuestionnaireUtils.asObs(qItem, qrItem, patient)

    Assert.assertEquals("Diabetes Mellitus (DM)", result.code.text)

    Assert.assertTrue(UUID.fromString(result.id).toString().isNotEmpty())
    Assert.assertTrue(result.effectiveDateTimeType.isToday)
    Assert.assertTrue(result.valueBooleanType.booleanValue())

    Assert.assertEquals("73211009", result.code.coding[0].code)
    Assert.assertEquals("https://www.snomed.org", result.code.coding[0].system)

    Assert.assertEquals("diabetes_mellitus", result.code.coding[1].code)
    Assert.assertEquals(
      "http://hl7.org/fhir/StructureDefinition/Observation",
      result.code.coding[1].system
    )

    Assert.assertEquals(Observation.ObservationStatus.FINAL, result.status)
    Assert.assertEquals("Patient/" + patient.id, result.subject.reference)
  }

  @Test
  fun testExtractObservation_shouldReturnValidObservations() {
    val patient = Patient()
    patient.id = "1122"

    // diabetes_mellitus
    questionnaireResponse.item[1].item[0].addAnswer().value = BooleanType(true)
    // hypertension
    questionnaireResponse.item[1].item[1].addAnswer().value = BooleanType(true)

    val result =
      QuestionnaireUtils.extractObservations(questionnaireResponse, questionnaire, patient)

    // obs1 with diabetes
    val obs1 = result[0]

    Assert.assertTrue(obs1.effectiveDateTimeType.isToday)
    Assert.assertTrue(obs1.valueBooleanType.booleanValue())

    Assert.assertEquals("73211009", obs1.code.coding[0].code)
    Assert.assertEquals("diabetes_mellitus", obs1.code.coding[1].code)
    Assert.assertEquals("Patient/" + patient.id, obs1.subject.reference)

    // obs2 with hypertension
    val obs2 = result[1]

    Assert.assertTrue(obs2.effectiveDateTimeType.isToday)
    Assert.assertTrue(obs2.valueBooleanType.booleanValue())

    Assert.assertEquals("59621000", obs2.code.coding[0].code)
    Assert.assertEquals("hypertension", obs2.code.coding[1].code)
    Assert.assertEquals("Patient/" + patient.id, obs2.subject.reference)

    // one group obs with hasMembers
    val main = result[2]
    Assert.assertEquals("Do you have any of the following conditions?", main.code.text)
    Assert.assertEquals("991381000000107", main.code.coding[0].code)
    Assert.assertEquals("https://www.snomed.org", main.code.coding[0].system)
    Assert.assertEquals("Observation/" + obs1.id, main.hasMember[0].reference)
    Assert.assertEquals("Observation/" + obs2.id, main.hasMember[1].reference)
  }

  @Test
  fun testExtractRiskAssessment_shouldReturnValidObservations() {
    val patient = Patient()
    patient.id = "1122"

    // diabetes_mellitus
    questionnaireResponse.item[1].item[0].addAnswer().value = BooleanType(true)
    // hypertension
    questionnaireResponse.item[1].item[1].addAnswer().value = BooleanType(true)

    val observations =
      QuestionnaireUtils.extractObservations(questionnaireResponse, questionnaire, patient)

    val risk = QuestionnaireUtils.extractRiskAssessment(observations, questionnaire)!!

    // obs1 with diabetes
    val obs1 = observations[0]
    Assert.assertEquals("diabetes_mellitus", obs1.code.coding[1].code)

    // obs2 with hypertension
    val obs2 = observations[1]
    Assert.assertEquals("hypertension", obs2.code.coding[1].code)

    Assert.assertEquals(obs1.subject.reference, risk.subject.reference)
    Assert.assertEquals("Patient/1122", risk.subject.reference)

    Assert.assertEquals(BigDecimal(2), risk.prediction[0].relativeRisk)
    Assert.assertEquals("Observation/" + obs1.id, risk.basis[0].reference)
    Assert.assertEquals("Observation/" + obs2.id, risk.basis[1].reference)

    Assert.assertTrue(risk.occurrence.dateTimeValue().isToday)
    Assert.assertEquals(RiskAssessment.RiskAssessmentStatus.FINAL, risk.status)

    Assert.assertEquals(
      "Client is at risk for serious illness from COVID-19",
      risk.prediction[0].outcome.text
    )

    Assert.assertEquals("711365007", risk.prediction[0].outcome.coding[0].code)
    Assert.assertEquals("https://www.snomed.org", risk.prediction[0].outcome.coding[0].system)

    Assert.assertEquals("high_risk", risk.prediction[0].outcome.coding[1].code)
    Assert.assertEquals(
      "http://hl7.org/fhir/StructureDefinition/RiskAssessment",
      risk.prediction[0].outcome.coding[1].system
    )
  }

  @Test
  fun testExtractFlag_shouldReturnValidExtensionWithData() {
    val patient = Patient()
    patient.id = "1122"

    // diabetes_mellitus
    questionnaireResponse.item[1].item[0].addAnswer().value = BooleanType(true)
    // hypertension
    questionnaireResponse.item[1].item[1].addAnswer().value = BooleanType(true)

    val observations =
      QuestionnaireUtils.extractObservations(questionnaireResponse, questionnaire, patient)

    val risk = QuestionnaireUtils.extractRiskAssessment(observations, questionnaire)!!

    val flag = QuestionnaireUtils.extractFlagExtension(questionnaire, risk)!!

    Assert.assertEquals("http://hl7.org/fhir/StructureDefinition/flag-detail", flag.url)
    Assert.assertEquals("at risk", flag.value.toString())
  }
}
