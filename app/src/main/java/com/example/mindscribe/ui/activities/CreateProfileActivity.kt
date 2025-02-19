package com.example.mindscribe.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.mindscribe.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.core.view.WindowCompat
import com.example.mindscribe.ui.theme.MindScribeTheme
import com.example.mindscribe.utils.publicsansBold
import com.example.mindscribe.utils.publicsansSemiBold


class CreateProfileActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent(){
            MindScribeTheme {
                ProfileScreenView()
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ProfileScreenView(){

        var name  = rememberSaveable { mutableStateOf("") }
        var lastName  = rememberSaveable { mutableStateOf("") }
        var nickName  = rememberSaveable { mutableStateOf("") }
        var selectedImage = rememberSaveable { mutableStateOf<Int?>(null) }

        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color("#333333".toColorInt()))
            .padding(30.dp))
        {
            Text(
                "Customize your profile",
                fontFamily = publicsansBold,
                fontSize = 26.sp,
                color = Color("#ffffff".toColorInt()),
            )
            Text(
                "Add your profile details below!",
                fontFamily = publicsansSemiBold,
                fontSize = 16.sp,
                color = Color("#b5b3b3".toColorInt()),
            )
            Spacer(modifier= Modifier.weight(1f))
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)){
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                            .clickable { /* Upload Profile Image */ },
                        contentAlignment = Alignment.Center
                    ) {
                        selectedImage.value?.let {
                            Image(
                                painter = painterResource(id = it),
                                contentDescription = "Selected Profile Picture",
                                modifier = Modifier.size(120.dp).clip(CircleShape)
                            )
                        } ?: Image(
                            painter = painterResource(id = android.R.drawable.ic_input_add),
                            contentDescription = "Upload",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier= Modifier.width(20.dp))
                    Column(){
                        Text(
                            text = "PICTURE IDEAS",
                            style = TextStyle(
                                color = Color("#b5b3b3".toColorInt()),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                            ),
                        )
                        FlowRow (modifier=Modifier.fillMaxWidth()){
                            val avatars = listOf(
                                R.drawable.avatar_1,
                                R.drawable.avatar_2,
                                R.drawable.avatar_3,
                                R.drawable.avatar_4,
                                R.drawable.avatar_5,
                                R.drawable.avatar_6
                            )
                            avatars.forEach { avatar ->
                                Image(
                                    painter = painterResource(id = avatar),
                                    contentDescription = "Avatar",
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .clickable { selectedImage.value = avatar }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier=Modifier.width(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                ) {
                    ProfileTextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        placeholder = "Name",
                        modifier = Modifier.weight(1f).padding(end = 5.dp)
                    )
                    ProfileTextField(
                        value = lastName.value,
                        onValueChange = { lastName.value = it },
                        placeholder = "Last Name",
                        modifier = Modifier.weight(1f).padding(start = 5.dp)
                    )
                }
                ProfileTextField(
                    value = nickName.value,
                    onValueChange = { nickName.value = it },
                    placeholder = "Nick Name"
                )
            }
            Spacer(modifier=Modifier.weight(1f))
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#ed5ac3".toColorInt()),
                    ),
                    modifier = Modifier.padding(16.dp)
                        .weight(1f),
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
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = modifier.fillMaxWidth().background(Color.Transparent),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(
            color = Color("#ffffff".toColorInt()),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    color = Color("#b5b3b3".toColorInt()),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                ),
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color("#545352".toColorInt()),
            focusedTextColor = Color("#ffffff".toColorInt()),
            unfocusedTextColor = Color.Transparent,
            focusedBorderColor = Color("#d0d5db".toColorInt()),
            unfocusedBorderColor = Color.Transparent
        )
    )
}

