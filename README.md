# Android Architecture: MVVM
# Introduction
* MVVM is one of the architectural patterns which enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic.Its target (with other MVC patterns goal) is to achieve the following principle “Keeping UI code simple and free of app logic in order to make it easier to manage”.
<p align="center">
<img src=https://github.com/ThuanPx/Android_Architecture/blob/master/images/mvvm.png />
</p>

* Model

Model represents the data and business logic of the app. One of the recommended implementation strategies of this layer, is to expose its data through observables to be decoupled completely from ViewModel or any other observer/consumer (This will be illustrated in our MVVM sample app below).
* View

The view role in this pattern is to observe (or subscribe to) a ViewModel observable to get data in order to update UI elements accordingly.
* View Model

ViewModel interacts with model and also prepares observable(s) that can be observed by a View. ViewModel can optionally provide hooks for the view to pass events to the model.
One of the important implementation strategies of this layer is to decouple it from the View, i.e, ViewModel should not be aware about the view who is interacting with.

# Libraries Used

* [Dagger][1] Dagger is a fully static, compile-time dependency injection framework for both Java and Android. It is an adaptation of an earlier version created by Square and now maintained by Google.

* [RxJava 2][4] Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.

* [ViewModel][2] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.

* [LiveData][3] - Build data objects that notify views when the underlying database changes.


[1]: https://dagger.dev/
[2]: https://developer.android.com/topic/libraries/architecture/viewmodel
[3]: https://developer.android.com/topic/libraries/architecture/livedata
[4]: https://github.com/ReactiveX/RxJava
[5]: https://developer.android.com/topic/libraries/architecture/coroutines#viewmodelscope