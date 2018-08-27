# Kotlin-Jetpack
Kotlin-Jetpack

This repository gives a basic example to build MVVM architecture using Kotlin Language.
Android architecture components are part of Android Jetpack. They are a collection of libraries that help you design robust, testable, and maintainable apps. Starts with classes for managing your UI component lifecycle and handling data persistence.
![alt text](https://www.xda-developers.com/files/2018/05/Android-Jetpack-1024x517.png)

1) Manage your app's lifecycle with ease. New lifecycle-aware components help you manage your activity and fragment lifecycles. Survive configuration changes, avoid memory leaks and easily load data into your UI.

2) Use LiveData to build data objects that notify views when the underlying database changes.

3) ViewModel Stores UI-related data that isn't destroyed on app rotations.

4) Room is an a SQLite object mapping library. Use it to Avoid boilerplate code and easily convert SQLite table data to Java objects. Room provides compile time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.

5)The WorkManager API makes it easy to specify deferrable, asynchronous tasks and when they should run. These APIs let you create a task and hand it off to WorkManager to run immediately or at an appropriate time. WorkManager might use JobScheduler, Firebase JobDispatcher, or AlarmManager. You don't need to write device logic to figure out what capabilities the device has and choose an appropriate API; instead, you can just hand your task off to WorkManager and let it choose the best option.
WorkManager might use JobScheduler, Firebase JobDispatcher, or AlarmManager. You don't need to write device logic to figure out what capabilities the device has and choose an appropriate API; instead, you can just hand your task off to WorkManager and let it choose the best option.

6)The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.

Below are the Jetpack Android architecture components used in these sample demo with Kotlin Language.

def room = [:]
room.runtime = "android.arch.persistence.room:runtime:$versions.room"
room.compiler = "android.arch.persistence.room:compiler:$versions.room"
deps.room = room

def lifecycle = [:]
lifecycle.extensions = "android.arch.lifecycle:extensions:$versions.lifecycle"
lifecycle.compiler = "android.arch.lifecycle:compiler:$versions.lifecycle"
deps.lifecycle = lifecycle

def work = [:]
work.runtime = "android.arch.work:work-runtime:$versions.work"
work.firebase = "android.arch.work:work-firebase:$versions.work"
deps.work = work

def kotlin = [:]
kotlin.stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jre7:$versions.unknown"
kotlin.plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$versions.kotlin"
deps.kotlin = kotlin


