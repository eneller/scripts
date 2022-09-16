import sys
import time
from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.support.ui import Select
from selenium.webdriver.common.by import By
from getpass import getpass

def check_exists_name(name):
    try:
        driver.find_element(By.NAME, name)
    except NoSuchElementException:
        return False
    return True

i=.2 # TODO replace dumb waiting with selenium waiting

username = input("Input Username: ")
password = getpass()
button_xpath = '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[3]/td/input[1]'
select_xpath = '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[3]/td/select'

driver = webdriver.Firefox(executable_path='./geckodriver') # TODO replace with service worker object
#driver.implicitly_wait(2) # seconds


while True:
    driver.get('https://campusonline.uni-ulm.de/CoronaNG/user/mycorona.html')
    
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
