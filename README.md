# MVVM, Paging, DataBinding, Hilt
The app aims to demonstrate the power of a new paging library combined with data-binding and superior architecture in android app development.

## File Structure
![File Structure in Android Studio](https://i.postimg.cc/MptpCsP1/Screenshot-from-2022-02-20-18-06-12.png) 

## Folders-
**Comman**  contains standred error adapters and UI.
**Data** has models, repo and pagingnation logics.
**DI** Hilt dependency injection
**Network** has retrofit request apis.
**UI** has activity, adapters, and UI state for handling data and connection status.
**ViewModels** logics to handle data get from repository.


## How Does it Work?

To give overview here I have a flowchart. ![Flowchart](https://i.postimg.cc/sgX09cLT/Mvvm-1.png)
Here paging source class process pulling data with specific config for paging like page number.

 - PagingSource class for the process of pulling the data,responsible for the source of the paginated data.  
 - LoadParam holds the page number to be loaded,  
LOADSIZE*3 items during the first load .
 - @LoadResult as return type. 
 - @getRefreshKey abstract method for subsequent refresh calls to @PagingSource.load().
Take a look at code itself

<script src="https://gist.github.com/pranimation/0c29c7ed5ef3e3067e6741487b0a705a.js"></script>
