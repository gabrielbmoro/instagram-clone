package components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import colors.TextColor
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import instaclone.resources.MR
import layout.horizontalPadding

@Composable
fun UsernameInput(
    currentValue: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = currentValue,
        onValueChange = {
            onTextChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        placeholder = { Text(text = stringResource(MR.strings.placeholder_user_login)) },
        colors = getTextFieldColors(),
        shape = RoundedCornerShape(5.dp),
    )
}

@Composable
fun PasswordInput(
    currentValue: String,
    onTextChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibleClick: () -> Unit
) {
    val passwordIcon = if (isPasswordVisible) MR.images.visibility_on else MR.images.visibility_off
    val contentDescriptionIcon =
        if (isPasswordVisible) MR.strings.password_visible else MR.strings.password_not_visible
    val transformation: VisualTransformation =
        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

    TextField(
        value = currentValue, onValueChange = {
            onTextChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        placeholder = { Text(text = stringResource(MR.strings.placeholder_user_password)) },
        colors = getTextFieldColors(),
        shape = RoundedCornerShape(5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = transformation,
        trailingIcon = {
            IconButton(onClick = {
                onPasswordVisibleClick()
            }) {
                Icon(
                    painterResource(passwordIcon),
                    tint = Color.Gray,
                    contentDescription = stringResource(contentDescriptionIcon)
                )
            }
        }
    )
}

@Composable
private fun getTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        backgroundColor = TextColor.BLACK_10A.color,
        placeholderColor = TextColor.BLACK_20A.color,
        textColor = TextColor.DARK_GRAY.color,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        cursorColor = TextColor.BLACK_20A.color,
    )
}