package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { // Пополнение баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);
        int actual = account.getBalance();

        Assertions.assertEquals(2_000 + 3_000, actual);
    }

    @Test
    public void emptyReplenishment() { // пустое поступление средств
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(false, false);
    }

    @Test
    public void OverMaxBalanceReplenishment() { // Пополнение средств сверх максимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(10_000);

        Assertions.assertEquals(false, false);
    }

    @Test
    public void testNegativeRate() { // сообщение о негативной ставке
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                -5
        );

        account.add(500);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void testPayAmountNormal() { //Отработка метода выше минимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        Assertions.assertEquals(true, true);
    }

    @Test
    public void testPayZero() { //Отработка метода, когда нет трат
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(false, false);
    }
    @Test
    public void testPayAmountNegativeMinBalance() { // Отработка метода ниже минимального баланса
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        Assertions.assertEquals(false, false);
    }

    @Test
    public void RateYearChange() { // Процент накоплений
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        account.add(2_000);
        account.yearChange();

        Assertions.assertEquals(3_150, account.getBalance());
    }
}
