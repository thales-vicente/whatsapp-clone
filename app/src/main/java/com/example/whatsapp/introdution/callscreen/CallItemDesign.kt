package com.example.whatsapp.introdution.callscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R

@Composable

fun CallItemDesign(call: Call) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(call.image),
            contentDescription = "Name",
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(call.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_call_missed),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if (call.isMissed) Color.Red else colorResource(R.color.light_green)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(call.time, color = Color.Gray, fontSize = 16.sp)
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(
                    painter = painterResource(R.drawable.ic_call),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}