package com.persistent.vaultpoc.converter;


import javax.persistence.AttributeConverter;

import com.persistent.vaultpoc.service.BeanUtil;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

public class TransitConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String customer) {
        VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
        Plaintext plaintext = Plaintext.of(customer);
        String cipherText = vaultOps.opsForTransit().encrypt("user", plaintext).getCiphertext();
        return cipherText;
    }

    @Override
    public String convertToEntityAttribute(String customer) {
        VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
        Ciphertext ciphertext = Ciphertext.of(customer);
        String plaintext = vaultOps.opsForTransit().decrypt("user", ciphertext).asString();
        return plaintext;
    }

}
