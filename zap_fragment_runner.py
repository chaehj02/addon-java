import time
from selenium import webdriver
from selenium.webdriver.chrome.options import Options

chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--proxy-server=http://127.0.0.1:8080")

driver = webdriver.Chrome(options=chrome_options)
driver.get("http://localhost:8080/WebGoat/start.mvc")

# 로그인 (WebGoat 기준)
driver.find_element("id", "username").send_keys("user123")
driver.find_element("id", "password").send_keys("user123")
driver.find_element("id", "login-button").click()
time.sleep(3)

# fragment_list 순회
with open("fragment_list.txt") as f:
    fragments = [line.strip() for line in f.readlines() if line.strip()]

for frag in fragments:
    driver.execute_script(f"window.location.hash = '{frag}';")
    time.sleep(2)

driver.quit()
