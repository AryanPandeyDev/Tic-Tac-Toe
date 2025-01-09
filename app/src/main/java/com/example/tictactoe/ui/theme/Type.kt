package com.example.tictactoe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val OpenSansBold = FontFamily(
    Font(R.font.opensans_bold, FontWeight.Bold),
    Font(R.font.opensans_bolditalic, FontWeight.Bold)
)

val OpenSansCondensedBold = FontFamily(
    Font(R.font.opensans_condensed_bold, FontWeight.Bold),
    Font(R.font.opensans_condensed_bolditalic, FontWeight.Bold)
)

val OpenSansCondensedExtraBold = FontFamily(
    Font(R.font.opensans_condensed_extrabold, FontWeight.ExtraBold),
    Font(R.font.opensans_condensed_extrabolditalic, FontWeight.ExtraBold)
)

val OpenSansCondensedItalic = FontFamily(
    Font(R.font.opensans_condensed_italic, FontWeight.Normal)
)

val OpenSansCondensedLight = FontFamily(
    Font(R.font.opensans_condensed_light, FontWeight.Light),
    Font(R.font.opensans_condensed_lightitalic, FontWeight.Light)
)

val OpenSansCondensedMedium = FontFamily(
    Font(R.font.opensans_condensed_medium, FontWeight.Medium),
    Font(R.font.opensans_condensed_mediumitalic, FontWeight.Medium)
)

val OpenSansCondensedRegular = FontFamily(
    Font(R.font.opensans_condensed_regular, FontWeight.Normal)
)

val OpenSansCondensedSemiBold = FontFamily(
    Font(R.font.opensans_condensed_semibold, FontWeight.SemiBold),
    Font(R.font.opensans_condensed_semibolditalic, FontWeight.SemiBold)
)

val OpenSansExtraBold = FontFamily(
    Font(R.font.opensans_extrabold, FontWeight.ExtraBold),
    Font(R.font.opensans_extrabolditalic, FontWeight.ExtraBold)
)

val OpenSansItalic = FontFamily(
    Font(R.font.opensans_italic, FontWeight.Normal)
)

val OpenSansLight = FontFamily(
    Font(R.font.opensans_light, FontWeight.Light),
    Font(R.font.opensans_lightitalic, FontWeight.Light)
)

val OpenSansMedium = FontFamily(
    Font(R.font.opensans_medium, FontWeight.Medium),
    Font(R.font.opensans_mediumitalic, FontWeight.Medium)
)

val OpenSansRegular = FontFamily(
    Font(R.font.opensans_regular, FontWeight.Normal)
)

val OpenSansSemiBold = FontFamily(
    Font(R.font.opensans_semibold, FontWeight.SemiBold),
    Font(R.font.opensans_semibolditalic, FontWeight.SemiBold)
)

val OpenSansSemiCondensedBold = FontFamily(
    Font(R.font.opensans_semicondensed_bold, FontWeight.Bold),
    Font(R.font.opensans_semicondensed_bolditalic, FontWeight.Bold)
)

val OpenSansSemiCondensedExtraBold = FontFamily(
    Font(R.font.opensans_semicondensed_extrabold, FontWeight.ExtraBold),
    Font(R.font.opensans_semicondensed_extrabolditalic, FontWeight.ExtraBold)
)

val OpenSansSemiCondensedItalic = FontFamily(
    Font(R.font.opensans_semicondensed_italic, FontWeight.Normal)
)

val OpenSansSemiCondensedLight = FontFamily(
    Font(R.font.opensans_semicondensed_light, FontWeight.Light),
    Font(R.font.opensans_semicondensed_lightitalic, FontWeight.Light)
)

val OpenSansSemiCondensedMedium = FontFamily(
    Font(R.font.opensans_semicondensed_medium, FontWeight.Medium),
    Font(R.font.opensans_semicondensed_mediumitalic, FontWeight.Medium)
)

val OpenSansSemiCondensedRegular = FontFamily(
    Font(R.font.opensans_semicondensed_regular, FontWeight.Normal)
)

val OpenSansSemiCondensedSemiBold = FontFamily(
    Font(R.font.opensans_semicondensed_semibold, FontWeight.SemiBold),
    Font(R.font.opensans_semicondensed_semibolditalic, FontWeight.SemiBold)
)
