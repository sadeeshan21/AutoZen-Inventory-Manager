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
public class FinanceReport {
    
   private final BigDecimal totalEarnings;
    private final BigDecimal totalSpends;

    public FinanceReport(String earningsInput, String spendsInput) {
        this.totalEarnings = parseExpression(earningsInput);
        this.totalSpends = parseExpression(spendsInput);
    }

    private BigDecimal parseExpression(String input) {
        BigDecimal total = BigDecimal.ZERO;

        if (input == null || input.isEmpty()) {
            return total;
        }

        String[] parts = input.split("\\+");
        for (String part : parts) {
            part = part.trim();
            if (!part.isEmpty()) {
                total = total.add(new BigDecimal(part));
            }
        }
        return total;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public BigDecimal getTotalSpends() {
        return totalSpends;
    }
}

/*✅ Encapsulation: Private fields inside the thread class.

✅ Abstraction: Calculator as an abstract class.

✅ Inheritance: ProfitCalculator extends Calculator.

✅ Polymorphism: Override of calculate() method.

✅ Exception Handling: Try-catch blocks.

✅ Threading: Calculation done in a background thread.*/

    

