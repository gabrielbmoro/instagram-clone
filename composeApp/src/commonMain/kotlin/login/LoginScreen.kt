package login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import colors.ButtonColor
import components.buttons.LoginButton
import components.inputs.PasswordInput
import components.inputs.UsernameInput
import components.misc.CustomDivider
import components.misc.LoginFooter
import components.texts.FacebookSignUpHyperlink
import components.texts.ForgotPasswordHyperlink
import components.texts.InstagramSignUpHyperlink
import components.texts.InstagramTextLogo
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import instaclone.resources.MR
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import layout.horizontalPadding


data class LoginUIState(
    val username: String = "",
    val password: String = "",
    var loginButtonBackgroundColor: ButtonColor = ButtonColor.BLUE_50A,
    var passwordVisibility: Boolean = false
)

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { LoginScreenModel() }
        val uiState by viewModel.uiState.collectAsState()
        LoginScreenContent(
            uiState = uiState,
            onUserNameChanged = viewModel::onUserNameChanged,
            onPasswordChanged = viewModel::onPasswordChanged,
            onLoginClick = viewModel::onLoginClick,
            onPasswordVisibleClick = viewModel::onPasswordVisibilityChange
        )
    }
}

class LoginScreenModel : ScreenModel {
    private val _state = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState>
        get() = _state
    private val inputFieldList = listOf(uiState.value.username, uiState.value.password)

    fun onUserNameChanged(userName: String) {
        _state.update {
            it.copy(
                username = userName,
                loginButtonBackgroundColor = updateLoginButtonColor(inputFieldList)
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(
                password = password,
                loginButtonBackgroundColor = updateLoginButtonColor(inputFieldList)
            )
        }
    }

    fun onLoginClick() {

    }

    fun onPasswordVisibilityChange() {
        _state.update {
            it.copy(
                passwordVisibility = !it.passwordVisibility
            )
        }
    }
}

@Composable
private fun LoginScreenContent(
    uiState: LoginUIState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onPasswordVisibleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(modifier = Modifier.padding(top = 10.dp)) {
            IconButton(onClick = {
                /* TODO ADD NAVIGATION ACTION */
            }) {
                Icon(
                    painterResource(MR.images.arrow_back),
                    contentDescription = stringResource(MR.strings.back_button)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InstagramTextLogo(modifier = Modifier.padding(top = 60.dp, bottom = 40.dp))
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                UsernameInput(
                    currentValue = uiState.username,
                    onTextChange = onUserNameChanged
                )

                Spacer(modifier = Modifier.size(15.dp))

                PasswordInput(
                    currentValue = uiState.password,
                    onTextChange = onPasswordChanged,
                    isPasswordVisible = uiState.passwordVisibility,
                    onPasswordVisibleClick = {
                        onPasswordVisibleClick()
                    }
                )

                Spacer(modifier = Modifier.size(15.dp))

                ForgotPasswordHyperlink(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = horizontalPadding)
                )
                LoginButton(
                    modifier = Modifier.fillMaxWidth().padding(horizontalPadding),
                    isEnabled = uiState.username.isNotBlank() && uiState.password.isNotBlank(),
                    onClick = onLoginClick
                )
                FacebookSignUpHyperlink(modifier = Modifier.padding(start = 10.dp))
                CustomDivider(
                    text = stringResource(MR.strings.or_divider),
                    rowModifier = Modifier.padding(vertical = 20.dp),
                    textModifier = Modifier.padding(10.dp)
                )
                InstagramSignUpHyperlink()
            }
        }
    }
    LoginFooter(modifier = Modifier.fillMaxWidth().padding(30.dp))
}

private fun updateLoginButtonColor(inputFields: List<String>): ButtonColor {
    val allFilled = inputFields.all { field -> field.isNotBlank() }
    return if (allFilled) ButtonColor.BLUE_100A else ButtonColor.BLUE_50A
}