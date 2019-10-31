
# micro-frontend-single-spa

A proof of concept using `single-spa` to implemented the micro-frontend architecture where all the micro-apps are running on seperates servers and has seperate code base.

## legend

- ms - back-end `spring-boot` micro-service to expose some REST API
- ma - front-end `react` micro-app to realize micro-frontend architecture

## structure

This repositoy is a strucvtured as `git submodules`, following are the sub modules which are included

### ms-notifications

This is a `spring-boot` based backend application which is used to expose the following two REST APIs

#### GET /notifications

This API will be used to get all the notifications.

#### GET /template?scope=\<admin or user\>

This API will use scope to provide customized template which will show menu items in navbar on the UI.

### ma-notifications

A micro-app to hook itself with `ms-notfication` REST API `/notifications` to fetch all the notifications and displayes them on a popup screen.
This app will be triggered from master container, `ma-container`, and the menu item linked to this app is in `ma-navbar` micro-app.

### ma-tasks

A micro-app to display all the tasks, does not use any backend API. The tasks are hardcoded.
This app will be triggered from master container, `ma-container`, and the menu item linked to this app is in `ma-navbar` micro-app.

### ma-navbar

A micro-app to display the navbar. The menu items on the navbar will be customized based on the `single-spa` `customProps` passed to it.
The `customProps` which we pass from the `ma-container` is called as `scope`. This `scope` property will be used in the react component to make a backend API call, `/template?scope=?` to `ms-notification` (ideally it should be a seperate microservice but for the sake of simplicity). When a template is recieved from the backend API, the navbar will change what menu items to display.

### ma-container

This is the master container micro-app which weaves all the micro-app togather into a `single-spa`. In this page, `index.tsx` we are passing a `customProps` known as `scope` to all the child micro-app.

# technology stack

- `react`
- `single-spa`
- `spring-boot`
