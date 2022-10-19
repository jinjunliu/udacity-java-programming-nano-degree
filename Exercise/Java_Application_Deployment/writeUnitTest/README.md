# JUnit 

The `cart` folder contains a Maven project, `TotalsCalculator` calculates the fields of a `CartTotals` object from a list 
of `CartItem` objects.

1. Create a unit test to check that `TotalsCalculator` is adding all the items. The test should have three main parts:

    1. Arrange: Creating an `TotalsCalculator` (the subject of under test), and a list of `CartItems` to be used on it.
    2. Act: A call to `getTotals` method pasing the list of `CartItems` and storing the result on a variable.
    3. Assert: Assertions over the result that was previously stored on a variable. Since the `CartTotals` fields are 
    `doubles` you will need to specify a `delta` like `assertEquals(11.0, totals.getSubtotal(), 0.001)`.