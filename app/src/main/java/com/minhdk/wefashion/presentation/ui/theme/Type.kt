package com.minhdk.wefashion.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.minhdk.wefashion.R

val Poppins = FontFamily(

    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_thin_italic, FontWeight.Thin, FontStyle.Italic),

    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),

    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_light_italic, FontWeight.Light, FontStyle.Italic),

    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),

    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_medium_italic, FontWeight.Medium, FontStyle.Italic),

    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),

    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_bold_italic, FontWeight.Bold, FontStyle.Italic),

    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),

    Font(R.font.poppins_black, FontWeight.Black),
    Font(R.font.poppins_black_italic, FontWeight.Black, FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(

    // DISPLAY
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Black,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Black,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),

    // HEADLINE
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        lineHeight = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),

    // TITLE
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 22.sp
    ),

    // BODY
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),

    // LABEL
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp
    )
)