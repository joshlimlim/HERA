# KOH PEI LING, JOSHUA LIM, LIAO WEI SHUN

# DESCRIPTION OF HERA APPLICATION 
How old are you? Do you have a girlfriend or boyfriend? Are you tired of texting your matched partner?
People always have a stigma of online dating applications such as others only finding one for a one-night stand. Many find that there is no point to talk online as they would rather meet each other in real life. Lastly, to those who cannot find a partner for a long time, this is a good opportunity to meet new people. 
HERA refers to the Greek Goddess of Romance which brings us to our application’s name. HERA is an application that provides the people the best of both worlds. Blissfulness and Happiness. 

# APPLICATION FEATURES
There is a total of six main features in this application. In this report, the features of the application would be introduced and how the implementation is going to be done.
# 1. Registering for an account
The first main feature is registering for an account. A registration page is built for user to easily create an account with HERA. By registering, the user profile would be saved into the database and existing users can then log in to their saved account created previously. 
# 2. Logging in to your account
Existing users that has been registered can log in to the application by going into this page. This is a function where users do not have to constantly verify themselves. 
Both the registration and logging in page will be implemented with the regular expression feature. If the any of the input variables fails the required regular expression criteria, it will throw an error message box that will inform them user that the activity has failed as it does not match criteria.
# 3. Start finding partners by swiping or clicking
Next feature that we have implemented is the swipe cards. Similar to the tinder, the accepting and rejecting of people is through swiping left or right. Swiping it to the right means the other party is accepted by you, while swiping it to the left means you have rejected the other party. If the other party right on you, this means that both of you are a match. 
A virtual library is being implemented and used for the swipe card activity. The specific library used was “lorentzos”.
Alternatively, we have implemented the button features for accepting and rejecting as well.
# 4. Chatting with your matched partners
The fourth main feature is chatting with the people you are matched with. A messaging pane is created for members to chat to their partners. They can get to know each other better and see whether they are suited for each other. To show the messages, a simple list view and a custom layout is used. 
# 5. Finding location to meet
API – We decide to adopt the Google Places API to retrieve places recommendation by google. The API starts with a formulated Search Query based on user’s interest profile followed by a Place Search and lastly a Detailed Place API.
Formulated Search Query
This process is required to get relevant places to recommend to the user. This query is used by the Place Search API to return places according to keywords provided. Thus, the query is a combination of the user’s interest profile which contains keyword of what they like. 
An example of a query is “live bands in singapore” – with “live band” being the interest keyword and “in singapore” being the default footer to ensure results are geographical relevant to Singapore.
Place Search API
This API is launched when the formulated search query is formed. This API returns up to 60 recommended places relevant to the keyword. However, only the Place ID is returned without in-depth details such as the address, atmosphere and other detailed information of the place. We aim to use the 60 Place ID collected from this API to do a detailed search in the Detailed Place API.
Detailed Place API
This is the final API that is called. This API returns detailed information of a given place ID. As mentioned, we will utilize the 60 place ID’s collected from the Place Search API to generate this in-depth information. The returned data is then appended into our firebase database under the “places” children.
# 6. UPDATE PROFILE
Lastly, if the user wants to update their profile, they can do so in the settings tab which would bring them to the settings page. 

# APPLICATION DESIGNS
Now, I will be talking about the user interface of our mobile application and how we designed it to ensure that our user has the best user experience.
# Before Logging in
There are the first three pages that the user will most likely interact. The home page, registration page and lastly login page. The user interface is designed with simple interactive buttons to guide the users through the registration and logging in process. Hints are added into the edit text field to also ease the process for the users. 
# After Logging in – MATCH
After logging in, the swipe card activity will appear. However, a user is advised to upload an image of themselves before they try to find a match. If both parties swipe right on each other, the application will return a match as shown above. They can then choose whether they want to send a message or continue browsing.
# Navigation Bar
Navigation Bar is designed to allow users to easily navigate from one page to another page. 
# Messages
Messages shows all the matched partners you have matched previously. This interface shows the people that you have interacted with and the past conversations you had with them. 
# Places to go and Settings
Another feature that the interface shows is the different locations that they can go to for a meet up. And lastly, the settings tab has a simple interface for users to upload their image, name and phone number.

#USER SETTINGS
STEP 1: OPEN APPLICATION
STEP 2: REGISTER FOR AN ACCOUNT (IF NEW USER)
            : LOGIN TO CREATED ACCOUNT (IF EXISTING)
STEP 3: UPLOAD IMAGE IN SETTINGS TAB
STEP 4: START SWIPING! SWIPE LEFT TO REJECT AND SWIPE RIGHT TO ACCEPT
STEP 5: START TALKING TO YOUR MATCHED DATE
STEP 6: CHOOSE A LOCATION TO GO TO FOR A DATE 
STEP 7: HAVE FUN FALLING IN LOVE!

# CONCLUSION
To conclude, I hope that with our application, it can resolve the problems that we have identified and hopefully, implement it to be a better and fun application for our users.
