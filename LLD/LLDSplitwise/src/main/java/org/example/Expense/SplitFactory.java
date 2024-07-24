package org.example.Expense;

import org.example.Expense.Split.EqualExpenseSplit;
import org.example.Expense.Split.ExpenseSplit;
import org.example.Expense.Split.PercentageExpenseSplit;
import org.example.Expense.Split.UnequalExpenseSplit;

public class SplitFactory {
    public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {
        switch(splitType) {
            case EQUAL: return new EqualExpenseSplit();
            case UNEQUAL: return new UnequalExpenseSplit();
            case PERCENTAGE: return new PercentageExpenseSplit();
            default: return null;
        }
    }
}
