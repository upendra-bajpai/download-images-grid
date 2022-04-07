﻿# API with chain of calls
The app aims to demonstrate the power of a new coroutine combined with data-binding and superior architecture in android app development.

## File Structure
![File Structure in Android Studio](https://i.postimg.cc/5NfpvPXv/Screenshot-from-2022-04-02-18-51-41.png)

## Folders-
**Common**  contains standard error adapters and UI.
**Data** has models, repo and pagination logics.
**DI** Hilt dependency injection
**Network** has retrofit request apis.
**UI** has activity, adapters, and UI state for handling data and connection status.
**ViewModels** logics to handle data get from repository.


## How Does it Work?

For parallel downloading of images we can use async/await coroutine which will block threads till await completes
```val usersFromApiDeferred =async { userRepository.getImages(1)}
                   val moreUsersFromApiDeferred  =async { userRepository.getImages(2)}
                   val moreUsersFromApisDeferred =async { userRepository.getImages(3)}


                   val usersFromApi = usersFromApiDeferred.await()
                   val moreUsersFromApi = moreUsersFromApiDeferred.await()
                   val moreUsersFromApis = moreUsersFromApisDeferred.await()

  ```

  For sequential downloading of images we can use async coroutine which  will chain one by one api calls
  ```     val usersFromApi = userRepository.getImages(1)
            val moreUsersFromApi =userRepository.getImages(2)
            val moreUsersFromApis=userRepository.getImages(3)
  ```


##For optimization of code

approach
We have images to load , which are the main cause of memory consumption, we need to optimize bitmap of images.
1. clear recycler view holder on <i>onViewRecycled()</i>. This helps in deep activity stack where activities are not destroyed after navigation glide will hold objects.
2. use **RGB_565**  instead of **ARGB_8888** in glide to load images(has visual impact).
3. fix height of viewholder if view holder has fixed size. we used ``` binding.rvImages.setHasFixedSize(true)```, since we had view holder with fix height.