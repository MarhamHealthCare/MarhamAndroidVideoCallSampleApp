# MarhamAndroidVideoCallSampleApp

1.	Download and Install SDK:

Add the JitPack repository to your build file
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add the dependency
	dependencies {
	        implementation 'com.github.MarhamHealthCare:MarhamAndroidVideoCall:0.0.24'
	}

2.	Initialize SDK
The below code will be used to initialize SDK:
MarhamVideoCallHelper.getInstance().setClient("telenor").setAPIKEY("$2y$10$UTa82jp.SycYYIc0wUn9r.2aqWEluFknWhki2Aooh3taGNsry3oA6").setFirebaseToken("fRHe-d--TkqGplJG_tgPJP:APA91bE9v2J6hop0mcaVx7wLYzT7n1iQRtTlNBaw-wxoEk9Wo4VvAlsnx0FJYYPEM8-q8CibWuo7Lpz4CczqalmSOKUsxS-mqQ4yDc4u-ZT5zg9fCuev56SYfRcq53Tt1tH94VHvHrhe").setUserPhoneNumber("+923334794867").setUserName("Wazzah").setMarhamPaymentListener(marhamPaymentListener);


Mandatory Parameters
Client: telenor
API Key: $2y$10$UTa82jp.SycYYIc0wUn9r.2aqWEluFknWhki2Aooh3taGNsry3oA6
Firebase: FCM token of the device
Phone Number: Phone Number of Health Line user
User Name: Name of Health Line user
Payment Listener: Reference of class responsible for handling payment.

3. Launch SDK MarhamVideoCallHelper.getInstance().openMarhamSdk(context);


4. Handle Payment:
Healthline's class responsible for handling payment will implement the interface below, the instance of the class will be passed as an argument while initializing SDK.

public interface MarhamPaymentListener { 
void onPaymentRequested(Appointment appointment); 
}

Once the payment is done the Healthline app will invoke Marham SDK's method below which will accept the Context and Appointment as an argument 

MarhamVideoCallHelper.getInstance().openPaymentScreen(context, appointment);


![image](https://user-images.githubusercontent.com/22675002/219412653-9a4d7afe-51f8-4450-b9a8-7ec45b83e362.png)
