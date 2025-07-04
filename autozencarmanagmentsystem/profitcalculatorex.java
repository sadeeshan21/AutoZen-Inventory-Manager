/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autozencarmanagmentsystem;

import java.math.BigDecimal;

/**
 *
 * @author malsh
 */
public class profitcalculatorex  extends Calculator{
    
     @Override
    public BigDecimal calculate(BigDecimal earnings, BigDecimal spends) {
        return earnings.subtract(spends);
    }
}
