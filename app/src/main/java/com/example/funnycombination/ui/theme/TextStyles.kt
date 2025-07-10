package com.example.funnycombination.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object CustomTextStyles {
    val appTitle = TextStyle(
        fontFamily = FredokaOne,
        fontWeight = FontWeight.SemiBold,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        color = JuicyOrange,
        textAlign = TextAlign.Center
    )

    val appSubtitle = TextStyle(
        fontFamily = FredokaOne,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        color = SunshineYellow,
        textAlign = TextAlign.Center
    )

    val primaryButtonText = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.1.sp,
        color = DeepCharcoal
    )

    val secondaryButtonText = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
        color = SunshineYellow
    )

    val smallButtonText = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = DeepCharcoal,
        textAlign = TextAlign.Center
    )

    val levelIndicator = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        color = DeepCharcoal,
        textAlign = TextAlign.Center
    )

    val cardTitle = TextStyle(
        fontFamily = FredokaOne,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        color = DeepCharcoal,
        textAlign = TextAlign.Center
    )

    val ScoreResult = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 72.sp,
        color = SunshineYellow
    )

    val cardContent = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = DeepCharcoal,
        textAlign = TextAlign.Center
    )

    val dialogTitle = TextStyle(
        fontFamily = FredokaOne,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        color = DeepCharcoal,
        textAlign = TextAlign.Center
    )

    val dialogContent = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = DeepCharcoal,
        textAlign = TextAlign.Center
    )

    val menuItem = TextStyle(
        fontFamily = FredokaOne,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        color = DeepCharcoal,
        textAlign = TextAlign.Center
    )

    val tabText = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )

    val caption = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = DeepCharcoal.copy(alpha = 0.7f)
    )

    val hint = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = DeepCharcoal.copy(alpha = 0.6f),
        textAlign = TextAlign.Center
    )

    val errorText = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = androidx.compose.ui.graphics.Color.Red
    )

    val successText = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = androidx.compose.ui.graphics.Color.Green
    )
}

val Typography.custom: CustomTextStyles
    @Composable get() = CustomTextStyles