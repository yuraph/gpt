package com.gpengtao.service;

import com.gpengtao.interfaces.ISayHiable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by gpengtao on 3/29/15.
 */
@Service
public class SayHiService implements ISayHiable {

    @Override
    public void sayHi() {
        System.out.println("say hi service say hi !");
    }
}
