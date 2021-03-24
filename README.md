# Android Global News
## Prof. Deryk Kim, Advanced Android Development

## Overview
This application is a basic global news app. It retrieves news and blogs from NewsApi (https://newsapi.org)
and displays them in recyclerviews with the assistance of our helper libraries listed below. 

## Libraries
Libraries used in this project include:  
**Retrofit:** API requests  
**ViewModel:** UI  
**Room:** Database  
**Glide:** Uploading pictures  
**LiveData / LifecycleScope:** Lifecycle  
**Moshi:** Parse JSON  

## Screenshots



## TODO

### Main page
Endpoint top headlines. Card views in recycler view using title, title image,
published time, and possible save button.  
TODO: add styling, fix the selected bottom navigation bar

### Search page
Card views in a recycler view, using search keyword contained in title.
Populate card views based on keyword existing in title. search everything endpoint using q parameter  
TODO: add search bar, styling, fix the selected bottom navigation bar

### Sections
list view with different topics. Click topic and it searches articles related 
to that topic. ex. clicking sports will search for sports articles.  
TODO: figure out why section articles are loading in external browser instead of in-app

### Possible saved articles page
Bookmark articles from anywhere to store in database for offline
viewing.  
TODO: viewpager2 for favourites list, fix the selected bottom navigation bar

## Credits: Dave, Zhihong, Rasheen
