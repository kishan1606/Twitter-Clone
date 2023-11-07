Twitter CLone API
===============================

## Overview

For this project, we are tasked with implementing a RESTful API using Spring Boot, JPA, and Postgresql. Specifically, we implementing an API that exposes operations for social media data that resembles the conceptual model of Twitter.

we implement this API from scratch, working from a series of endpoint specifications (found at the end of this document) to develop a mental model of the data. we develop a suitable database schema and write Spring services and controllers to handle requests, perform validation and business logic, and to transform data between the API and database models.

## Entity Relationship Diagram
![Spring Assessment ERD](https://user-images.githubusercontent.com/12191780/187276918-ccb2d373-be3b-42ff-a74d-5560ba806a10.png)


This ERD represents the database for this project. Create three classes, `User`, `Tweet`, and `Hashtag`, annotated with `@Entity`. There are, however, two additional classes that are needed to create for this project: `Credentials` and `Profile`. These two classes will be annotated with `@Embeddable` and will be used inside of the `User` entity class with the `@Embedded` annotation. This allows us to maintain credentials and profiles as separate objects in Java while still being stored in just one table in the database.

**IMPORTANT:** The `User` entity will also need to use an `@Table(name=<newName>)` annotation to give its table a different name as `user` is a reserved keyword in PostgreSQL.


## API Endpoints

### `GET   validate/tag/exists/{label}`
Checks whether or not a given hashtag exists.

#### Response
```javascript
'boolean'
```

### `GET   validate/username/exists/@{username}`
Checks whether or not a given username exists.

#### Response
```javascript
'boolean'
```

### `GET   validate/username/available/@{username}`
Checks whether or not a given username is available.

#### Response
```javascript
'boolean'
```

### `GET     users`
Retrieves all active (non-deleted) users as an array.

#### Response
```javascript
['User']
```

### `POST    users`
Creates a new user. If any required fields are missing or the `username` provided is already taken, an error should be sent in lieu of a response.

If the given credentials match a previously-deleted user, re-activate the deleted user instead of creating a new one.

#### Request
```javascript
{
  credentials: 'Credentials',
  profile: 'Profile'
}
```

#### Response
```javascript
'User'
```

### `GET     users/@{username}`
Retrieves a user with the given username. If no such user exists or is deleted, an error should be sent in lieu of a response.

#### Response
```javascript
'User'
```


### `PATCH   users/@{username}`
Updates the profile of a user with the given username. If no such user exists, the user is deleted, or the provided credentials do not match the user, an error should be sent in lieu of a response. In the case of a successful update, the returned user should contain the updated data.

#### Request
```javascript
{
  credentials: 'Credentials',
  profile: 'Profile'
}
```

#### Response
```javascript
'User'
```

### `DELETE  users/@{username}`
"Deletes" a user with the given username. If no such user exists or the provided credentials do not match the user, an error should be sent in lieu of a response. If a user is successfully "deleted", the response should contain the user data prior to deletion.

**IMPORTANT:** This action should not actually drop any records from the database! Instead, develop a way to keep track of "deleted" users so that if a user is re-activated, all of their tweets and information are restored.

#### Request
```javascript
'Credentials'
```

#### Response
```javascript
'User'
```

### `POST    users/@{username}/follow`
Subscribes the user whose credentials are provided by the request body to the user whose username is given in the url. If there is already a following relationship between the two users, no such followable user exists (deleted or never created), or the credentials provided do not match an active user in the database, an error should be sent as a response. If successful, no data is sent.

#### Request
```javascript
'Credentials'
```

### `POST    users/@{username}/unfollow`
Unsubscribes the user whose credentials are provided by the request body from the user whose username is given in the url. If there is no preexisting following relationship between the two users, no such followable user exists (deleted or never created), or the credentials provided do not match an active user in the database, an error should be sent as a response. If successful, no data is sent.

#### Request
```javascript
'Credentials'
```

### `GET     users/@{username}/feed`
Retrieves all (non-deleted) tweets authored by the user with the given username, as well as all (non-deleted) tweets authored by users the given user is following. This includes simple tweets, reposts, and replies. The tweets should appear in reverse-chronological order. If no active user with that username exists (deleted or never created), an error should be sent in lieu of a response.

#### Response
```javascript
['Tweet']
```

### `GET     users/@{username}/tweets`
Retrieves all (non-deleted) tweets authored by the user with the given username. This includes simple tweets, reposts, and replies. The tweets should appear in reverse-chronological order. If no active user with that username exists (deleted or never created), an error should be sent in lieu of a response.

#### Response
```javascript
['Tweet']
```

### `GET     users/@{username}/mentions`
Retrieves all (non-deleted) tweets in which the user with the given username is mentioned. The tweets should appear in reverse-chronological order. If no active user with that username exists, an error should be sent in lieu of a response.

A user is considered "mentioned" by a tweet if the tweet has `content` and the user's username appears in that content following a `@`.

#### Response
```javascript
['Tweet']
```

### `GET     users/@{username}/followers`
Retrieves the followers of the user with the given username. Only active users should be included in the response. If no active user with the given username exists, an error should be sent in lieu of a response.

#### Response
```javascript
['User']
```

### `GET     users/@{username}/following`
Retrieves the users followed by the user with the given username. Only active users should be included in the response. If no active user with the given username exists, an error should be sent in lieu of a response.

#### Response
```javascript
['User']
```

### `GET     tags`
Retrieves all hashtags tracked by the database.

#### Response
```javascript
['Hashtag']
```

### `GET     tags/{label}`
Retrieves all (non-deleted) tweets tagged with the given hashtag label. The tweets should appear in reverse-chronological order. If no hashtag with the given label exists, an error should be sent in lieu of a response.

A tweet is considered "tagged" by a hashtag if the tweet has `content` and the hashtag's label appears in that content following a `#`

#### Response
```javascript
['Tweet']
```

### `GET     tweets`
Retrieves all (non-deleted) tweets. The tweets should appear in reverse-chronological order.

#### Response
```javascript
['Tweet']
```

### `POST    tweets`
Creates a new simple tweet, with the author set to the user identified by the credentials in the request body. If the given credentials do not match an active user in the database, an error should be sent in lieu of a response.

The response should contain the newly-created tweet.

Because this always creates a simple tweet, it must have a `content` property and may not have `inReplyTo` or `repostOf` properties.

**IMPORTANT:** when a tweet with `content` is created, the server must process the tweet's content for `@{username}` mentions and `#{hashtag}` tags. There is no way to create hashtags or create mentions from the API, so this must be handled automatically!

#### Request
```javascript
{
  content: 'string',
  credentials: 'Credentials'
}
```

#### Response
```javascript
'Tweet'
```

### `GET     tweets/{id}`
Retrieves a tweet with a given id. If no such tweet exists, or the given tweet is deleted, an error should be sent in lieu of a response.

#### Response
```javascript
'Tweet'
```

### `DELETE  tweets/{id}`
"Deletes" the tweet with the given id. If no such tweet exists or the provided credentials do not match author of the tweet, an error should be sent in lieu of a response. If a tweet is successfully "deleted", the response should contain the tweet data prior to deletion.

**IMPORTANT:** This action should not actually drop any records from the database! Instead, develop a way to keep track of "deleted" tweets so that even if a tweet is deleted, data with relationships to it (like replies and reposts) are still intact.

#### Request
```javascript
'Credentials'
```

#### Response
```javascript
'Tweet'
```

### `POST    tweets/{id}/like`
Creates a "like" relationship between the tweet with the given id and the user whose credentials are provided by the request body. If the tweet is deleted or otherwise doesn't exist, or if the given credentials do not match an active user in the database, an error should be sent. Following successful completion of the operation, no response body is sent.

#### Request
```javascript
'Credentials'
```

### `POST    tweets/{id}/reply`
Creates a reply tweet to the tweet with the given id. The author of the newly-created tweet should match the credentials provided by the request body. If the given tweet is deleted or otherwise doesn't exist, or if the given credentials do not match an active user in the database, an error should be sent in lieu of a response.

Because this creates a reply tweet, content is not optional. Additionally, notice that the `inReplyTo` property is not provided by the request. The server must create that relationship.

The response should contain the newly-created tweet.

**IMPORTANT:** when a tweet with `content` is created, the server must process the tweet's content for `@{username}` mentions and `#{hashtag}` tags. There is no way to create hashtags or create mentions from the API, so this must be handled automatically!

#### Request
```javascript
{
  content: 'string',
  credentials: 'Credentials'
}
```

#### Response
```javascript
'Tweet'
```

### `POST    tweets/{id}/repost`
Creates a repost of the tweet with the given id. The author of the repost should match the credentials provided in the request body. If the given tweet is deleted or otherwise doesn't exist, or the given credentials do not match an active user in the database, an error should be sent in lieu of a response.

Because this creates a repost tweet, content is not allowed. Additionally, notice that the `repostOf` property is not provided by the request. The server must create that relationship.

The response should contain the newly-created tweet.

#### Request
```javascript
'Credentials'
```

#### Response
```javascript
'Tweet'
```

### `GET     tweets/{id}/tags`
Retrieves the tags associated with the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

**IMPORTANT** Remember that tags and mentions must be parsed by the server!

#### Response
```javascript
['Hashtag']
```

### `GET     tweets/{id}/likes`
Retrieves the active users who have liked the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

Deleted users should be excluded from the response.

#### Response
```javascript
['User']
```

### `GET     tweets/{id}/context`
Retrieves the context of the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

**IMPORTANT:** While deleted tweets should not be included in the `before` and `after` properties of the result, transitive replies should. What that means is that if a reply to the target of the context is deleted, but there's another reply to the deleted reply, the deleted reply should be excluded but the other reply should remain.

#### Response
```javascript
'Context'
```

### `GET     tweets/{id}/replies`
Retrieves the direct replies to the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

Deleted replies to the tweet should be excluded from the response.

#### Response
```javascript
['Tweet']
```

### `GET     tweets/{id}/reposts`
Retrieves the direct reposts of the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

Deleted reposts of the tweet should be excluded from the response.

#### Response
```javascript
['Tweet']
```

### `GET     tweets/{id}/mentions`
Retrieves the users mentioned in the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

Deleted users should be excluded from the response.

**IMPORTANT** Remember that tags and mentions must be parsed by the server!

#### Response
```javascript
['User']
```
