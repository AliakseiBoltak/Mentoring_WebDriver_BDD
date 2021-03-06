Feature: GmailTest

  Background:
    Given  I Logged into the gmail account

  Scenario: Create draft email
    When I Compose new email
    And I Close email
    And I open Drafts
    Then I see that this email is saved in drafts

  Scenario: Send email from drafts
    When I Compose new email
    And I Close email
    And I send email from Drafts
    And I open Sent folder
    Then I see that this email appeared in Sent folder

  Scenario: Compose new email and logout from user`s account without saving changes
    When I Compose new email
    And I log out without saving changes
    Then I am on the enter password page
