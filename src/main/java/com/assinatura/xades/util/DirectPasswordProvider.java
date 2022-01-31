package com.assinatura.xades.util;

import xades4j.providers.impl.KeyStoreKeyingDataProvider.KeyEntryPasswordProvider;
import xades4j.providers.impl.KeyStoreKeyingDataProvider.KeyStorePasswordProvider;

import java.security.cert.X509Certificate;

/**
 *
 * @author Luís
 */
public class DirectPasswordProvider implements KeyStorePasswordProvider, KeyEntryPasswordProvider{
    private char[] password;

    public DirectPasswordProvider(String password){
        this.password = password.toCharArray();
    }

    @Override
    public char[] getPassword(){
        return password;
    }

    @Override
    public char[] getPassword(String entryAlias, X509Certificate entryCert) {
        return password;
    }
}