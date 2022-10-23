The maven project `charge-service` contains the `ChargeService` class. This class calls a different service, which is
not implemented yet. We need to test that `ChargeUserApiHttpClient` receives the correct amount.

1. Add a unit test for `ChargeService`.
2. Mock `ChargeUserApiHttpClient` and check it is called with an expected amount calculated from the parameters received 
by `ChargeService`.