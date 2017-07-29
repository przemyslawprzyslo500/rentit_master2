/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.session;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 *
 * @author miszcz
 */

@Named(value = "myDate")
public class myDate {

    private Date currentDate;

    @PostConstruct
    public void init() {
        currentDate = new Date();
    }
}
