package com.example.learningartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningartspace.ui.theme.LearningArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainArtScreen()
                }
            }
        }
    }
}
@Composable
fun MainArtScreen(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) }
    var artImage=
        when(counter){
            0->R.drawable.salt
            1->R.drawable.fasilmosque
            2->R.drawable.suhawa
            else->R.drawable.harappa
        }
    var title=
        when(counter){
            0->R.string.salt
            1->R.string.fasil
            2->R.string.suhawa
            else->R.string.harappa
        }
    var year=
        when(counter){
            0->R.string.salt_year
            1->R.string.fasil_year
            2->R.string.suhawa_year
            else->R.string.harappa_year
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        PictureView(
            modifier = Modifier,
            placeImage = artImage
        )
        Spacer(Modifier.weight(1f))
        BottomDetailView(title=title, year = year, onPrevious = {
            if (counter!=0){
                counter-=1
            }
        }, onNext = {
            if (counter!=3){
                counter+=1
            }
        })
    }
}

@Composable
fun BottomDetailView(modifier: Modifier = Modifier,@StringRes title:Int,@StringRes year:Int , onPrevious: () -> Unit, onNext: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        DetailText(title=title, year=year)
        Spacer(modifier = Modifier.height(8.dp))
        BottomButtons(onPrevious = onPrevious, onNext = onNext)
    }
}

@Composable
fun DetailText(modifier: Modifier = Modifier, @StringRes title:Int,@StringRes year:Int,) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(year),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Composable
fun BottomButtons(modifier: Modifier = Modifier, onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier.weight(1f).padding(end = 4.dp)
        ) {
            Text("Previous")
        }

        Button(
            onClick = onNext,
            modifier = Modifier.weight(1f).padding(start = 4.dp)
        ) {
            Text("Next")
        }
    }
}

@Composable
fun PictureView(modifier: Modifier = Modifier, @DrawableRes placeImage:Int) {
    Card(
        modifier = modifier
            .width(360.dp)
            .aspectRatio(1f)
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Image(
            painter = painterResource(placeImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ArtScreen() {
    LearningArtSpaceTheme {
        MainArtScreen()
    }
}
