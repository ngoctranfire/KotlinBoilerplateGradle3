Android Kotlin Boilerplate
==========================
This project is a kotlin boilerplate project. The project assumes that your project will be using Firebase.
For you compile this project, you will need to setup Firebase. You can do so [here](https://firebase.google.com/docs/android/setup)
1. You will need to provide a google-services.json file in the [folder](app)
 + If you would like to disable any google services, you can easily do so:
    Just remove the dependencies and remove their dependencies from the Dagger Graph. Right
    now, pretty much the only one we are using is the Firebase Analytics. It should be simple.
    If you have questions, please reach out to me.
2. The project also requires kotlin support so make sure that you have kotlin downloaded

***Notes***: ***If you notice, even our debug builds are using pro-guard services 
and we are also minimizing and shrinking resources. This is to guarantee that it works
in both debug and release mode in the same way. If you would like, you can toggle these to
false.***