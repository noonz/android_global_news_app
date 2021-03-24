# Android Global News
## Prof. Deryk Kim, Advanced Android Development

## Overview
This application is a global news app using MVVM architecture. It retrieves news and blogs from NewsApi (https://newsapi.org)
and displays them in recycler views with the assistance of our helper libraries listed below.

## Libraries
Libraries used in this project include:  
**Retrofit:** API requests  
**ViewModel:** UI  
**Room:** Database  
**Glide:** Displaying images  
**LiveData / LifecycleScope:** Lifecycle  
**Moshi:** Parse JSON  

## Screenshots
![](images\global_news_beta.PNG)

## TODO

### Main page
Endpoint #1 top headlines. Card views in recycler view using title, title image,
published time, and possible save button.  
TODO: add styling, fix the selected bottom navigation bar

### Search page
Endpoint #2 everything. Card views in a recycler view, using search keyword contained in title. Populate card views based on keyword existing in title.  
TODO: add search bar, styling, fix the selected bottom navigation bar, fix search results not updating

### Categories
Endpoint #3 sources. list view with different topics. Click topic and it searches articles related to that topic. ex. clicking sports will search for sports articles.  
TODO: styling

### Possible saved articles page
Bookmark articles from anywhere to store in database for offline
viewing.  
TODO: viewpager2 for favourites list, fix the selected bottom navigation bar

## Credits: Dave, Zhihong, Rasheen
