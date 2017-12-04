# Next Engine Java App Sample

This sample app is meant to provide a working example with Next Engine System.

This sample app is also the practical foundation of our used when creating Java applications using [Next Engine API](http://api.next-e.jp/). It will help you use Next Engine API easily and effectively.

For those who produce general apps, this foundation was released as "Next Engine Application Base".

Next Engine Java App Sample consists of the following:

- Authentication Next Engine System - App calls "/users/sign_in/" API for users to input NE account information, after successfully logging in, App calls "/api_neauth" API to get "access_token" & "refresh_token" than save those data to DB. With these ""access_token" & "refresh_token", users will be able to call any API of NE.
- Product master upload - The operation is the same as the product master reservation collective registration, and it is registered when it becomes possible to register to the main function asynchronously. 
- Search order slip - The search operation order slip in Next Engine with multiple search conditions

This is not a seed project to be deployed to your production environment. In our sample apps, we strive to strike create a application foundation which helps you build app working with Next Engine System easily, effectively, clarity, maintainability, and performance where we can. However, clarity is ultimately the most important quality in a sample app.


# Installation


### System Requirement :
- Vagrant 1.8.1

### Run :

#### 1. Setup clientId and clientSecret

- Open file src/main/resources/ne-api.properties to change value

```
clientId = XXXXX
clientSecret = XXXXX
```

#### 2. Run app

- Step 1 : Run app
```
vagrant up
```
- Step 2 : Open web https://localhost:8443/


# Help and Docs

- [App Document (Japanese)](https://github.com/mulodo-vietnam/next-engine-api-for-java/blob/master/docs_JP/index.md)
- [NE API Document](http://api.next-e.jp/)


# Contributing
1. Fork it ( https://github.com/[my-github-username]/ne_api/fork )
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create a new Pull Request

# Update

### ver0.0.1



