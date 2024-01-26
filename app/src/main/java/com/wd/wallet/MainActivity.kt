package com.wd.wallet

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.android.relay.ConnectionType
import com.walletconnect.web3.wallet.client.Wallet
import com.walletconnect.web3.wallet.client.Web3Wallet



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val projectId = "" // Get Project ID at https://cloud.walletconnect.com/
        val relayUrl = "relay.walletconnect.com"
        val serverUrl = "wss://$relayUrl?projectId=$projectId"
        val connectionType = ConnectionType.AUTOMATIC
        val appMetaData = Core.Model.AppMetaData(
            name = "Wallet Name",
            description = "Wallet Description",
            url = "Wallet URL",
            icons =listOf("R.mipmap.ic_launcher")/*list of icon url strings*/,
            redirect = "kotlin-wallet-wc:/request" // Custom Redirect URI
        )

        CoreClient.initialize(relayServerUrl = serverUrl, connectionType = connectionType, application = this, metaData = appMetaData)

        val initParams = Wallet.Params.Init(core = CoreClient)

        Web3Wallet.initialize(initParams) { error ->
            // Error will be thrown if there's an issue during initialization
        }




    }
}

private fun CoreClient.initialize(relayServerUrl: String, connectionType: ConnectionType, application: MainActivity, metaData: Core.Model.AppMetaData) {

}
