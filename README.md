# Bing Voice Recognition API test

### Get ready to run the application

Quick-and-dirty usage example of of Bing Voice Recognition RESTful API.
This application has been developed using Spring Boot.

Steps on how to use this app:

1. Create a Microsoft Azure Account: [https://azure.microsoft.com](https://azure.microsoft.com)
2. Subscribe to the free tier (5000 free requests/month) of Voice Recognition API ([https://datamarket.azure.com/dataset/bing/speechrecognition](https://datamarket.azure.com/dataset/bing/speechrecognition))
3. Register a new application in your account: [https://datamarket.azure.com/account/keys](https://datamarket.azure.com/account/keys), note down app id and app secret
4. Generate an unique UUID (command `uuidgen` in UNIX) for this app.
5. Edit `src/main/resources/application.properties` and configure `bing.speech.to.text.example.app.id` and `bing.speech.to.text.example.app.secret` using the values generated at step #3 above.
6. Edit `src/main/resources/application.properties` and configure `bing.speech.to.text.example.device.id` using the values generated at step #4 above.

### Run the application

You first need to get an authentication token from Azure. Once you've correctly configured `src/main/resources/application.properties` as detailed in the previous paragraph, run the following command:

	mvn spring-boot:run -Drun.arguments=auth

This calls the appropriate Bing authentication API and shows the token in the terminal. To make things easier for you, the above commnd shows an `export BING_AUTH=...` statement. Execute this statement in your terminal before proceeding with the audio conversion.

Be aware that each authentication token lasts only 10 minutes, if you start getting error 403 (Forbidden) just run the above auth step above.

To convert an audio (WAV) file:

	mvn spring-boot:run -Drun.arguments=/path/to/audio/file.wav


### Additional documentation

Check out Official Microsoft documentation at: [https://onedrive.live.com/view.aspx?resid=9A8C02C3B59E575!106&ithint=file%2cdocx&app=Word&authkey=!AIEJaNeh8CcDTjU](https://onedrive.live.com/view.aspx?resid=9A8C02C3B59E575!106&ithint=file%2cdocx&app=Word&authkey=!AIEJaNeh8CcDTjU)