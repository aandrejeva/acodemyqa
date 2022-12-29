Feature: Coupon code functionality

  As a customer
  I want to have the possibility to apply my discount during the purchase
  So that I can be eligible for discount

  Scenario: User enters invalid coupon code and gets error message
    # Given, When, then, And, *
    Given user opens "https://shop.acodemy.lv"
    And user selects "Cap" product from a list
    And user click on add to cart button
