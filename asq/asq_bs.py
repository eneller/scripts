import sys
import time
from getpass import getpass
import credentials

PAGE_PATH= "https://campusonline.uni-ulm.de/CoronaNG/user/mycorona.html"
TIME_CSS_PATH = "html body div#page_margins div#page.hold_floats div#footer div#mblock div#mblock_content.clearfix div#mblock_innen.floatbox"

print("Logging in as "+credentials.username)
"""
while True:
    driver.get()
    
    if (check_exists_name('uid')):
        username_field = driver.find_element(By.NAME, 'uid')
        password_field = driver.find_element(By.NAME, 'password')
        username_field.send_keys(username)
        password_field.send_keys(password)
        password_field.submit()
        time.sleep(i)
        driver.get('https://campusonline.uni-ulm.de/CoronaNG/user/mycorona.html')

    button = driver.find_element(By.XPATH, button_xpath)
    select = Select(driver.find_element(By.XPATH,select_xpath))
    select.select_by_visible_text('Alle markieren')
    button.submit()
    time.sleep(i)
    select = Select(driver.find_element(By.XPATH,select_xpath))
    select.select_by_visible_text('An Markierten teilnehmen')

    button = driver.find_element(By.XPATH, button_xpath)
    button.submit()
    time.sleep(i)
driver.quit()
"""

