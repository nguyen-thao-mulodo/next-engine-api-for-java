# Next Engine Java App Sample

This sample app is meant to provide a working example with Next Engine System.

This sample app is also the practical foundation of our used when creating Java applications using [Next Engine API](http://api.next-e.jp/). It will help you use Next Engine API easily and effectively.

For those who produce general apps, this foundation was released as "Next Engine Application Base".

Next Engine Java App Sample consists of the following:

- Authentication Next Engine System - First, the app calls the "/users/sign_in/" API endpoint for users to input their NE account information. After successfully logging in, the app calls the "/api_neauth" API endpoint to retrieve the "access_token" and "refresh_token," which are subsequently saved to the database. By utilizing the "access_token" and "refresh_token," users will be able to call any NE API without having to login again.
- Product master upload - This operation is the same as the product master bulk registration, and it is registered when it becomes possible to register to the main function asynchronously.
- Search order slip - The search operation order slip in Next Engine with multiple search conditions

This is not a seed project to be deployed to your production environment. In our sample apps, we strive to create an foundation that helps users easily build effective, clearly-defined and maintainable apps that use the Next Engine System. However, clarity is ultimately the most important quality in a sample app.


# Installation


### System Requirements :
- Vagrant 1.8.1

### Run :

#### 1. Setup the clientId and clientSecret

- Open the file src/main/resources/ne-api.properties and change the following values

```
clientId = XXXXX
clientSecret = XXXXX
```

#### 2. Run the app

- Step 1 : Run the app
```
vagrant up
```
- Step 2 : Open https://localhost:8443/ in a web browser


# Help and Docs

- [App Document (Japanese)](https://github.com/mulodo-vietnam/next-engine-api-for-java/blob/master/docs_JP/index.md)
- [NE API Document](http://api.next-e.jp/)


# Contributing
1. Fork it ( https://github.com/[my-github-username]/next-engine-api-for-java)
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create a new Pull Request

# Update

### ver0.0.1

# License
Copyright 2017 Mulodo VietNam Co., Ltd.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

