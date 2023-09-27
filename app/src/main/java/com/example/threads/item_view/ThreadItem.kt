package com.example.threads.item_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.threads.R
import com.example.threads.model.ThreadModel
import com.example.threads.model.UserModel
import com.example.threads.ui.theme.ThreadsTheme
import com.example.threads.utils.SharedPref
import java.sql.Driver

@Composable
fun ThreadItem(
    thread: ThreadModel,
    users: UserModel,
    navHostController: NavHostController,
    userId: String
) {

    val context = LocalContext.current
    Column {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            val (userImage, userName, date, time,
                title, image) = createRefs()

            Image(painter =
            rememberAsyncImagePainter(model = users.imageUrl)
                , contentDescription = "Close",
                modifier = Modifier
                    .constrainAs(userImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(text = users.name
                , style = TextStyle(
                    fontSize = 20.sp), modifier = Modifier.constrainAs(userName){
                    top.linkTo(userImage.top)
                    start.linkTo(userImage.end, margin = 12.dp)
                    bottom.linkTo(userImage.bottom)}
            )

            Text(text = thread.thread
                , style = TextStyle(
                    fontSize = 18.sp), modifier = Modifier.constrainAs(title){
                    top.linkTo(userName.bottom, margin = 8.dp)
                    start.linkTo(userName.start) }
            )
            if (thread.image != ""){
                Card(modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(title.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Image(painter =
                                rememberAsyncImagePainter(model = thread.image)
                        , contentDescription = "Close",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }


        }

        Divider(color = Color.DarkGray, thickness = 1.dp)
    }

}




//@Preview(showBackground = true)
//@Composable
//fun ThreadItemPreview() {
//    ThreadsTheme {
//        ThreadItem()
//    }
//}