package com.iteneum.office.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iteneum.designsystem.components.LpOutlinedButton
import com.iteneum.designsystem.theme.LeasePertTheme
import com.iteneum.office.R
import com.iteneum.office.data.OfficeModel

/**
 * function: OfficeView  will handle:
 * Exposing to the UI the Office information,
 * working as container for OfficeUI function.
 * @author Andres Ivan Medina
 */

@Composable
fun OfficeView() {
    val viewModel = OfficeViewModel()

    LaunchedEffect(true) {
        viewModel.getOfficeInformation()
    }

    val OfficeData = viewModel.officeInfo

    OfficeData?.let {
        OfficeUI(it,
            onCallButtonClicked = { viewModel.makeCall() },
            onEmailButtonClicked = { viewModel.sendEmail() })
    }
}

/**
 * function: OfficeUI creates the Office screen UI
 * @param onCallButtonClicked launches the call intent
 * @param onEmailButtonClicked launches the Email intent
 * @author Yaritza Moreno
 * Modified by:
 * @author Andres Ivan Medina
 */
@Composable
fun OfficeUI(
    officeData: OfficeModel,
    onCallButtonClicked: () -> Unit,
    onEmailButtonClicked: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        val sizes = LeasePertTheme.sizes
        Text(
            text = stringResource(id = R.string.LPInfo),
            modifier = Modifier
                .width(sizes.extraSize124)
                .height(sizes.regularSize),
            style = TextStyle(
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.tertiary,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                textIndent = TextIndent(firstLine = 14.sp)
            )
        )

        Text(
            text = officeData.address,
            style = TextStyle(
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
                textIndent = TextIndent(firstLine = 14.sp, restLine = 3.sp)
            ),
            modifier = Modifier.padding(top = sizes.extraSize10)
        )

        Text(
            text = officeData.schedule,
            modifier = Modifier.padding(top = sizes.extraSize10),
            style = TextStyle(
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
                textIndent = TextIndent(firstLine = 14.sp, restLine = 3.sp)
            )
        )

        LpOutlinedButton(modifier = Modifier,
            icon = Icons.Filled.Call,
            textButton = stringResource(id = R.string.LPCallButton),
            onClicked = {
                onCallButtonClicked()
            })


        LpOutlinedButton(modifier = Modifier,
            icon = Icons.Outlined.Mail,
            textButton = stringResource(id = R.string.LPMailButton),
            onClicked = {
                onEmailButtonClicked()
            })
    }
}
