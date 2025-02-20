package com.example.mindscribe.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import com.example.mindscribe.ui.theme.MindScribeTheme
import com.example.mindscribe.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.google.accompanist.pager.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.delay

class IntroActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent(){
            MindScribeTheme {
                IntroScreenView()
            }
        }

    }

    @Composable
    fun IntroScreenView(){
        val images = listOf(
            R.drawable.intro_img_3, // Replace with actual images
            R.drawable.intro_img_1,
            R.drawable.intro_img_2
        )
        val pagerState = rememberPagerState(initialPage = images.size * 10)

        LaunchedEffect(pagerState) {
            while (true) {
                delay(3000) // Auto-scroll every 3 seconds
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0D0D0D)), // Dark background
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))


            HorizontalPager(
                state = pagerState,
                count = Int.MAX_VALUE, // Infinite scrolling
                contentPadding = PaddingValues(horizontal = 50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) { page ->
                val realPage = page % images.size
                val pageOffset = (pagerState.currentPage - page).toFloat()

                val scale = animateFloatAsState(
                    targetValue = if (pageOffset == 0f) 1.1f else 1f, label = ""
                )
                val alpha = animateFloatAsState(
                    targetValue = if (pageOffset == 0f) 1f else 0.5f, label = ""
                )
                val rotation = pageOffset * 15f // Tilt effect

                Image(
                    painter = rememberImagePainter(images[realPage]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(250.dp)
                        .scale(scale.value)
                        .alpha(alpha.value)
                        .graphicsLayer {
                            rotationZ = rotation
                        }
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Capture Your Thoughts, Every Day",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Stay mindful and organized by writing daily journal entries, tracking your mood, and reflecting on your journey.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            Button(
                onClick = { /* TODO: Navigate to next screen */ },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Get Started")
            }
        }
    }
}