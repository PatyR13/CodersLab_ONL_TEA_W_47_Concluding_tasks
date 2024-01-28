Feature: Adding or deleting an address from user's account.

  Scenario Outline: Adding a new address to logged user's account.
    Given the user is logged into his account
    And an open browser at create a new address
    When the user adds a new address with <alias> <address> <city> <zip code> <phone>
    Then new address added with correct <alias> <address> <city> <zip code> <country> <phone>
    And quit browser

    Examples:
    | alias   | address     | city   | zip code | country          | phone      |
    | actual  | "Baking 15" | London | W1U6SG   | "United Kingdom" | 2071234567 |

  Scenario: Deleting of additional address.
    Given an open browser at logged user's address page
    When the user deletes one of additional addresses
    Then the address was removed successfully
    And quit browser