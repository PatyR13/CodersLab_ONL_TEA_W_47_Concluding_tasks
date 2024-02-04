Feature: Shopping happy path.

  Scenario Outline: E2E test - buying a sweater with pick up in store and pay by check.
    Given the logged user has open browser at clothes category
    When the user buys <quantity> pieces of sweater with size <size>
    Then the order is placed with correct quantity <quantity> and size <size>
    And the screenshot is taken
    And the order status is Awaiting check payment and the amount is the same as in the confirmation

    Examples:
    | size  | quantity |
    | M     |   5      |

