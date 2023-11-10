package com.bensek.topheadlines.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bensek.topheadlines.R
import com.bensek.topheadlines.domain.model.Article

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    article: Article
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder_image)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = article.title,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))
        if (article.description != null) {
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Volutpat est velit egestas dui id. Mattis pellentesque id nibh tortor id aliquet lectus proin. Eget mi proin sed libero enim. Vitae tempus quam pellentesque nec. Vitae elementum curabitur vitae nunc sed velit dignissim. Egestas sed tempus urna et pharetra pharetra massa massa ultricies. Lorem donec massa sapien faucibus. Consequat semper viverra nam libero justo laoreet sit. Quis risus sed vulputate odio ut enim blandit. Sit amet risus nullam eget felis. Nunc lobortis mattis aliquam faucibus. Parturient montes nascetur ridiculus mus mauris vitae ultricies leo integer.\n" +
                        "\n" +
                        "Enim nulla aliquet porttitor lacus. Nam libero justo laoreet sit amet cursus. Morbi quis commodo odio aenean sed adipiscing diam donec adipiscing. Tellus integer feugiat scelerisque varius morbi enim nunc faucibus a. Pharetra convallis posuere morbi leo. Rhoncus aenean vel elit scelerisque mauris pellentesque pulvinar. Diam quis enim lobortis scelerisque fermentum. Lectus magna fringilla urna porttitor. Rhoncus mattis rhoncus urna neque viverra justo. Dolor purus non enim praesent elementum facilisis.\n" +
                        "\n" +
                        "Lorem dolor sed viverra ipsum nunc aliquet bibendum enim. Urna nunc id cursus metus aliquam eleifend mi in. Tellus molestie nunc non blandit massa enim nec. Pellentesque nec nam aliquam sem et tortor consequat id porta. Vulputate eu scelerisque felis imperdiet proin. Faucibus a pellentesque sit amet porttitor eget dolor morbi. Interdum posuere lorem ipsum dolor sit. Non consectetur a erat nam at. Pretium lectus quam id leo. In nisl nisi scelerisque eu ultrices vitae auctor eu.\n" +
                        "\n" +
                        "Nunc sed blandit libero volutpat sed cras. Adipiscing at in tellus integer feugiat scelerisque. Mauris in aliquam sem fringilla ut morbi tincidunt augue. Mauris ultrices eros in cursus turpis massa tincidunt. Morbi tincidunt augue interdum velit euismod. Netus et malesuada fames ac turpis. Laoreet sit amet cursus sit amet dictum sit amet justo. Viverra orci sagittis eu volutpat odio facilisis mauris sit. Et malesuada fames ac turpis egestas integer eget aliquet. Habitant morbi tristique senectus et netus et. Ipsum nunc aliquet bibendum enim facilisis gravida neque convallis a.\n" +
                        "\n" +
                        "Proin gravida hendrerit lectus a. Vulputate eu scelerisque felis imperdiet. Aliquet enim tortor at auctor urna nunc. Viverra nam libero justo laoreet. Tellus integer feugiat scelerisque varius morbi enim nunc faucibus. Ac turpis egestas maecenas pharetra convallis. Eget est lorem ipsum dolor sit amet. Ipsum suspendisse ultrices gravida dictum fusce ut placerat orci nulla. Malesuada nunc vel risus commodo viverra maecenas accumsan lacus vel. Volutpat ac tincidunt vitae semper quis lectus nulla at. Volutpat sed cras ornare arcu dui vivamus arcu felis. Iaculis urna id volutpat lacus laoreet non curabitur. Sapien faucibus et molestie ac feugiat sed lectus vestibulum. Nascetur ridiculus mus mauris vitae ultricies leo integer malesuada nunc. Ut consequat semper viverra nam libero. Ornare suspendisse sed nisi lacus sed. Magna ac placerat vestibulum lectus mauris ultrices eros in.",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
