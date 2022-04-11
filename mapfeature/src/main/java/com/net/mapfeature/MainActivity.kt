package com.net.mapfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.net.mapfeature.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding
    private lateinit var manager: SplitInstallManager
    private val listener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        val names = state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                //  In order to see this, the application has to be uploaded to the Play Store.
                displayLoadingState(state, "Downloading $names")
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                /*
                  This may occur when attempting to download a sufficiently large module.
                  In order to see this, the application has to be uploaded to the Play Store.
                  Then features can be requested until the confirmation path is triggered.
                 */
                startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
            }
            SplitInstallSessionStatus.INSTALLED -> {
                onSuccessfulLoad(names, launch = !multiInstall)
            }

            SplitInstallSessionStatus.INSTALLING -> displayLoadingState(state, "Installing $names")
            SplitInstallSessionStatus.FAILED -> {
                Toast.makeText(this, "Error: ${state.errorCode()} for module ${state.moduleNames()}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
        println("moduleName -> $moduleName")
    }

    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        displayProgress()
        vb.progressBar.max = state.totalBytesToDownload().toInt()
        vb.progressBar.progress = state.bytesDownloaded().toInt()
        updateProgressMessage(message)
    }

    private fun displayProgress() {
        vb.progressBar.visibility = View.VISIBLE
    }

    private fun updateProgressMessage(message: String) {
        if (vb.progressBar.visibility != View.VISIBLE) displayProgress()
        vb.progressText.text = message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        manager = SplitInstallManagerFactory.create(this)
        setContentView(vb.root)
    }

    override fun onResume() {
        super.onResume()
        manager.registerListener(listener)
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(listener)
    }

    private fun loadAndLaunchModule(name: String) {
        updateProgressMessage("Loading module $name")
        // Skip loading if the module already is installed. Perform success action directly.
        if (manager.installedModules.contains(name)) {
            updateProgressMessage("Already installed")
            onSuccessfulLoad(name, launch = true)
            return
        }

        // Create request to install a feature module by name.
        val request = SplitInstallRequest.newBuilder()
            .addModule(name)
            .build()

        // Load and install the requested feature module.
        manager.startInstall(request)

        updateProgressMessage("Starting install for $name")
    }
}