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
        int actual = account.getBalance();

        Assertions.assertEquals(2_000, actual);
    }

    @Test
    public void overMaxBalanceReplenishment() { // Пополнение средств сверх максимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(10_000);
        int actual = account.getBalance();

        Assertions.assertEquals(2_000, actual);
    }

    @Test
    public void testNegativeRate() { // сообщение о негативной ставке

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    0,
                    10_000,
                    -5);
        });
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
        int actual = account.getBalance();

        Assertions.assertEquals(1_500, actual);
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
        int actual = account.getBalance();

        Assertions.assertEquals(2_000, actual);
    }
    @Test
    public void testPayAmountNegativeMinBalance() { // Отработка метода ниже минимального баланса
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(500));
    }
    @Test
    public void testPayNegativeMinBalance() { // Отработка метода ниже минимального баланса
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );
        account.pay(500);

        Assertions.assertEquals(1_000, account.getBalance());
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

        Assertions.assertEquals(3_000/100*5, account.yearChange());
    }
}
