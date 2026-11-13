
import requests
import time

TOKEN = "713293558:uEgC7wpPakk9VI3kX63ShVvMyGcZxxtTosA"

API_URL = f"https://tapi.bale.ai/bot{TOKEN}"

offset = 0

print("Bot is running...")

while True:
    try:
        response = requests.get(
            f"{API_URL}/getUpdates",
            params={
                "offset": offset,
                "timeout": 30
            },
            timeout=40
        )

        data = response.json()

        if not data.get("ok"):
            print("API Error:", data)
            time.sleep(3)
            continue

        for update in data.get("result", []):

            offset = update["update_id"] + 1

            message = update.get("message")

            if not message:
                continue

            chat_id = message["chat"]["id"]
            text = message.get("text", "")

            if text == "/start":
                reply = "سلام! به ربات من خوش اومدی 😊"

            else:
                reply = "چیکار داری؟ 😁"

            requests.post(
                f"{API_URL}/sendMessage",
                json={
                    "chat_id": chat_id,
                    "text": reply
                },
                timeout=30
            )

    except requests.RequestException as error:
        print("Connection error:", error)
        time.sleep(3)

    except Exception as error:
        print("Error:", error)
        time.sleep(3)