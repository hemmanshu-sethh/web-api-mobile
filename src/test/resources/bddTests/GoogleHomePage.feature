Feature: Google

  # The first example has two steps
  Scenario: Check Google Search Books Tab

Given Google Home Page is Launched
  When I perform Search
  And I click on Books
  Then I am navigated to Books Result Page