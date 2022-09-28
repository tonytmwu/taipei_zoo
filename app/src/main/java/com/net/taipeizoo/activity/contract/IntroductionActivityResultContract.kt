package com.net.taipeizoo.activity.contract

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.net.taipeizoo.activity.IntroductionActivity

class IntroductionActivityResultContract:
    ActivityResultContract<IntroductionActivityResultContract.Input, Void?>() {

    companion object {
        val EXTRA_DATA_TYPE = "dataType"
        val EXTRA_DATA = "data"
    }

    data class Input(val zooDataType: String, val data: String)

    override fun createIntent(context: Context, input: Input): Intent {
        return Intent(context, IntroductionActivity::class.java).apply {
            this.putExtra(EXTRA_DATA_TYPE, input.zooDataType)
            this.putExtra(EXTRA_DATA, input.data)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Void? {
        return null
    }

}