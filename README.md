#Reach - Music Curating App

Reach is a music curating app which takes data from SoundCloud's public APIs and presents them in a clean and modular interface.


## Architecture & Design Pattern

Reach follows a Model-View-Presenter design pattern. A clear segregation among the three elements helps in achieving a loosely coupled but highly cohesive application.
In order to create a highly testable piece of code, Dependency Injection has been used. Dagger2 and ButterKnife help in facilitating this.

Reach has a single screen for all the information required by the user.


### Music Ranking
Ranking of songs has been achieved on the basis on below mentioned parameters:

* Number of times a song was made favorite,
* Number of times a song was commented on,
* Number of times a song was played,
* Release Month

### Data Updates
App's data is refreshed every 60 minutes from the time of opening of app. JobScheduler has been utilized to achieve this. API calls to fetch latest data is requested only on app open. Background operations are kept at minimum while the app in not in use.
Data is being persisted in SQLite, which serves as the data source for the app. Database tables are cleared by JobScheduler at periodic intervals (60 mins).

### Background Operations
ThreadPoolExecutor along with other Java/Android Threading mechanisms have been utilized in order to keep the UI thread free from heavy operations.


## 3rd Party Libraries

* Android Support Libraries (v 28.0.0)
* Dagger (v 2.12)
* ButterKnife (v 8.7.0)
* RetroFit (v 2.3.0)
* Glide (v 4.8.0)
* Calligraphy (v 3.1.1)
* Gson (v 2.7)


## Credits & Acknowledgments

* Dribbble
* GitHub
* StackOverflow
* Medium


