package com.project.crm.encryptor;

import com.project.crm.domain.User;
import org.junit.Assert;
import org.junit.Test;


public class EncryptorTest {

    @Test
    public void shouldEncodeText() {
        User u1 = new User(1, "maklukas", "pass", "fn", "ln", 1);
        System.out.println(u1.getPassword());
    }
}