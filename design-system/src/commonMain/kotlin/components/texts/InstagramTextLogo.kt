package components.texts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import instaclone.resources.MR

@Composable
fun InstagramTextLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.padding(top = 60.dp, bottom = 40.dp),
        painter = painterResource(MR.images.instagram_text_logo),
        contentDescription = "Instagram text logo"
    )
}
