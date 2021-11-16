import sys
import time
from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.support.ui import Select
from selenium.webdriver.common.by import By

def check_exists_name(name):
    try:
        driver.find_element(By.NAME, name)
    except NoSuchElementException:
        return False
    return True

i=.2
driver = webdriver.Firefox(executable_path='./geckodriver')
pattern = '%d.%m.%Y %H:%M:%S'
target = int(time.mktime(time.strptime(sys.argv[3], pattern)))

while True:
    driver.get('https://campusonline.uni-ulm.de/CoronaNG/user/mycorona.html')

    if (check_exists_name('uid')):
        username = driver.find_element(By.NAME, 'uid')
        password = driver.find_element(By.NAME, 'password')
        username.send_keys(sys.argv[1])
        password.send_keys(sys.argv[2])
        password.submit()
        time.sleep(i)
        driver.get('https://campusonline.uni-ulm.de/CoronaNG/user/mycorona.html')
    elif (target-time.clock_gettime(time.CLOCK_REALTIME)>0):
        button = driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select')
        select = Select(driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select'))
        select.select_by_visible_text('Alle markieren')
        button.submit()
        time.sleep(i)
        select = Select(driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select'))
        select.select_by_visible_text('An Markierten teilnehmen')

        button = driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select')
        time.sleep(target-time.clock_gettime(time.CLOCK_REALTIME))
        button.submit()
        time.sleep(i)
    else:
        button = driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select')
        select = Select(driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select'))
        select.select_by_visible_text('Alle markieren')
        button.submit()
        time.sleep(i)
        select = Select(driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select'))
        select.select_by_visible_text('An Markierten teilnehmen')
        button = driver.find_element(By.XPATH, '/html/body/div/div/div[4]/div[2]/div[1]/div/div/div[1]/div/form[1]/div/div/table/tbody/tr[6]/td/select')
        button.submit()
        time.sleep(i)
 
driver.quit()
