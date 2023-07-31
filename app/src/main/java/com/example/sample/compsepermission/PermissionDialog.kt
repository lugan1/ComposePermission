package com.example.sample.compsepermission

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(modifier.height(70.dp)) {
                Divider()
                Text(
                    text = if(isPermanentlyDeclined) {
                        "권한이 필요합니다. 설정에서 권한을 허용해주세요."
                    } else {
                        "권한이 필요합니다. 권한을 허용해주세요."
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToAppSettingsClick()
                            } else {
                                onConfirm()
                            }
                        }
                        .padding(16.dp)
                    )
            }
        },
        title = {
                Text(text = "권한이 필요합니다.")
        },
        text = {
               Text(text = permissionTextProvider.getDescription(isPermanentlyDeclined))
        },
        modifier = modifier
    )
}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class CameraPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined) {
            "당신은 카메라 권한을 영구적으로 거부한 것 같습니다. 앱 설정으로 이동하여 권한을 허용할 수 있습니다."
        } else {
            "이 앱은 친구들이 영상통화에서 당신을 볼 수 있도록 카메라에 대한 접근 권한이 필요합니다."
        }
    }
}

class RecordAudioPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined) {
            "당신은 음성마이크 권한을 영구적으로 거부한 것 같습니다. 앱 설정으로 이동하여 권한을 허용할 수 있습니다."
        } else {
            "이 앱은 친구들이 영상통화에서 당신의 음성을 들을 수 있도록 음성마이크에 대한 접근 권한이 필요합니다."
        }
    }
}

class PhoneCallPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined) {
            "전화 걸기 권한을 영구적으로 거부한 것 같습니다. 앱 설정으로 이동하여 권한을 허용할 수 있습니다."
        } else {
            "이 앱은 친구들과 대화할 수 있도록 전화 걸기 권한이 필요합니다."
        }
    }

}