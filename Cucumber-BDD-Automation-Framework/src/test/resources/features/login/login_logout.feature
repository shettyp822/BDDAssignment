Feature: As a Amazon user I should be able to login and logout with valid credentials
	@First
  Scenario: Login into the application with valid credentials
  	Given user is on Login page URL "amazonURL"
    When user click on sign in button
    Then user should see Sign In Page
    When enter username as "amazonUserName"
    And click on Continue button
    And enter password as "amazonPassword"
    And click on login button
    Then user is logged in
    And clear cart items if any
    And choose Electronincs after that Headphones
    Then add first availabe headphone to cart
    Then search "Macbook pro" and add second available item to cart
    And select cart from home and remove the earlier added headphones
    Then reduce the quantity of the macbook pro product to one and proceed to checkout
    And click on Sign out
	  Then user is log out from the application
   
   
  Scenario Outline: Searching different products after login
    Given user is on Login page URL "amazonURL"
    When user click on sign in button
    Then user should see Sign In Page
    When enter username as "<username>"
    And click on Continue button
    And enter password as "<password>"
    And click on login button
    Then user is logged in
		And search different "<products>" from the search bar
		
 	Examples:
	 		| username   | password  | products |
      | 9892333262 | Moni@2419 | laptops  |
      | 9892333262 | Moni@2419 | pendrive |
      | 9892333262 | Moni@2419 | led tv   |
	
	
	Scenario Outline: Searching different products after login using data from excel
    Given user is on the Login page URL "<ExcelData>"
    When user click on sign in button
    Then user should see Sign In Page
    When enter username through excel as "username"
    And click on Continue button
    And enter password through excel as "Password"
    And click on login button
    Then user is logged in
    And search different "products" from the search bar

    Examples:
      | ExcelData |
      | ROW1 |
      | ROW2 |
      | ROW3 |
      
	
	Scenario: Login into the application and search for buses
	Given user is on Login page URL "redbusURL"
	When user enter from destination as "fromDestination"
	And enter to destination as "toDestination"
	And enter "onward" travel Month as "Jun 2020" and date as "18"
	And enter "Return" travel Month for return as "July 2020" and date as "28"
	Then click on Search Buses button


	
	
  
  
   
