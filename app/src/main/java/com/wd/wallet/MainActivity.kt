package com.wd.wallet

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.android.relay.ConnectionType
import com.walletconnect.web3.wallet.client.Wallet
import com.walletconnect.web3.wallet.client.Web3Wallet



class MainActivity : AppCompatActivity() {
    //aadkajndjandknadjnjasdnasndjasndkjnasjkdnajksdnkajndjaksd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val projectId = "89b957fac364f290134f68c547f516e1" // Get Project ID at https://cloud.walletconnect.com/
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

//        CoreClient.initialize(metaData = appMetaData,relayServerUrl = serverUrl, connectionType = connectionType, application = this.application)

        val initParams = Wallet.Params.Init(core = CoreClient)

        Web3Wallet.initialize(initParams) { error ->
            // Error will be thrown if there's an issue during initialization
        }


        val anotherSupportedNamespaces = mapOf(
            "eip155" to Wallet.Model.Namespace.Session(
                chains = listOf("eip155:1", "eip155:2", "eip155:4"),
                methods = listOf("personal_sign", "eth_sendTransaction", "eth_signTransaction"),
                events = listOf("chainChanged", "accountsChanged"),
                accounts = listOf("eip155:1:0x57f48fAFeC1d76B27e3f29b8d277b6218CDE6092", "eip155:2:0x57f48fAFeC1d76B27e3f29b8d277b6218CDE6092", "eip155:4:0x57f48fAFeC1d76B27e3f29b8d277b6218CDE6092")
            ),
            "cosmos" to Wallet.Model.Namespace.Session(
                chains = listOf("cosmos:cosmoshub-4"),
                methods = listOf("cosmos_method"),
                events = listOf("cosmos_event"),
                accounts = listOf("cosmos:cosmoshub-4:cosmos1hsk6jryyqjfhp5dhc55tc9jtckygx0eph6dd02")
            )
        )
        val supportedNamespace = mapOf(
            "eip155" to Wallet.Model.Namespace.Session(
                chains = listOf("eip155:1", "eip155:137", "eip155:3"),
                methods = listOf("personal_sign", "eth_sendTransaction", "eth_signTransaction"),
                events = listOf("chainChanged"),
                accounts = listOf("eip155:1:0x57f48fAFeC1d76B27e3f29b8d277b6218CDE6092", "eip155:137:0x57f48fAFeC1d76B27e3f29b8d277b6218CDE6092", "eip155:3:0x57f48fAFeC1d76B27e3f29b8d277b6218CDE6092")
            )
        )
        // 在 onSessionProposal 方法中
        fun onSessionProposal(sessionProposal: Wallet.Model.SessionProposal) {
            // 处理 sessionProposal，其他逻辑...

        }

// 外部变量声明
        var sessionProposa: Wallet.Model.SessionProposal? = null

// 在需要使用 sessionProposal 的地方
        val sessionProposalInstance: Wallet.Model.SessionProposal? = sessionProposa

        val supportedNamespaces: Map<String, Wallet.Model.Namespace.Session> =anotherSupportedNamespaces /* a map of all supported namespaces created by a wallet */
        val sessionProposal: Wallet.Model.SessionProposal? = sessionProposalInstance /* an object received by `fun onSessionProposal(sessionProposal: Wallet.Model.SessionProposal)` in `Web3Wallet.WalletDelegate` */
        val sessionNamespaces =
            sessionProposal?.let { Web3Wallet.generateApprovedNamespaces(it, supportedNamespaces) }

//        val approveParams: Wallet.Params.SessionApprove = Wallet.Params.SessionApprove(proposerPublicKey, sessionNamespaces)
//        Web3Wallet.approveSession(approveParams) { error -> /*callback for error while approving a session*/ }







    }
}




