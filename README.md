# InstaAndroid

You will need to register your app [here](https://www.instagram.com/developer/) and get **CLIENT_ID** and **CLIENT_SECRET**. Add them in below format in `gradle.properties file`. Create your own file if it does not exist. Also change the **REDIRECT_URI** in `Constants.Java` file.

```
CLIENT_ID = "your id"
CLIENT_SECRET = "your secret"
```
Use Chrome browser when app prompts to open Instagram.


To implement more features
- [ ] Using Instagram Graph API
- [ ] Using WebView
- [ ] To remove cookies, so that Instagram login is asked again
