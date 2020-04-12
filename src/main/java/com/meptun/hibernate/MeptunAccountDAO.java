/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.MeptunAccount;
import java.util.List;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public interface MeptunAccountDAO {
    public void saveMeptunAccount(MeptunAccount ma);
    public void deleteMeptunAccount(MeptunAccount ma);
    public void updateMeptunAccount(MeptunAccount ma);
    public List<MeptunAccount> listMeptunAccount();
    
    default public void close(){};
}
