package org.smartregister.fhircore.quest.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.smartregister.fhircore.quest.ui.appsetting.AppSettingActivity
import org.smartregister.fhircore.quest.ui.login.LoginActivity

class LaunchActivityTest {
    @get:Rule
    val composeTestAppScreen = createAndroidComposeRule(AppSettingActivity::class.java)
    @get:Rule val composeTestRule = createAndroidComposeRule(LoginActivity::class.java)
    @Test
    fun applicationId() {
        composeTestAppScreen.onNodeWithText("Enter Application ID").performTextInput("demo")
        composeTestAppScreen.onNodeWithText("LOAD CONFIGURATIONS").performClick()
    }
    @Test
    fun successfulLogin() {
        composeTestRule.onNodeWithText("Enter username").performTextInput("demo")
        composeTestRule.onNodeWithText("Enter password").performTextInput("Amani123")
        composeTestRule.onNodeWithText("LOGIN").performClick()
    }
}