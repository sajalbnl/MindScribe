package com.example.mindscribe.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.example.mindscribe.ui.theme.MindScribeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.mindscribe.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.mindscribe.utils.publicsansBold
import com.example.mindscribe.utils.publicsansRegular
import com.example.mindscribe.utils.publicsansSemiBold
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


class IntroScreensActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent(){
            MindScribeTheme {
                ImageSlider()
            }
        }
    }
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ImageSlider() {
        val context= LocalContext.current as Activity

        val images = listOf(
            R.drawable.intro_img_2,
            R.drawable.intro_img_1,
            R.drawable.intro_img_3
        )
        val titles = listOf("Journaling Made Easy", "Understand Your Mood", "Ready to Begin?")
        val descriptions = listOf(
            "Write journal entries with rich text formatting, categorize them with tags, and revisit your memories anytime.",
            "Log your daily mood, analyze trends, and gain insights into your emotional well-being over time.",
            "Start writing today and build a habit of self-reflection."
        )

        val pagerState = rememberPagerState(0)
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)).padding(20.dp), // Dark background
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState, modifier = Modifier.weight(1f),
                count = images.size,
                reverseLayout = false,
                itemSpacing = 10.dp,
                contentPadding = PaddingValues(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = images[page]),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .border(border = BorderStroke(0.dp, Color(0xFF121212)),shape = RoundedCornerShape(40.dp)),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = titles[page],
                        fontSize = 22.sp,
                        color = Color.White,
                        fontFamily = publicsansBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = descriptions[page],
                        fontSize = 16.sp,
                        fontFamily = publicsansRegular,
                        color = Color.Gray,
                        textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Indicator with dynamic size
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                images.indices.forEach { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp).height(10.dp)
                            .width(if (pagerState.currentPage == index) 40.dp else 10.dp)
                            .background(if (pagerState.currentPage == index) Color(0xFF6A5ACD) else Color.Gray, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(){
                if(pagerState.currentPage >  0){
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                        ),
                        modifier = Modifier.padding(16.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(30.dp),
                        onClick = {
                            coroutineScope.launch {
                                if (pagerState.currentPage > 0) {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        }
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                top = 10.dp,
                                bottom = 10.dp
                            ),
                            fontFamily = publicsansSemiBold,
                            text = "Back",
                            color = Color("#ffffff".toColorInt()),
                            fontSize = 15.sp
                        )

                    }

                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A5ACD),
                    ),
                    modifier = Modifier.padding(16.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(30.dp),
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage < images.size - 1) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }else{
                                Intent(context, CreateProfileActivity::class.java).also {
                                    startActivity(it)
                                }

                            }
                        }
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 10.dp
                        ),
                        fontFamily = publicsansSemiBold,
                        text = "Next",
                        color = Color("#ffffff".toColorInt()),
                        fontSize = 15.sp
                    )

                }
            }

        }
    }
}