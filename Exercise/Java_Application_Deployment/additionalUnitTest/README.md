# JUnit annotations

The `cart` folder contains a maven project that expands the previous exercise. This project contains a class extending 
the previous calculator (using decorator pattern) to include credits and specials discounts for different types of 
users.

There is some code on the `TotalsWithDiscountCalculatorTest` class that needs to be completed.

1. The method `getTotalsWithDiscounts_regularAndPlatinumUser_returnsDifferentSubtotal` receives
an `User` as parameter, add the required annotations to get different types of users, and a different
expected result. Tip: The method `differentUserTypesAndExpectedTotals` can provide those.

2. `getTotalsWithDiscounts_userWithCredit_chargedMultipleTimes` test that a user can be charged multiple times.
    1. Add the required annotation to initialize the `globalUser` in the `setupGlobalUser` method.
    2. The same user can be used by different calculators, add the required annotation to initialize them in 
    `setupCalculator` method.
    3. Make the `getTotalsWithDiscounts_userWithCredit_chargedMultipleTimes` method repeat three times. 

 