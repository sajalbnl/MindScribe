package com.example.mindscribe.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.core.view.WindowCompat
import com.example.mindscribe.ui.theme.MindScribeTheme
import com.example.mindscribe.utils.publicsansBold
import com.example.mindscribe.utils.publicsansSemiBold


class CreateProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent(){
            MindScribeTheme {
                ProfileScreenView()
            }
        }
    }

    @Composable
    fun ProfileScreenView(){

        Column(modifier = Modifier.fillMaxSize().background(Color("#333333".toColorInt())).padding(30.dp)) {
            Text(
                "Customize your profile",
                fontFamily = publicsansBold,
                fontSize = 24.sp,
                color = Color("#ffffff".toColorInt()),
            )
            Text(
                "Add your profile details below!",
                fontFamily = publicsansSemiBold,
                fontSize = 15.sp,
                color = Color("#b5b3b3".toColorInt()),
            )
            Spacer(modifier=Modifier.weight(1f))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color("#ed5ac3".toColorInt()),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 15.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {

                }
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    fontFamily = publicsansSemiBold,
                    text = "Continue",
                    color = Color("#ffffff".toColorInt()),
                    fontSize = 15.sp
                )

            }

        }
    }
}

