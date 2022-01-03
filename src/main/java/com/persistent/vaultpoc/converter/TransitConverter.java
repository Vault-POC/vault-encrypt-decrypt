package com.persistent.vaultpoc.converter;


import javax.persistence.AttributeConverter;

import com.persistent.vaultpoc.service.BeanUtil;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

public class TransitConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String text) {
        VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
        Plaintext plaintext = Plaintext.of(text);
        String cipherText = vaultOps.opsForTransit().encrypt("user", plaintext).getCiphertext();
        return cipherText;
    }

    @Override
    public String convertToEntityAttribute(String text) {
        VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
        Ciphertext ciphertext = Ciphertext.of(text);
        String plaintext = vaultOps.opsForTransit().decrypt("user", ciphertext).asString();
        return plaintext;
    }

}
