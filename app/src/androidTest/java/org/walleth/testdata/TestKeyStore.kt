package org.walleth.testdata

import org.kethereum.crypto.model.ECKeyPair
import org.kethereum.crypto.toAddress
import org.kethereum.model.Address
import org.walleth.data.keystore.WallethKeyStore

class TestKeyStore : WallethKeyStore {

    val addresses = mutableMapOf<Address, ECKeyPair>()

    override fun importKey(key: ECKeyPair, password: String): Address? {
        if (password == "bad password") {
            throw(IllegalArgumentException("Bad Password"))
        }
        val element = key.toAddress()
        addresses[element] = key
        return element
    }

    override fun deleteKey(address: Address): Boolean {
        addresses.remove(address)
        return true
    }

    override fun getKeyForAddress(address: Address, password: String) =  addresses[address]

    override fun getAddresses() = addresses.keys

    override fun hasKeyForForAddress(wallethAddress: Address) = addresses.contains(wallethAddress)

}