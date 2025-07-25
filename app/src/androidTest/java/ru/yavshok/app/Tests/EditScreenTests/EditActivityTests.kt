package ru.yavshok.app.Tests.EditScreenTests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Pages.EditScreenPage
import ru.yavshok.app.Pages.LoginScreenPage
import ru.yavshok.app.Pages.MainScreenPage
import ru.yavshok.app.Pages.ProfileScreenPage
import ru.yavshok.app.Pages.RegisterScreenPage
import ru.yavshok.app.Utils.testData
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class EditScreenActivityTests {

    @get:Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var profileScreen: ProfileScreenPage
    private lateinit var editScreen: EditScreenPage

    @Before
    fun setUp(){

        mainScreen = MainScreenPage(composeRuleActivity)
        loginScreen = LoginScreenPage(composeRuleActivity)
        registerScreen = RegisterScreenPage(composeRuleActivity)
        profileScreen = ProfileScreenPage(composeRuleActivity)
        editScreen = EditScreenPage(composeRuleActivity)

    }

    @Test
    fun allViewInEditPage(){
        mainScreen
            .timeoutFotTitle()
            .clickLoginButton()
        loginScreen
            .timeoutTitle()
            .inputEmail(testData.VALID_EMAIL)
            .inputPassword(testData.VALID_PASSWORD)
            .clickButtonInShock()
        profileScreen
            .timeoutForUserName()
            .displayedUserName()
            .clickEditButton()
        editScreen
            .displayedTitle()
            .displayedLineName()
            .displayedNameTextField()
            .displayedButtonSave()
            .displayedCancel()
            .clickButtonCancel()
        profileScreen
            .clickLogoutButton()
    }

    @Test
    fun placeholderNameTextField(){
        mainScreen
            .timeoutFotTitle()
            .clickLoginButton()
        loginScreen
            .timeoutTitle()
            .inputEmail(testData.VALID_EMAIL)
            .inputPassword(testData.VALID_PASSWORD)
            .clickButtonInShock()
        profileScreen
            .timeoutForUserName()
            .displayedUserName()
            .clickEditButton()
        editScreen
            .timeoutTitle()
            .displayedLineName()
            .displayedNameTextField()
            .placeholderNameTextField()
    }

    @Test
    fun shouldNavigateFromEditScreenCancelToProfileScreen(){
        mainScreen
            .timeoutFotTitle()
            .clickLoginButton()
        loginScreen
            .timeoutTitle()
            .inputEmail(testData.VALID_EMAIL)
            .inputPassword(testData.VALID_PASSWORD)
            .clickButtonInShock()
        profileScreen
            .timeoutForUserName()
            .displayedUserName()
            .clickEditButton()
        editScreen
            .timeoutTitle()
            .displayedLineName()
            .displayedNameTextField()
            .displayedCancel()
            .clickButtonCancel()
        profileScreen
            .timeoutForUserName()
            .displayedUserName()
            .clickLogoutButton()
    }

    @Test
    fun renameProfileInEditPage() {
        val fakerRU = Faker(Locale("ru"))
        val fakerJA = Faker(Locale("ja"))
        val fakerAR = Faker(Locale("ar"))
        val faker = Faker()
        val nameValid = testData.VALID_NAME
        val listName = listOf(
            faker.name().fullName(),
            fakerJA.name().fullName(),
            fakerAR.name().fullName(),
            fakerRU.name().fullName() + "\uD83E\uDD7A"
        )
        for (name in listName) {
            mainScreen
                .timeoutFotTitle()
                .clickLoginButton()
            loginScreen
                .timeoutTitle()
                .inputEmail(testData.VALID_EMAIL)
                .inputPassword(testData.VALID_PASSWORD)
                .clickButtonInShock()
            profileScreen
                .timeoutForUserName()
                .displayedUserName()
                .clickEditButton()
            editScreen
                .timeoutTitle()
                .displayedLineName()
                .displayedNameTextField()
                .clearNameTextField()
                .inputName(name)
                .displayedButtonSave()
                .clickButtonSave()
            profileScreen
                .timeoutForUserName()
                .displayedUserName()
                .nameLineHasName(name)
                .clickEditButton()
        }
        editScreen
            .timeoutTitle()
            .displayedLineName()
            .displayedNameTextField()
            .clearNameTextField()
            .inputName(nameValid)
            .displayedButtonSave()
            .clickButtonSave()
        profileScreen
            .timeoutForUserName()
            .displayedUserName()
            .nameLineHasName(nameValid)
            .clickLogoutButton()
    }
}