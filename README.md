# Selenium Automation Script for eBay Purchase

This project is a Selenium automation script developed in Java to automate the purchase of smartphones on eBay. The script handles the entire buying process, from logging in and searching for a specific smartphone to adding it to the cart and completing the checkout.

## Features

- **Automated Login:** Automatically logs in to eBay using user-provided credentials.
- **Item Search:** Searches for a specific smartphone based on user-defined criteria.
- **Add to Cart:** Adds the selected smartphone to the shopping cart.
- **Automated Checkout:** Completes the checkout process, ensuring a seamless purchase.

## Requirements

- Java Development Kit (JDK)
- Maven
- Selenium WebDriver
- Web browser (Chrome, Firefox, etc.) with corresponding WebDriver

## Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/EmilWijayasekara/Selenium-automation-script-for-ebay-purchase.git
   cd Selenium-automation-script-for-ebay-purchase` 

2.  **Install dependencies:** Ensure you have Maven installed, then run:
    
    `mvn install` 
    
3.  **Configure the script:** Update the `config.properties` file with your eBay credentials and desired search criteria.
    

## Usage

1.  **Run the script:**
    
    `mvn test` 
    
2.  **Observe the automation:** The script will open a web browser and perform the automated tasks as specified.
    

## Notes

-   Ensure that the WebDriver path is correctly set in the `config.properties` file.
-   The script is intended for educational purposes. Be mindful of eBay's terms of service when using automated scripts.
